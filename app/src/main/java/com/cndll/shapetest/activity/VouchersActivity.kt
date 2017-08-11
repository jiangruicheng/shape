package com.cndll.shapetest.activity

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ListView
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.AdvanceOrderAdapter
import com.cndll.shapetest.adapter.GroupBookingAdapter
import com.cndll.shapetest.adapter.MyViewPagerAdapter
import com.cndll.shapetest.databinding.ActivityVouchersBinding
import com.cndll.shapetest.fragment.VouchersFragment
import com.handmark.pulltorefresh.library.PullToRefreshBase

/**
     * 我的抵用卷--预约订单--凭团
 * */
class VouchersActivity : BaseActivity<ActivityVouchersBinding>() {
   lateinit var context: Context
    var moreList=ArrayList<ContentValues>()
    lateinit var listView:ListView
    var adapterBooking:GroupBookingAdapter?=null
    var adapterAdv:AdvanceOrderAdapter?=null

    private var fragemnts=ArrayList<Fragment>()
    private val titles = arrayOf("红包抵用卷", "通用抵用卷")
    private var adapterPage: MyViewPagerAdapter?=null

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
        binding.pull.getLoadingLayoutProxy(false,true).setPullLabel("上拉中")
        binding.pull.mode=PullToRefreshBase.Mode.BOTH
        binding.pull.getLoadingLayoutProxy(false, true).setRefreshingLabel("刷新中")
        binding.pull.getLoadingLayoutProxy(false, true).setReleaseLabel("释放刷新")
        listView=binding.pull.refreshableView
        listView.divider=resources.getDrawable(R.color.bg)


        var bundle=this.intent.extras
        var type=bundle.getString("type")
        if(type.equals("voucher")){
            binding.titlebar.title.text="我的抵用卷"
            binding.pull.visibility= View.GONE
            binding.vouchersViewpager.visibility=View.VISIBLE
            binding.vouchersTab.visibility=View.VISIBLE
            fragemnts.add(VouchersFragment().newInstance(1))
            fragemnts.add(VouchersFragment().newInstance(2))
            adapterPage=MyViewPagerAdapter(supportFragmentManager,fragemnts,titles)
            binding.vouchersViewpager.adapter=adapterPage
            binding.vouchersTab.setupWithViewPager(binding.vouchersViewpager)
            binding.vouchersTab.setTabsFromPagerAdapter(adapterPage)

        }else if(type.equals("booking")){
            listView.dividerHeight=10
            binding.titlebar.title.text="我的拼团"
            if(adapterBooking==null){
                adapterBooking= GroupBookingAdapter(context,moreList,0)
                listView.adapter=adapterBooking
            }
            initBookingData()
        }else if (type.equals("advance")){
            listView.dividerHeight=50
            binding.titlebar.title.text="预约订单"
            if(adapterAdv==null){
                adapterAdv= AdvanceOrderAdapter(moreList,context)
                listView.adapter=adapterAdv
            }
           initAdvData()
        }
    }

    private fun initAdvData(){
        var con:ContentValues= ContentValues()
        con.put("advSim","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con.put("advName","三人行概念店")
        con.put("advType","等待用餐")
        con.put("advTime","2017-8-8")
        con.put("advData","22")
        con.put("advDetails","鸡蛋等三件商品")
        var con1:ContentValues= ContentValues()
        con1.put("advSim","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con1.put("advName","三人行概念店")
        con1.put("advType","订单已完成")
        con1.put("advTime","2017-8-8")
        con1.put("advData","222.00")
        con1.put("advDetails","鸡蛋等三件商品")
        moreList.add(con1)
        moreList.add(con)
        adapterAdv!!.notifyDataSetChanged()
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
