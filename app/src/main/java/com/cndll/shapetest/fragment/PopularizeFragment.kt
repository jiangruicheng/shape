package com.cndll.shapetest.fragment

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.PopularizeAdapter
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.UserInfoResponse
import com.cndll.shapetest.databinding.FragmentPopularizeBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import com.cndll.shapetest.zixing.encode.CodeCreator
import com.handmark.pulltorefresh.library.PullToRefreshBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2017/7/10 0010.
 */
class PopularizeFragment : BaseFragment<FragmentPopularizeBinding>(){

    var moerList=ArrayList<UserInfoResponse.DatasBean.MyRelationBean>()
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
        httpUserInfo()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 加载视图
     * */
    private fun initView(){
        if(mNumber==1){
            //用户推光
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
        binding.popularPull.mode=PullToRefreshBase.Mode.DISABLED
        binding.popularPull.getLoadingLayoutProxy(false,true).setRefreshingLabel("刷新中")
        binding.popularPull.getLoadingLayoutProxy(false,true).setReleaseLabel("释放刷新")
        listView=binding.popularPull.refreshableView
        listView.dividerHeight=2
        if (adapter == null){
            adapter = PopularizeAdapter(context,moerList)
            listView.adapter=adapter
        }

    }

    internal var onListener2:PullToRefreshBase.OnRefreshListener2<ListView> = object : PullToRefreshBase.OnRefreshListener2<ListView>{
        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.popularPull.onRefreshComplete()
        }

        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            binding.popularPull.onRefreshComplete()
        }
    }

    /**
     * 会员信息
     * */
    private fun httpUserInfo(){
        AppRequest.getAPI().userInfo("member_info","index", SharedPreferenceUtil.read("key","")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable(){
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as UserInfoResponse
                if(t.code==200){
                    if(mNumber==1){
                        val bitmap = CodeCreator.createQRCode(t.datas.download_url)
                        binding.popularImage.setImageBitmap(bitmap)
                        binding.popuCode.text=t.datas.member_code
                    }else if(mNumber==2){
                        moerList.addAll(t.datas.my_relation)
                        adapter!!.notifyDataSetChanged()
                    }
                }else{
                    Toast.makeText(context,t.error_massage, Toast.LENGTH_LONG).show()
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