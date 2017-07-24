package com.cndll.shapetest.fragment

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.PopularizeAdapter
import com.cndll.shapetest.databinding.FragmentPopularizeBinding
import com.cndll.shapetest.zixing.encode.CodeCreator
import com.handmark.pulltorefresh.library.PullToRefreshBase

/**
 * Created by Administrator on 2017/7/10 0010.
 */
class PopularizeFragment : BaseFragment<FragmentPopularizeBinding>(){

    var moerList=ArrayList<ContentValues>()
    var adapter:PopularizeAdapter?=null
    lateinit var listView:ListView

    var mNumber:Int?=null
    fun newInstance(number: Int): Fragment {
        var bundle=Bundle()
        bundle.putInt("type",number)
        val fragment = PopularizeFragment()
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
        initBinding(R.layout.fragment_popularize,container)
        mView=binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 加载视图
     * */
    private fun initView(){
        if(mNumber==1){
            //用户推光
            val bitmap = CodeCreator.createQRCode("123456")
            binding.popularImage.setImageBitmap(bitmap)
            binding.linPopularCode.visibility=View.VISIBLE
            binding.linPopularData.visibility=View.GONE
        }else if(mNumber==2){
            //与我关联
            binding.linPopularCode.visibility=View.GONE
            binding.linPopularData.visibility=View.VISIBLE
            initListView()
        }
    }

    //加载刷新
    private fun initListView(){
        binding.popularPull.setOnRefreshListener(onListener2)
        binding.popularPull.getLoadingLayoutProxy(false,true).setPullLabel("上蜡烛")
        binding.popularPull.mode=PullToRefreshBase.Mode.BOTH
        binding.popularPull.getLoadingLayoutProxy(false,true).setRefreshingLabel("刷新中")
        binding.popularPull.getLoadingLayoutProxy(false,true).setReleaseLabel("释放刷新")
        listView=binding.popularPull.refreshableView
        listView.dividerHeight=2
        if (adapter == null){
            adapter = PopularizeAdapter(context,moerList)
            listView.adapter=adapter
        }
        initData()
    }

    //测试数据
    private fun initData(){
        var con:ContentValues = ContentValues()
        con.put("img","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con.put("name","miceahle")
        con.put("time","2017-8-9")
        var con1:ContentValues = ContentValues()
        con1.put("img","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con1.put("name","miceahle")
        con1.put("time","2017-8-9")
        var con2:ContentValues = ContentValues()
        con2.put("img","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        con2.put("name","miceahle")
        con2.put("time","2017-8-9")
        moerList.add(con)
        moerList.add(con1)
        moerList.add(con2)
        adapter!!.notifyDataSetChanged()
    }

    internal var onListener2:PullToRefreshBase.OnRefreshListener2<ListView> = object : PullToRefreshBase.OnRefreshListener2<ListView>{
        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.popularPull.onRefreshComplete()
        }

        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.popularPull.onRefreshComplete()
        }
    }

}