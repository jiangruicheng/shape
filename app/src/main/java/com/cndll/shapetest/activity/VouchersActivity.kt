package com.cndll.shapetest.activity

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.widget.ListView
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.GroupBookingAdapter
import com.cndll.shapetest.adapter.VouchersAdapter
import com.cndll.shapetest.databinding.ActivityVouchersBinding
import com.handmark.pulltorefresh.library.PullToRefreshBase
import java.util.*

/**
 * 我的抵用卷
 * */
class VouchersActivity : BaseActivity<ActivityVouchersBinding>() {
   lateinit var context: Context
    var moreList=ArrayList<ContentValues>()
     var adapter:VouchersAdapter?=null
    lateinit var listView:ListView

    var adapterBooking:GroupBookingAdapter?=null

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_vouchers)
        context=this
        initView()

    }

    private fun initView(){
        binding.pull.setOnRefreshListener(onListener2)
        binding.pull.getLoadingLayoutProxy(false,true).setPullLabel("上蜡烛")
        binding.pull.mode=PullToRefreshBase.Mode.BOTH
        binding.pull.getLoadingLayoutProxy(false, true).setRefreshingLabel("刷新中")
        binding.pull.getLoadingLayoutProxy(false, true).setReleaseLabel("释放刷新")
        listView=binding.pull.refreshableView
        listView.divider=resources.getDrawable(R.color.bg)


        var bundle=this.intent.extras
        var type=bundle.getString("type")
        if(type.equals("voucher")){
            binding.titlebar.title.text="我的抵用卷"
            //我的优惠卷
            listView.dividerHeight=50
            if(adapter==null){
                adapter= VouchersAdapter(moreList,context)
                listView.adapter=adapter
            }
            initData()
        }else if(type.equals("booking")){
            binding.titlebar.title.text="我的拼团"
            if(adapterBooking==null){
                adapterBooking= GroupBookingAdapter(context,moreList)
                listView.adapter=adapterBooking
            }
            initBookingData()
        }
    }


    private fun initBookingData(){
        var con:ContentValues= ContentValues()
        con.put("shopImg","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con.put("shopName","万聚鲜城 三文鱼刺身拼盘500g 生鱼片 袋 装 海鲜水产")
        con.put("shopType","2人团")
        con.put("shopPrice","11")
        con.put("shopOldPrice","22")
        var con1:ContentValues= ContentValues()
        con1.put("shopImg","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con1.put("shopName","万聚鲜城 三文鱼刺身拼盘500g 生鱼片 袋 装 海鲜水产")
        con1.put("shopType","5人团")
        con1.put("shopPrice","111.00")
        con1.put("shopOldPrice","222.00")
        moreList.add(con1)
        moreList.add(con)
        adapterBooking!!.notifyDataSetChanged()
    }

    private fun initData(){
        var con:ContentValues= ContentValues()
        con.put("time","1027-5-5")
        con.put("price","22.55")
        moreList.add(con)
        var con1:ContentValues= ContentValues()
        con1.put("time","1027-5-5")
        con1.put("price","22.55")
        moreList.add(con1)
        var con2:ContentValues= ContentValues()
        con2.put("time","1027-5-5")
        con2.put("price","22.55")
        moreList.add(con2)
        adapter!!.notifyDataSetChanged()

    }


    internal var onListener2: PullToRefreshBase.OnRefreshListener2<ListView> = object : PullToRefreshBase.OnRefreshListener2<ListView> {

        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>) {
        //xia
            binding.pull.onRefreshComplete()
        }

        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>) {
        //shang
            binding.pull.onRefreshComplete()
        }
    }


}
