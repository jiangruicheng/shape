package com.cndll.shapetest.fragment

import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.CollectGoodsAdapter
import com.cndll.shapetest.adapter.ShopListAdapter
import com.cndll.shapetest.databinding.FavoriteFragmentBinding
import com.handmark.pulltorefresh.library.PullToRefreshBase

/**
 * Created by Administrator on 2017/7/10 0010.
 */
class FavoriteFragmnet : BaseFragment<FavoriteFragmentBinding>() {
    var moerList = ArrayList<ContentValues>()
    var adapterCollect: CollectGoodsAdapter? = null
    lateinit var listView: ListView
    var adapterShop: ShopListAdapter? = null
    override fun initBindingVar() {
    }

    var mNumber: Int? = null
    fun newInstance(number: Int): Fragment {
        var bundle = Bundle()
        bundle.putInt("type", number)
        var fragment = FavoriteFragmnet()
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNumber = arguments.getInt("type")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.favorite_fragment, container)
        mView = binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initView() {
        binding.favPull.setOnRefreshListener(onListener2)
        binding.favPull.getLoadingLayoutProxy(false, true).setPullLabel("上蜡烛")
        binding.favPull.mode = PullToRefreshBase.Mode.BOTH
        binding.favPull.getLoadingLayoutProxy(false, true).setRefreshingLabel("刷新中")
        binding.favPull.getLoadingLayoutProxy(false, true).setReleaseLabel("释放刷新")
        listView = binding.favPull.refreshableView
        listView.divider = resources.getDrawable(R.color.gray)
        listView.dividerHeight = 6
        if (mNumber == 1) {
            //商品
            if (adapterCollect == null) {
                adapterCollect = CollectGoodsAdapter(moerList, context)
                listView.adapter = adapterCollect
            }
            initData()
        } else if (mNumber == 2) {
            //店铺
            if (adapterShop == null){
                adapterShop= ShopListAdapter(moerList,context)
                listView.adapter=adapterShop
            }
            initShopData()
        }

    }


    //测试数据
    private fun initShopData() {
        var con: ContentValues = ContentValues()
        con.put("shopSim", "http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con.put("shopName", "miceahle")
        con.put("shopAccount", "小玲1002件")
        con.put("shopAllAccount", "小玲1002件")
        var con1: ContentValues = ContentValues()
        con1.put("shopSim", "http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con1.put("shopName", "miceahle")
        con1.put("shopAccount", "小玲1002件")
        con1.put("shopAllAccount", "小玲1002件")
        var con2: ContentValues = ContentValues()
        con2.put("shopSim", "http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con2.put("shopName", "miceahle")
        con2.put("shopAccount", "小玲1002件")
        con2.put("shopAllAccount", "小玲1002件")
        moerList.add(con)
        moerList.add(con1)
        moerList.add(con2)
        adapterShop!!.notifyDataSetChanged()
    }


    //测试数据
    private fun initData() {
        var con: ContentValues = ContentValues()
        con.put("goodsSim", "http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con.put("goodsName", "miceahle")
        con.put("goodsNewPrice", "11")
        con.put("goodsOldPrice", "22")
        var con1: ContentValues = ContentValues()
        con1.put("goodsSim", "http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con1.put("goodsName", "miceahle")
        con1.put("goodsNewPrice", "11")
        con1.put("goodsOldPrice", "22")
        var con2: ContentValues = ContentValues()
        con2.put("goodsSim", "http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con2.put("goodsName", "miceahle")
        con2.put("goodsNewPrice", "20")
        con2.put("goodsOldPrice", "25")
        moerList.add(con)
        moerList.add(con1)
        moerList.add(con2)
        adapterCollect!!.notifyDataSetChanged()
    }

    internal var onListener2: PullToRefreshBase.OnRefreshListener2<ListView> = object : PullToRefreshBase.OnRefreshListener2<ListView> {
        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.favPull.onRefreshComplete()
        }

        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.favPull.onRefreshComplete()
        }
    }


}