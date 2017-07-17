package com.cndll.shapetest.fragment

import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.GroupBookingAdapter
import com.cndll.shapetest.databinding.FragmentSalesBinding
import com.handmark.pulltorefresh.library.PullToRefreshBase
import java.util.ArrayList

/**
 * Created by Administrator on 2017/7/10 0010.
 */
class SalesFragment : BaseFragment<FragmentSalesBinding>(){
    var moreList= ArrayList<ContentValues>()
    var adapter: GroupBookingAdapter?=null
    lateinit var listView:ListView

    var mNumber:Int?=null
    fun newInstance(number: Int): Fragment {
        var bundle=Bundle()
        bundle.putInt("type",number)
        val fragment = SalesFragment()
        fragment.arguments=bundle
        return fragment
    }

    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNumber = arguments.getInt("type")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.fragment_sales,container)
        mView=binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initView(){
        binding.salesPull.setOnRefreshListener(onListener2)
        binding.salesPull.getLoadingLayoutProxy(false, true).setPullLabel("上蜡烛")
        binding.salesPull.mode = PullToRefreshBase.Mode.BOTH
        binding.salesPull.getLoadingLayoutProxy(false, true).setRefreshingLabel("刷新中")
        binding.salesPull.getLoadingLayoutProxy(false, true).setReleaseLabel("释放刷新")
        listView = binding.salesPull.refreshableView
        listView.divider = resources.getDrawable(R.color.gray)
        listView.dividerHeight = 6
        if(moreList.size > 0 && moreList!=null){moreList.clear()}
        adapter=null
        if(mNumber==1){
            //维权中
            if(adapter==null){
                adapter= GroupBookingAdapter(context,moreList,1)
                listView.adapter=adapter
            }
        }else if (mNumber==2){
            //已退款
            if(adapter==null){
                adapter= GroupBookingAdapter(context,moreList,2)
                listView.adapter=adapter
            }
        }else if (mNumber==3){
            //已拒绝
            if(adapter==null){
                adapter= GroupBookingAdapter(context,moreList,3)
                listView.adapter=adapter
            }
        }
        initBookingData()
    }
    private fun initBookingData(){
        var con:ContentValues= ContentValues()
        con.put("shopImg","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con.put("shopName","万聚鲜城 三文鱼刺身拼盘500g 生鱼片 袋 装 海鲜水产")
        con.put("shopType","麻辣味")
        con.put("shopPrice","11")
        con.put("shopOldPrice","22")
        var con1:ContentValues= ContentValues()
        con1.put("shopImg","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con1.put("shopName","万聚鲜城 三文鱼刺身拼盘500g 生鱼片 袋 装 海鲜水产")
        con1.put("shopType","麻辣味")
        con1.put("shopPrice","111.00")
        con1.put("shopOldPrice","222.00")
        moreList.add(con1)
        moreList.add(con)
        adapter!!.notifyDataSetChanged()
    }

    internal var onListener2: PullToRefreshBase.OnRefreshListener2<ListView> = object : PullToRefreshBase.OnRefreshListener2<ListView> {
        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.salesPull.onRefreshComplete()
        }

        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.salesPull.onRefreshComplete()
        }
    }



}