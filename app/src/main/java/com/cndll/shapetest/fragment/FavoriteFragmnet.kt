package com.cndll.shapetest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.CollectGoodsAdapter
import com.cndll.shapetest.adapter.ShopListAdapter
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.CollectResponse
import com.cndll.shapetest.databinding.FavoriteFragmentBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import com.handmark.pulltorefresh.library.PullToRefreshBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2017/7/10 0010.
 */
class FavoriteFragmnet : BaseFragment<FavoriteFragmentBinding>() {
    var adapterCollect: CollectGoodsAdapter? = null
    lateinit var listView: ListView
    var adapterShop: ShopListAdapter? = null
    var page: Int = 1
    var collectGoods = ArrayList<CollectResponse.DatasBean.FavoritesListBean>()
    var cahGoods = ArrayList<CollectResponse.DatasBean.FavoritesListBean>()

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

    /**
     * 加载控件
     * */
    private fun initView() {
        binding.favPull.setOnRefreshListener(onListener2)
        binding.favPull.getLoadingLayoutProxy(false, true).setPullLabel("上拉中")
        binding.favPull.mode = PullToRefreshBase.Mode.BOTH
        binding.favPull.getLoadingLayoutProxy(false, true).setRefreshingLabel("刷新中")
        binding.favPull.getLoadingLayoutProxy(false, true).setReleaseLabel("释放刷新")
        listView = binding.favPull.refreshableView
        listView.divider = resources.getDrawable(R.color.gray)
        listView.dividerHeight = 6
        if (mNumber == 1) {
            //商品
            if (adapterCollect == null) {
                adapterCollect = CollectGoodsAdapter(collectGoods, context)
                listView.adapter = adapterCollect
            }
            collectGoods()
        } else if (mNumber == 2) {
            //店铺
            if (adapterShop == null) {
                adapterShop = ShopListAdapter(collectGoods, context)
                listView.adapter = adapterShop
            }
            collectShop()
        }

    }

    /**
     * 刷新数据
     * */
    internal var onListener2: PullToRefreshBase.OnRefreshListener2<ListView> = object : PullToRefreshBase.OnRefreshListener2<ListView> {
        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            if (cahGoods != null && cahGoods.size > 0) {
                cahGoods.clear()
            }
            page = page + 1
            if (mNumber == 1) {
                collectGoods()
            } else if (mNumber == 2) {
                collectShop()
            }

        }

        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            if (cahGoods != null && cahGoods.size > 0) {
                cahGoods.clear()
            }
            if (collectGoods != null && collectGoods.size > 0) {
                collectGoods.clear()
            }
            page = 1
            if (mNumber == 1) {
                collectGoods()
            } else if (mNumber == 2) {
                collectShop()
            }
        }
    }


    /**
     * 收藏商品
     * */
    private fun collectGoods() {
        AppRequest.getAPI().collectGoods("member_favorites", "favorites_list", SharedPreferenceUtil.read("key", ""), page.toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as CollectResponse
                if (t.code == 200) {
                    cahGoods.addAll(t.datas.favorites_list)
                    collectGoods.addAll(cahGoods)
                    adapterCollect!!.notifyDataSetChanged()
                    binding.favPull.onRefreshComplete()
                    if (t.datas.favorites_list.size <= 0) {
                        binding.favPull.mode = PullToRefreshBase.Mode.PULL_FROM_START
                    } else {
                        binding.favPull.mode = PullToRefreshBase.Mode.BOTH
                    }
                } else {
                    Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /**
     * 收藏店铺
     * */
    private fun collectShop() {
        AppRequest.getAPI().collectGoods("member_favorites_store", "favorites_list", SharedPreferenceUtil.read("key", ""), page.toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as CollectResponse
                if (t.code == 200) {
                    cahGoods.addAll(t.datas.favorites_list)
                    collectGoods.addAll(cahGoods)
                    adapterShop!!.notifyDataSetChanged()
                    binding.favPull.onRefreshComplete()
                    if (t.datas.favorites_list.size <= 0) {
                        binding.favPull.mode = PullToRefreshBase.Mode.PULL_FROM_START
                    } else {
                        binding.favPull.mode = PullToRefreshBase.Mode.BOTH
                    }
                } else {
                    Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}