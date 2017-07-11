package com.cndll.shapetest.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.ArrayMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.DelegateAdapter.Adapter
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.BannerAdapter
import com.cndll.shapetest.weight.MenuGrid
import kotlinx.android.synthetic.main.fragment_pager_home.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [PagerHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagerHomeFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    var isFirstRecycler = true
    var offsetX = 0
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_pager_home, container, false)
        return view
    }

    lateinit var adapter: DelegateAdapter
    override fun onResume() {
        super.onResume()
        val vlayout = VirtualLayoutManager(activity)
        recycler.layoutManager = vlayout
        adapter = DelegateAdapter(vlayout, true)
        recycler.adapter = adapter
        val adapterList = ArrayList<Adapter<*>>()

        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val h = windowManager.defaultDisplay.height / 8
        adapterList.add(BannerAdapter(context, LinearLayoutHelper(), 5))

        val mLayoutParams = ViewGroup.LayoutParams(windowManager.defaultDisplay.width / 6, windowManager.defaultDisplay.height / 8)

        val mScrollFixLayoutHelper = ScrollFixLayoutHelper(ScrollFixLayoutHelper.BOTTOM_RIGHT, 12, 12)
        mScrollFixLayoutHelper.setItemCount(1)
        mScrollFixLayoutHelper.showType = ScrollFixLayoutHelper.SHOW_ON_LEAVE
        adapterList.add(object : BannerAdapter(context, mScrollFixLayoutHelper, 1, mLayoutParams) {
            override fun onBindViewHolder(holder: BannerViewHolder?, position: Int) {
            }

            override fun getItemCount(): Int {
                return 1
            }

            override fun getItemViewType(position: Int): Int {
                return 1
            }

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
                val view = LayoutInflater.from(context).inflate(R.layout.button_vlayout, parent, false)
                return BannerViewHolder(view)
            }

            override fun onCreateLayoutHelper(): LayoutHelper {
                return mScrollFixLayoutHelper
            }
        })


        adapterList.add(BannerAdapter(context, LinearLayoutHelper(), 12))

        adapter.addAdapters(adapterList)

        recycler.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            val isSlidingToLast = false
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                offsetX = offsetX + dy
                if (offsetX > 0) {
                    isFirstRecycler = false
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val manager = recyclerView!!.getLayoutManager() as LinearLayoutManager

                if (newState === RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    val lastVisibleItem = manager.findLastCompletelyVisibleItemPosition()
                    val totalItemCount = manager.getItemCount()
                    isFirstRecycler = manager.findFirstCompletelyVisibleItemPosition() == 0
                    if (isFirstRecycler) {
                        offsetX = 0
                        //back_top.visibility = View.GONE
                    }
                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == totalItemCount - 1 /*&& isSlidingToLast*/) {
                        //加载更多功能的代码

                    }
                }
            }
        })
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment PagerHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): PagerHomeFragment {
            val fragment = PagerHomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ItemView>() {
        override fun onBindViewHolder(p0: RecyclerAdapter.ItemView?, p1: Int) {
        }

        var count = 20
        lateinit var mitems: ArrayMap<String, Objects>
        override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): RecyclerAdapter.ItemView {
            //val view = LayoutInflater.from(p0?.context).inflate(R.layout.testitem, p0, false)
            val bean1 = MenuGrid.MenuBean()
            bean1.title = "美妆"
            val bean2 = MenuGrid.MenuBean()
            bean2.title = "家电"
            val bean3 = MenuGrid.MenuBean()
            bean3.title = "美女"
            val dataList = listOf<MenuGrid.MenuBean>(bean1, bean2, bean3)

            val view = MenuGrid(p0)
            val params = view.view.layoutParams
            val windowManager = p0?.context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            params.height = windowManager.defaultDisplay.height / 2
            view.setMenuData(dataList)
            val bannerBeans = ArrayList<MenuGrid.BannerBean>()
            bannerBeans.add(MenuGrid.BannerBean("http://zhongxiang.51edn.com/data/upload/shop/goods_class/05513824560521848.jpg", "http:www.baidu.com"))
            bannerBeans.add(MenuGrid.BannerBean("http://zhongxiang.51edn.com/data/upload/shop/goods_class/05513824560521848.jpg", "http:www.baidu.com"))
            bannerBeans.add(MenuGrid.BannerBean("http://zhongxiang.51edn.com/data/upload/shop/goods_class/05513824560521848.jpg", "http:www.baidu.com"))
            view.setBanner(bannerBeans)
            return ItemView(view.view)
        }

        override fun getItemCount(): Int {
            return count
        }

        override fun getItemId(position: Int): Long {
            return super.getItemId(position)
        }

        override fun getItemViewType(position: Int): Int {
            return super.getItemViewType(position)
        }

        inner class ItemView(view: View) : RecyclerView.ViewHolder(view) {
        }

    }
}// Required empty public constructor
