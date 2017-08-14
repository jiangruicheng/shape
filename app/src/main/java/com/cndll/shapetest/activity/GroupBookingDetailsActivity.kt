package com.cndll.shapetest.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.api.bean.response.OrderListResponse
import com.cndll.shapetest.databinding.ActivityGroupBookingDetailsBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 拼团订单详情------订单详情
 * */
class GroupBookingDetailsActivity : BaseActivity<ActivityGroupBookingDetailsBinding>() {
    lateinit var context: Context
    var bundle = Bundle()
    var orderBean = OrderListResponse.DatasBean()
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "订单详情"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_group_booking_details)
        context = this
        initView()
    }

    /**
     * 判断 中，成功，失败
     * */
    private fun initView() {
        var bundles = this.intent.extras
        if (bundles.containsKey("orderList")) {
            orderBean = bundles.getSerializable("orderList") as OrderListResponse.DatasBean

            if (orderBean.order_state.equals("0")) {
                binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.order_ok))
                binding.groupBookingDetails.text = "删除订单"
                binding.groupBookingDetails.setOnClickListener {
                    removeOrder(orderBean.order_id)
                }
            } else if (orderBean.order_state.equals("10")) {
                binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.group_payment))
                binding.groupBookingDetails.text = "取消订单"
                binding.groupLogistics.visibility = View.VISIBLE
                binding.groupLogistics.setBackgroundResource(R.drawable.shape_button_red)
                binding.groupLogistics.setTextColor(resources.getColor(R.color.white))
                binding.groupLogistics.text = "立即支付"
                binding.groupBookingDetails.setOnClickListener {
                    httpOrderCancel(orderBean.order_id)
                }
                binding.groupLogistics.setOnClickListener { }
            } else if (orderBean.order_state.equals("20")) {
                binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_shipments))
                binding.groupLogistics.visibility = View.VISIBLE
                binding.groupLogistics.setBackgroundResource(R.drawable.shape_button_red)
                binding.groupLogistics.setTextColor(resources.getColor(R.color.white))
                binding.groupLogistics.text = "提醒发货"
                binding.groupBookingDetails.text = "申请退款"
                binding.groupBookingDetails.setOnClickListener { }
                binding.groupLogistics.setOnClickListener { }
            } else if (orderBean.order_state.equals("30")) {
                binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_delivery))
                binding.groupLogistics.visibility = View.VISIBLE
                binding.groupIncentive.visibility = View.VISIBLE
                binding.groupBall.visibility = View.VISIBLE

                binding.groupBookingDetails.text = "申请退款"
                binding.groupLogistics.text = "查看物流"
                binding.groupIncentive.text = "确认收货"
                binding.groupBall.text = "确认激励"
                binding.groupIncentive.setBackgroundResource(R.drawable.shape_button_red)
                binding.groupIncentive.setTextColor(resources.getColor(R.color.white))
                binding.groupBookingDetails.setOnClickListener { }
                binding.groupLogistics.setOnClickListener {
                    bundle.putString("order_id", orderBean.order_id)
                    context.startActivity(Intent(context, LogisticsActivity::class.java).putExtras(bundle))
                }
                binding.groupIncentive.setOnClickListener {
                    queryOrder(orderBean.order_id)
                }
                binding.groupBall.setOnClickListener {
                    dealView(orderBean.order_id)
                }
            } else if (orderBean.order_state.equals("40")) {
                if (orderBean.order_state.equals("40") && orderBean.is_excitation_state.equals("0")) {
                    binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_delivery))
                    binding.groupLogistics.visibility = View.VISIBLE
                    binding.groupIncentive.visibility = View.VISIBLE
                    binding.groupBall.visibility = View.VISIBLE

                    binding.groupBookingDetails.text = "申请退款"
                    binding.groupLogistics.text = "查看物流"
                    binding.groupIncentive.text = "确认激励"
                    binding.groupBall.text = "评价"
                    binding.groupIncentive.setBackgroundResource(R.drawable.shape_button_red)
                    binding.groupIncentive.setTextColor(resources.getColor(R.color.white))

                    binding.groupBookingDetails.setOnClickListener { }
                    binding.groupLogistics.setOnClickListener {
                        bundle.putString("order_id", orderBean.order_id)
                        context.startActivity(Intent(context, LogisticsActivity::class.java).putExtras(bundle))
                    }
                    binding.groupIncentive.setOnClickListener {
                        dealView(orderBean.order_id)
                    }
                    binding.groupBall.setOnClickListener {
                        bundle.putString("order_id", orderBean.order_id)
                        bundle.putString("rec_id", orderBean.goods_list[0].rec_id)
                        bundle.putString("opType", "save")
                        context.startActivity(Intent(context, AppraiseActivity::class.java).putExtras(bundle))
                    }
                } else if (orderBean.order_state.equals("40") && orderBean.evaluation_state.equals("1") && orderBean.evaluation_again_state.equals("1")) {
                    binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_delete))
                    binding.groupLogistics.visibility = View.VISIBLE
                    binding.groupBookingDetails.text = "删除订单"
                    binding.groupLogistics.text = "再次购买"
                    binding.groupBookingDetails.setOnClickListener { removeOrder(orderBean.order_id) }
                    binding.groupLogistics.setOnClickListener { }
                } else if (orderBean.order_state.equals("40") && orderBean.evaluation_state.equals("1") && orderBean.evaluation_again_state.equals("0")) {
                    binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_ok))
                    binding.groupLogistics.visibility = View.VISIBLE
                    binding.groupIncentive.visibility = View.VISIBLE
                    binding.groupBookingDetails.text = "删除订单"
                    binding.groupLogistics.text = "追加评价"
                    binding.groupIncentive.text = "再次购买"
                    binding.groupBookingDetails.setOnClickListener { removeOrder(orderBean.order_id) }
                    binding.groupLogistics.setOnClickListener {
                        bundle.putString("order_id", orderBean.order_id)
                        bundle.putString("rec_id", orderBean.goods_list[0].rec_id)
                        bundle.putString("opType", "save_again")
                        context.startActivity(Intent(context, AppraiseActivity::class.java).putExtras(bundle))
                    }
                    binding.groupIncentive.setOnClickListener { }
                } else {
                    binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.order_ok))
                    binding.groupLogistics.visibility = View.VISIBLE
                    binding.groupLogistics.setBackgroundResource(R.drawable.shape_button_red)
                    binding.groupLogistics.setTextColor(resources.getColor(R.color.white))
                    binding.groupBookingDetails.text = "查看物流"
                    binding.groupLogistics.text = "评价"
                    binding.groupBookingDetails.setOnClickListener {
                        bundle.putString("order_id", orderBean.order_id)
                        context.startActivity(Intent(context, LogisticsActivity::class.java).putExtras(bundle))
                    }
                    binding.groupLogistics.setOnClickListener {
                        bundle.putString("order_id", orderBean.order_id)
                        bundle.putString("rec_id", orderBean.goods_list[0].rec_id)
                        bundle.putString("opType", "save")
                        context.startActivity(Intent(context, AppraiseActivity::class.java).putExtras(bundle))

                    }
                }
            }
        }


//        var type = 1
//        if (type == 1) {
//            //凭团中
//            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.group_booking))
//        } else if (type == 2) {
//            //成功--等待收货
//            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_ok))
//        } else if (type == 3) {
//            //失败
//            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_delete))
//        } else if (type == 4) {
//            //待发货
//            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_shipments))
//        } else if (type == 5) {
//            //收货
//            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.booking_delivery))
//        } else if (type == 6) {
//            //待付款
//            binding.groupBookingType.setImageDrawable(resources.getDrawable(R.mipmap.group_payment))
//        }
//
//        //1查看团详情
//        binding.groupBookingDetails.setOnClickListener {
//            context.startActivity(Intent(context, ApplyForRefundActivity::class.java))
//        }
//        //2查看物流
//        binding.groupLogistics.setOnClickListener {
//            context.startActivity(Intent(context, LogisticsActivity::class.java))
//        }
//        //3确认激励
//        binding.groupIncentive.setOnClickListener { }
//        //4
//        binding.groupBall.setOnClickListener { }
    }

    /**
     * 取消订单
     * */
    private fun httpOrderCancel(orderID: String) {
        AppRequest.getAPI().orderCancel("member_order", "order_cancel", SharedPreferenceUtil.read("key", ""), orderID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if (t.code == 200) {
                    Toast.makeText(context, "取消成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(context, "取消失败", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /**
     * 删除订单
     * */
    private fun removeOrder(orderID: String) {
        AppRequest.getAPI().deleteOrder("member_order", "order_delete", SharedPreferenceUtil.read("key", ""), orderID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if (t.code == 200) {
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

        })

    }

    /**
     * 确认收货
     * */
    private fun queryOrder(orderID: String) {
        AppRequest.getAPI().queryOrder("member_order", "order_receive", SharedPreferenceUtil.read("key", ""), orderID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if (t.code == 200) {
                    Toast.makeText(context, "收货成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(context, "收货失败", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

        })

    }

    /**
     * 确认激励
     * */
    private fun excitation(orderID: String) {
        AppRequest.getAPI().excitation("member_order", "excitation", SharedPreferenceUtil.read("key", ""), orderID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {

            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if (t.code == 200) {
                    Toast.makeText(context, "激励成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(context, "激励失败", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }

    /**
     * 对话框
     * */
    private fun dealView(orderID: String) {
        val dm = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)
        val dialog = Dialog(context, R.style.AlertDialog)
        dialog!!.setContentView(R.layout.deal_content)
        dialog!!.setCanceledOnTouchOutside(true)
        dialog!!.show()
        // 设置对话框大小
        val layoutParams = dialog!!.window.attributes
        layoutParams.width = (dm.widthPixels * 0.8).toInt()
        layoutParams.height = (dm.heightPixels * 0.9).toInt()
        dialog!!.window.attributes = layoutParams

        val title = dialog!!.findViewById(R.id.deal_title) as TextView
        val ok = dialog!!.findViewById(R.id.deal_ok) as TextView
        val chose = dialog!!.findViewById(R.id.deal_close) as TextView
        title.text = "确认激励后无法进行退换货，是否确认激励"
        chose.setOnClickListener {
            dialog.dismiss()
        }
        ok.setOnClickListener {
            dialog.dismiss()
            excitation(orderID)
        }
    }

}
