package com.cndll.shapetest.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.AppraiseActivity
import com.cndll.shapetest.activity.LogisticsActivity
import com.cndll.shapetest.adapter.SalesAdapter
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.api.bean.response.OrderListResponse
import com.cndll.shapetest.databinding.FragmentOrdersBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import com.handmark.pulltorefresh.library.PullToRefreshBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2017/7/7 0007.
 */
class OrdersFragment : BaseFragment<FragmentOrdersBinding>(), SalesAdapter.setOnClickLoction {
    var bundle = Bundle()
    var moerList = ArrayList<OrderListResponse.DatasBean>()
    var cah = ArrayList<OrderListResponse.DatasBean>()
    lateinit var listView: ListView
    var adapter: SalesAdapter? = null
    var page: Int = 1
    var type: String = ""
    override fun initBindingVar() {
    }

    var mNumber: Int? = null
    fun newInstance(number: Int): Fragment {
        var bundle = Bundle()
        bundle.putInt("type", number)
        val fragment = OrdersFragment()
        fragment.arguments = bundle
        return fragment
    }

    /**
     * 判断点击事件
     * */
    override fun shopAllAppeal(position: Int) {

        if (moerList[position].order_state.equals("0")) {

        } else if (moerList[position].order_state.equals("10")) {

        } else if (moerList[position].order_state.equals("20")) {

        } else if (moerList[position].order_state.equals("30")) {
            //查看物流
            bundle.putString("order_id", moerList[position].order_id)
            context.startActivity(Intent(context, LogisticsActivity::class.java).putExtras(bundle))
        } else if (moerList[position].order_state.equals("40")) {
            if (moerList[position].order_state.equals("40") && moerList[position].evaluation_state.equals("1") && moerList[position].evaluation_again_state.equals("1")) {

            } else if (moerList[position].order_state.equals("40") && moerList[position].evaluation_state.equals("1") && moerList[position].evaluation_again_state.equals("0")) {
                //删除订单
                removeOrder(moerList[position].order_id)
            } else if (moerList[position].order_state.equals("40") && moerList[position].is_excitation_state.equals("0")) {
                //查看物流
                bundle.putString("order_id", moerList[position].order_id)
                context.startActivity(Intent(context, LogisticsActivity::class.java).putExtras(bundle))
            }

        }
    }

    /**
     * 判断点击事件
     * */
    override fun shopAllApply(position: Int) {
        if (moerList[position].order_state.equals("0")) {
            //删除订单
            removeOrder(moerList[position].order_id)
        } else if (moerList[position].order_state.equals("10")) {
            //取消订单
            httpOrderCancel(moerList[position].order_id)
        } else if (moerList[position].order_state.equals("20")) {

        } else if (moerList[position].order_state.equals("30")) {
            //确认收货
            queryOrder(moerList[position].order_id)
        } else if (moerList[position].order_state.equals("40")) {
            if (moerList[position].order_state.equals("40") && moerList[position].is_excitation_state.equals("0")) {
                //确认激励
            } else if (moerList[position].order_state.equals("40") && moerList[position].evaluation_state.equals("1") && moerList[position].evaluation_again_state.equals("1")) {
                //删除订单
                removeOrder(moerList[position].order_id)
            } else if (moerList[position].order_state.equals("40") && moerList[position].evaluation_state.equals("1") && moerList[position].evaluation_again_state.equals("0")) {
                //追加评论
                bundle.putString("order_id", moerList[position].order_id)
                bundle.putString("rec_id", moerList[position].goods_list[0].rec_id)
                bundle.putString("opType", "save_again")
                context.startActivity(Intent(context, AppraiseActivity::class.java).putExtras(bundle))
            }
        }
    }

    /**
     * 判断点击事件
     * */
    override fun shopAllDetails(position: Int) {
        if (moerList[position].order_state.equals("0")) {
            //交易已取消
            return
        } else if (moerList[position].order_state.equals("10")) {
            //立即支付
        } else if (moerList[position].order_state.equals("20")) {
            //提醒发货
        } else if (moerList[position].order_state.equals("30")) {
            //确认激励
        } else if (moerList[position].order_state.equals("40")) {

            if (moerList[position].order_state.equals("40") && moerList[position].is_excitation_state.equals("0")) {
                //评价
                bundle.putString("order_id", moerList[position].order_id)
                bundle.putString("rec_id", moerList[position].goods_list[0].rec_id)
                bundle.putString("opType", "save")
                context.startActivity(Intent(context, AppraiseActivity::class.java).putExtras(bundle))
            } else if (moerList[position].order_state.equals("40") && moerList[position].evaluation_state.equals("1") && moerList[position].evaluation_again_state.equals("1")) {
                //再次购买
            } else if (moerList[position].order_state.equals("40") && moerList[position].evaluation_state.equals("1") && moerList[position].evaluation_again_state.equals("0")) {
                //再次购买
            } else {
                //评价
                bundle.putString("order_id", moerList[position].order_id)
                bundle.putString("rec_id", moerList[position].goods_list[0].rec_id)
                bundle.putString("opType", "save")
                context.startActivity(Intent(context, AppraiseActivity::class.java).putExtras(bundle))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNumber = arguments.getInt("type")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.fragment_orders, container)
        mView = binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 添加视图
     * */
    private fun initView() {
        binding.ordersPull.setOnRefreshListener(onListener2)
        binding.ordersPull.getLoadingLayoutProxy(false, true).setPullLabel("上拉中")
        binding.ordersPull.mode = PullToRefreshBase.Mode.BOTH
        binding.ordersPull.getLoadingLayoutProxy(false, true).setRefreshingLabel("刷新中")
        binding.ordersPull.getLoadingLayoutProxy(false, true).setReleaseLabel("释放刷新")
        listView = binding.ordersPull.refreshableView
        listView.divider = resources.getDrawable(R.color.gray)
        listView.dividerHeight = 6

    }

    override fun onResume() {
        super.onResume()
        if (moerList != null && moerList.size > 0) {
            moerList.clear()
        }
        if (mNumber == 1) {
            if (adapter == null) {
                adapter = SalesAdapter(context, moerList, this)
                listView.adapter = adapter
            }
            page=1
            type = ""
        } else if (mNumber == 2) {
            if (adapter == null) {
                adapter = SalesAdapter(context, moerList, this)
                listView.adapter = adapter
            }
            page=1
            type = "state_new"
        } else if (mNumber == 3) {
            if (adapter == null) {
                adapter = SalesAdapter(context, moerList, this)
                listView.adapter = adapter
            }
            page=1
            type = "state_pay"
        } else if (mNumber == 4) {
            if (adapter == null) {
                adapter = SalesAdapter(context, moerList, this)
                listView.adapter = adapter
            }
            page=1
            type = "state_send"
        } else if (mNumber == 5) {
            if (adapter == null) {
                adapter = SalesAdapter(context, moerList, this)
                listView.adapter = adapter
            }
            page=1
            type = "state_success"
        } else if (mNumber == 6) {
            if (adapter == null) {
                adapter = SalesAdapter(context, moerList, this)
                listView.adapter = adapter
            }
            page=1
            type = "state_excitation"

        }
        httpOrderList(type)
    }

    /**
     * 上拉下拉
     * */
    internal var onListener2: PullToRefreshBase.OnRefreshListener2<ListView> = object : PullToRefreshBase.OnRefreshListener2<ListView> {
        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            if (cah != null && cah.size > 0) {
                cah.clear()
            }
            page = 1 + page
            httpOrderList(type)
        }

        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            if (cah != null && cah.size > 0) {
                cah.clear()
            }
            if (moerList != null && moerList.size > 0) {
                moerList.clear()
            }
            page = 1
            httpOrderList(type)
        }
    }

    /**
     * 销毁
     * */
    override fun onDestroyView() {
        if (adapter != null) {
            adapter = null
        }
        if (moerList != null && moerList.size > 0) {
            moerList.clear()
        }
        System.gc()
        super.onDestroyView()
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
                    if (moerList != null && moerList.size > 0) {
                        moerList.clear()
                    }
                    page = 1
                    httpOrderList(type)
                } else {
                    Toast.makeText(context, "取消失败", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /**
     * 我的订单
     * */
    private fun httpOrderList(details: String) {
        if (cah != null && cah.size > 0) {
            cah.clear()
        }

        AppRequest.getAPI().orderList("member_order", "orderList", SharedPreferenceUtil.read("key", ""), details, page.toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as OrderListResponse
                if (t.code == 200) {
                    cah.addAll(t.datas)
                    moerList.addAll(cah)
                    adapter!!.notifyDataSetChanged()
                    binding.ordersPull.onRefreshComplete()
                    if(t.datas.size<=0){
                        binding.ordersPull.mode = PullToRefreshBase.Mode.PULL_FROM_START
                    }else{
                        binding.ordersPull.mode = PullToRefreshBase.Mode.BOTH
                    }
                } else {
                   Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show()
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
                    if (moerList != null && moerList.size > 0) {
                        moerList.clear()
                    }
                    page = 1
                    httpOrderList(type)
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
                    if (moerList != null && moerList.size > 0) {
                        moerList.clear()
                    }
                    page = 1
                    httpOrderList(type)
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


}