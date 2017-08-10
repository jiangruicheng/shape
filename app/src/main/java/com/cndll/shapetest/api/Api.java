package com.cndll.shapetest.api;


import com.cndll.shapetest.api.bean.BaseRequest;

import com.cndll.shapetest.api.bean.BaseResponse;
import com.cndll.shapetest.api.bean.response.BrandResponse;
import com.cndll.shapetest.api.bean.response.CommodityResponse;
import com.cndll.shapetest.api.bean.response.FightResponse;
import com.cndll.shapetest.api.bean.response.HomePageResponse;
import com.cndll.shapetest.api.bean.response.LimitedResponse;
import com.cndll.shapetest.api.bean.response.AboutUsResponse;
import com.cndll.shapetest.api.bean.response.AddressDetailsResponse;
import com.cndll.shapetest.api.bean.response.AddressListResponse;
import com.cndll.shapetest.api.bean.response.AddressResponse;
import com.cndll.shapetest.api.bean.response.FileResponse;
import com.cndll.shapetest.api.bean.response.HttpCodeResponse;
import com.cndll.shapetest.api.bean.response.LineaOffResponse;
import com.cndll.shapetest.api.bean.response.LogisticsResponse;
import com.cndll.shapetest.api.bean.response.MemberResponse;
import com.cndll.shapetest.api.bean.response.NearByResponse;
import com.cndll.shapetest.api.bean.response.OrderListResponse;
import com.cndll.shapetest.api.bean.response.RegisterResponse;
import com.cndll.shapetest.api.bean.response.ScoreResponse;
import com.cndll.shapetest.api.bean.response.TestResponse;
import com.cndll.shapetest.api.bean.response.UserInfoResponse;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jiang_ruicheng on 16/10/27.
 */
public interface Api {
    @POST("/index/")
    Observable<TestResponse> test(@Body BaseRequest request);

    //首页
    @GET("/mobile/index.php?act=index")
    Observable<HomePageResponse> homePage(@Query("gc_id") String id, @Query("page") String page);

    //限时秒杀
    @GET("/mobile/index.php?act=goods&op=goodsSeckill")
    Observable<LimitedResponse> limitidPage(@Query("page") String page, @Query("time_part") String timePart);

    //拼团
    @GET("/mobile/index.php?act=fight_groups_goods&op=index")
    Observable<FightResponse> fightPage(@Query("page") String page);

    /*@GET("/mobile/index.php?act=goods&op=index&goods_id=7")
    Observable<CommodityResponse> commodiytPage();*/
    @GET("/mobile/index.php?act=goods&op=index&goods_id=80&fight_groups_id=13")
    Observable<CommodityResponse> commodiytPage();

    //品牌折扣
    @GET("/mobile/index.php?act=goods&op=goods_type&goods_type=2&page=1")
    Observable<BrandResponse> brandPage();

    //积分
    @GET("mobile/index.php?act=score_goods&op=index")
    Observable<ScoreResponse> scorePage(@Query("key") String key);

    //会员
    @GET("/mobile/index.php?act=goods&op=membergoods")
    Observable<MemberResponse> memberPage(@Query("key") String key);

    //线下体验店
    @GET("/mobile/index.php?act=line_store&op=index")
    Observable<LineaOffResponse> lineaOffPage(@Query("page") String page);

    //线下体验店
    @GET("/mobile/index.php?act=nearby&op=nearby")
    Observable<NearByResponse> nearByPage(@Query("page") String page, @Query("nearby_type") String type);

    /**
     * 地区列表
     **/
    @GET("mobile/index.php/")
    Observable<AddressResponse> addressList(@Query("act") String act, @Query("op") String op);

    /**
     * 地址列表
     **/
    @GET("mobile/index.php/")
    Observable<AddressListResponse> addressList(@Query("act") String act, @Query("op") String op, @Query("key") String key);

    /**
     * 地址详情
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<AddressDetailsResponse> addressDetails(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("address_id") String addressId);

    /**
     * 编辑地址
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> updateAddress(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("address_id") String addId, @Field("is_default") String is_default,
                                               @Field("true_name") String true_name, @Field("area_id") String area_id, @Field("city_id") String city_id, @Field("tel_phone") String tel_phone, @Field("address") String address, @Field("area_info") String area_info);

    /**
     * 添加地址
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> addAddress(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("is_default") String is_default,
                                            @Field("true_name") String true_name, @Field("area_id") String area_id, @Field("city_id") String city_id, @Field("tel_phone") String tel_phone, @Field("address") String address, @Field("area_info") String area_info);

    /**
     * 删除地址
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> deleteAddress(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("address_id") String address_id);

    /**
     * 注册
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<RegisterResponse> register(@Field("act") String act, @Field("op") String op, @Field("username") String username, @Field("password") String password, @Field("password_confirm") String password_confirm, @Field("code") String code, @Field("client") String client, @Field("member_code") String member_code);

    /**
     * 发送验证码
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> sendCode(@Field("act") String act, @Field("op") String op, @Field("mobile") String mobile);

    /**
     * 登录
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<RegisterResponse> login(@Field("act") String act, @Field("op") String op, @Field("username") String username, @Field("password") String password, @Field("client") String client);

    /**
     * 忘记密码
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<RegisterResponse> newLoginPwd(@Field("act") String act, @Field("op") String op, @Field("username") String username, @Field("password") String password, @Field("code") String code, @Field("type") String type);

    /**
     * 修改登錄密碼
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> updateLoginPwd(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("password") String password, @Field("new_password") String new_password);

    /**
     * 会员信息
     **/
    @GET("mobile/index.php/")
    Observable<UserInfoResponse> userInfo(@Query("act") String act, @Query("op") String op, @Query("key") String key);

    /**
     * 修改个人信息
     **/
    @Multipart
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> updateUserInfo(@PartMap Map<String, RequestBody> map);

    /**
     * 关于我们
     **/
    @GET("mobile/index.php/")
    Observable<AboutUsResponse> aboutUs(@Query("act") String act, @Query("op") String op);

    /**
     * 我的订单
     **/
    @GET("mobile/index.php/")
    Observable<OrderListResponse> orderList(@Query("act") String act, @Query("op") String op, @Query("key") String key, @Query("order_state") String order_state, @Query("page") String page);

    /**
     * 查看物流
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<LogisticsResponse> logistics(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("order_id") String order_id);

    /**
     * 取消订单
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> orderCancel(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("order_id") String order_id);

    /**
     * 上传文件-评论
     **/
    @Multipart
    @POST("mobile/index.php/")
    Observable<FileResponse> uploadFile(@PartMap Map<String, RequestBody> map);

    /**
     * 评论
     **/
    @Multipart
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> appraise(@PartMap Map<String, RequestBody> map);

    /**
     * 删除订单
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> deleteOrder(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("order_id") String order_id);

    /**
     * 确认收货
     **/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> queryOrder(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("order_id") String order_id);
}

