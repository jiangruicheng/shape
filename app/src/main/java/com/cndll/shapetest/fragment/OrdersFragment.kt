package com.cndll.shapetest.fragment

import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.SalesAdapter
import com.cndll.shapetest.databinding.FragmentOrdersBinding
import com.handmark.pulltorefresh.library.PullToRefreshBase

/**
 * Created by Administrator on 2017/7/7 0007.
 */
class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {
    var moerList = ArrayList<ContentValues>()
    lateinit var listView: ListView
    var adapter:SalesAdapter?=null
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
        binding.ordersPull.getLoadingLayoutProxy(false, true).setPullLabel("上蜡烛")
        binding.ordersPull.mode = PullToRefreshBase.Mode.BOTH
        binding.ordersPull.getLoadingLayoutProxy(false, true).setRefreshingLabel("刷新中")
        binding.ordersPull.getLoadingLayoutProxy(false, true).setReleaseLabel("释放刷新")
        listView = binding.ordersPull.refreshableView
        listView.divider = resources.getDrawable(R.color.gray)
        listView.dividerHeight = 6
        if(mNumber==1){
            if(adapter==null){
                adapter= SalesAdapter(context,moerList,1)
                listView.adapter=adapter
            }
        }else if (mNumber==2){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList,2)
                listView.adapter=adapter
            }
        }else if (mNumber==3){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList,3)
                listView.adapter=adapter
            }
        }else if (mNumber==4){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList,4)
                listView.adapter=adapter
            }
        }else if(mNumber==5){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList,5)
                listView.adapter=adapter
            }
        }else if (mNumber==6){
            if(adapter==null){
                adapter=SalesAdapter(context,moerList,6)
                listView.adapter=adapter
            }
        }

        initShopData()
    }
    //测试数据
    private fun initShopData() {
        var con: ContentValues = ContentValues()
        con.put("shopAllImg", "http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        var con1: ContentValues = ContentValues()
        con1.put("shopAllImg", "http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        var con2: ContentValues = ContentValues()
        con2.put("shopAllImg", "http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        moerList.add(con)
        moerList.add(con1)
        moerList.add(con2)
        adapter!!.notifyDataSetChanged()
    }
    internal var onListener2: PullToRefreshBase.OnRefreshListener2<ListView> = object : PullToRefreshBase.OnRefreshListener2<ListView> {
        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.ordersPull.onRefreshComplete()
        }

        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.ordersPull.onRefreshComplete()
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

}