package com.cndll.shapetest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.SalesAdapter
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.OrderListResponse
import com.cndll.shapetest.databinding.FragmentOrdersBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import com.handmark.pulltorefresh.library.PullToRefreshBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2017/7/7 0007.
 */
class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {
    var moerList = ArrayList<OrderListResponse.DatasBean>()
    var cah = ArrayList<OrderListResponse.DatasBean>()
    lateinit var listView: ListView
    var adapter:SalesAdapter?=null
    var page:Int=1
    override fun initBindingVar() {
    }

    var mNumber:Int?=null
    fun newInstance(number: Int):Fragment {
        var bundle=Bundle()
        bundle.putInt("type",number)
        val fragment = OrdersFragment()
        fragment.arguments=bundle
        return fragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNumber = arguments.getInt("type")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.fragment_orders,container)
        mView = binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initView(){
        binding.ordersPull.setOnRefreshListener(onListener2)
        binding.ordersPull.getLoadingLayoutProxy(false, true).setPullLabel("上拉中")
        binding.ordersPull.mode = PullToRefreshBase.Mode.BOTH
        binding.ordersPull.getLoadingLayoutProxy(false, true).setRefreshingLabel("刷新中")
        binding.ordersPull.getLoadingLayoutProxy(false, true).setReleaseLabel("释放刷新")
        listView = binding.ordersPull.refreshableView
        listView.divider = resources.getDrawable(R.color.gray)
        listView.dividerHeight = 6
        if(mNumber==1){
            if(adapter==null){
                adapter= SalesAdapter(context,moerList)
                listView.adapter=adapter
            }
            httpOrderList("")
        }else if (mNumber==2){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList)
                listView.adapter=adapter
            }
            httpOrderList("state_new")
        }else if (mNumber==3){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList)
                listView.adapter=adapter
            }
            httpOrderList("state_pay")
        }else if (mNumber==4){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList)
                listView.adapter=adapter
            }
            httpOrderList("state_send")
        }else if(mNumber==5){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList)
                listView.adapter=adapter
            }
            httpOrderList("state_success")
        }else if (mNumber==6){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList)
                listView.adapter=adapter
            }
            httpOrderList("state_excitation")
        }
    }
    internal var onListener2: PullToRefreshBase.OnRefreshListener2<ListView> = object : PullToRefreshBase.OnRefreshListener2<ListView> {
        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            if (cah!=null && cah.size>0){
                cah.clear()
            }
            page=1+page
            when(mNumber){
                1 ->  httpOrderList("")
                2 ->  httpOrderList("state_new")
                3 ->  httpOrderList("state_pay")
                4 ->  httpOrderList("state_send")
                5 ->  httpOrderList("state_success")
                6 ->   httpOrderList("state_excitation")
            }
        }

        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            if (cah!=null && cah.size>0){
                cah.clear()
            }
            if(moerList!=null && moerList.size>0){
                moerList.clear()
            }
            page=1
            when(mNumber){
                1 ->  httpOrderList("")
                2 ->  httpOrderList("state_new")
                3 ->  httpOrderList("state_pay")
                4 ->  httpOrderList("state_send")
                5 ->  httpOrderList("state_success")
                6 ->   httpOrderList("state_excitation")
            }
        }
    }

    override fun onDestroyView() {
        if(adapter!=null){
            adapter=null
        }
        if(moerList !=null && moerList.size>0){
            moerList.clear()
        }
        System.gc()
        super.onDestroyView()
    }

    /**
     * 我的订单
     * */
    private fun httpOrderList(details:String){
        if (cah!=null && cah.size>0){
            cah.clear()
        }

       AppRequest.getAPI().orderList("member_order","orderList",SharedPreferenceUtil.read("key",""),details,page.toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
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
               if (t.code==200){
                   cah.addAll(t.datas)
                   moerList.addAll(cah)
                   adapter!!.notifyDataSetChanged()
                   binding.ordersPull.onRefreshComplete()
               }else{
                   binding.ordersPull.mode = PullToRefreshBase.Mode.PULL_FROM_START
               }
           }
       })
    }



}