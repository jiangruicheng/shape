package com.cndll.shapetest.tools;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/24 0024.
 * 支付
 */

public class PayTool {
    /**
     * 替换订单号--支付
     * */
    public static void pay(final Activity activity, final ContentValues goods, final ContentValues addr, final int type, final Handler mHandler) {
        // 生成新的订单号（price ==0 失败）
        if (goods.containsKey("ddid") && goods.getAsString("ddid") != null && !"".equals(goods.getAsString("ddid"))) {
//            final LoadingDialog mDialog = new LoadingDialog(activity);
//            mDialog.setText("正在加载");
//            mDialog.show();

            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("action", "Orderid.updateOrderCode")
                    .add("id", String.valueOf(goods.getAsInteger("id"))).build();
            Request request = new Request.Builder()
                    .url(Ini.Url)
                    .post(requestBody)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
//                    mDialog.dismiss();
                    Log.e("pay", e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
//                    mDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (0 == jsonObject.getInt("error")) {
                            Log.e("ddid:::::::", "" + jsonObject.get("data").toString());
//                            SharedPreferenceUtil.insert("orderid", jsonObject.get("data").toString());
                            if (type == Ini.PAY_TYPE_WEIXIN) {
                                PayForWeixin(activity, goods);
                            } else if (type == Ini.PAY_TYPE_ZFB) {
                                PayForZFB(activity, goods, mHandler);
                            }
                        } else {
                            Log.e("pay", "error");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            getUUID(activity, goods, addr, type, mHandler);
        }

        //不生产新的订单号------同一个订单不能二次支付
//        if (goods.containsKey("ddid") && goods.getAsString("ddid") != null && !"".equals(goods.getAsString("ddid"))) {
//            SharedPreferenceUtil.insert("orderid", goods.getAsString("ddid").trim());
//            if (type == Ini.PAY_TYPE_WEIXIN) {
//                PayForWeixin(activity, goods);
//            } else if (type == Ini.PAY_TYPE_ZFB) {
//                PayForZFB(activity, goods, mHandler);
//            }
//        }else{
//            getUUID(activity, goods, addr, type, mHandler);
//        }

    }

    /**
     * 后台获取订单号
     * */
    private static void getUUID(final Activity activity, final ContentValues goods, ContentValues addr, final int type, final Handler mHandler) {
//        final LoadingDialog mDialog = new LoadingDialog(activity);
//        mDialog.setText("正在加载");
//        mDialog.show();

        OkHttpClient client = new OkHttpClient();
        String channel = "0";
        if (type == Ini.PAY_TYPE_WEIXIN) {
            channel = "1";
        } else if (type == Ini.PAY_TYPE_ZFB) {
            channel = "0";
        }
        RequestBody requestBody = new FormBody.Builder()
                .add("action", "Orderid.payChannel")
//                .add("uid", SharedPreferenceUtil.read("id",""))
                .add("channel", channel)
                .add("money", goods.containsKey("price") ? goods.getAsString("price") : goods.getAsString("money"))
//                .add("money", "0.01")
                .add("trade_name", goods.containsKey("good_name") ? goods.getAsString("good_name") : goods.getAsString("trade_name"))
                .add("ship",goods.getAsString("ship"))
                .add("num", String.valueOf(goods.getAsInteger("num")))
                .add("shid", String.valueOf(goods.getAsInteger("uid")))
                .add("address", String.valueOf(addr.getAsInteger("id")))
                .add("goodsid", String.valueOf(goods.getAsInteger("id"))).build();
        Request request = new Request.Builder()
                .url(Ini.Url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                mDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                mDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (0 == jsonObject.getInt("error")) {
                        Log.e("sdsdsdsd:",""+jsonObject.get("data").toString());

                        //微信支付
                        JSONObject jsonobject = new JSONObject(jsonObject.get("data").toString());
//                        SharedPreferenceUtil.insert("orderid", jsonobject.getString("ddid"));
                        if (type == Ini.PAY_TYPE_WEIXIN) {
                            PayForWeixin(activity, goods);
                        } else if (type == Ini.PAY_TYPE_ZFB) {
                            PayForZFB(activity, goods, mHandler);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    /**
     * 支付宝支付
     *
     * @param
     * @param goods
     * @param mHandler
     */
    private static void PayForZFB(final Activity activity, final ContentValues goods, final Handler mHandler) {
        String allPrice = "";
        if (goods.containsKey("price")) {
//            allPrice = ValidData.formatDouble4(((Double.valueOf(goods.getAsString("price")) * goods.getAsInteger("num")) + Double.valueOf(goods.getAsString("ship"))));
        } else {
//            allPrice =ValidData.formatDouble4(goods.getAsDouble("totalmoney"));
        }
        /**
         * 请求支付宝界面支付
         * */
        String orderName=goods.containsKey("good_name") ? goods.getAsString("good_name") : goods.getAsString("trade_name");
        final OkHttpClient client = new OkHttpClient();
        String url = Ini.RequestPay_Alipay + "?price=" + allPrice +
//                "&orderid=" + SharedPreferenceUtil.read("orderid", "") +
                "&trade_name=" + orderName;
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("erro", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String orderInfo = response.body().string();   // 订单信息
                Message msg = new Message();
                msg.what = Ini.SDK_PAY_FLAG2;
                msg.obj = orderInfo;
                mHandler.sendMessage(msg);
            }
        });
    }

    /**
     * 支付状态结果
     * */
    public static void payZFB(final Activity activity, final String orderInfo, final Handler mHandler) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Bundle bundle =new Bundle();
                bundle.putString("resultStatus",result.get("resultStatus"));
                Message msg = new Message();
                msg.what = Ini.SDK_PAY_FLAG;
                msg.obj = result;
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 微信支付
     *
     * @param
     * @param goods
     */
    private static void PayForWeixin(final Activity activity, ContentValues goods) {
        String allPrice = "";  //总价格
        if (goods.containsKey("price")) {
//            allPrice = ValidData.formatDouble4(((Double.valueOf(goods.getAsString("price")) * goods.getAsInteger("num")) + Double.valueOf(goods.getAsString("ship"))));
        } else {
//            allPrice =ValidData.formatDouble4(goods.getAsDouble("totalmoney"));
        }
        String orderName=goods.containsKey("good_name") ? goods.getAsString("good_name") : goods.getAsString("trade_name");
        /**
         * 请求微信页面支付
         * */
        OkHttpClient client = new OkHttpClient();
        String url = Ini.RequestPay_Weixin + "?price=" + allPrice +
//                "&orderid=" + SharedPreferenceUtil.read("orderid", "") +
                "&trade_name=" + orderName;
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("erro", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String test = response.body().string();
                    Log.e("sssss:::::",""+test);
                    JSONObject object = new JSONObject(test);
                    final IWXAPI msgApi = WXAPIFactory.createWXAPI(activity, null);
                    // 将该app注册到微信
                    msgApi.registerApp(object.getString("appid"));
                    PayReq request = new PayReq();
                    request.appId = object.getString("appid");
                    request.partnerId = object.getString("partnerid");
                    request.prepayId = object.getString("prepayid");
                    request.packageValue = "Sign=WXPay";
                    request.nonceStr = object.getString("noncestr");
                    request.timeStamp = String.valueOf(object.getInt("timestamp"));
                    request.sign = object.getString("sign");
                    msgApi.sendReq(request);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
