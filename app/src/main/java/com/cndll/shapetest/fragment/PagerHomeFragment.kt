package com.cndll.shapetest.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.DelegateAdapter.Adapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.BannerAdapter
import com.cndll.shapetest.weight.VLayoutHelper
import com.facebook.drawee.view.SimpleDraweeView
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [PagerHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagerHomeFragment : Fragment() {

    var isFirstRecycler = true
    var offsetX = 0
    val adapterList = ArrayList<Adapter<out RecyclerView.ViewHolder>>()
    lateinit var adapter: DelegateAdapter
    lateinit var bannerAdapter: BannerAdapter
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater?.inflate(R.layout.fragment_pager_home, container, false) as View
        setScollListen(view)
        setFirstViewPage(view)
        return view
    }

    fun setFirstViewPage(view: View) {
        val vlayout = VirtualLayoutManager(context)
        val recycler: RecyclerView = view!!.findViewById(R.id.recycler) as RecyclerView

        recycler.layoutManager = vlayout
        adapter = DelegateAdapter(vlayout, true)
        recycler.adapter = adapter
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val llh = LinearLayoutHelper()
        bannerAdapter = BannerAdapter(context, llh, 1, layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, windowManager.defaultDisplay.height / 2))
        adapter.addAdapter(bannerAdapter)

        val mLayoutParams = ViewGroup.LayoutParams(windowManager.defaultDisplay.width / 6, windowManager.defaultDisplay.height / 8)
        val mScrollFixLayoutHelper = ScrollFixLayoutHelper(ScrollFixLayoutHelper.BOTTOM_RIGHT, 12, 12)
        mScrollFixLayoutHelper.setItemCount(1)
        mScrollFixLayoutHelper.showType = ScrollFixLayoutHelper.SHOW_ON_LEAVE
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setLayoutHelper(mScrollFixLayoutHelper).
                setParams(mLayoutParams).setViewType(1).setCount(1).setRes(R.layout.button_vlayout).
                creatAdapter())

        val grid = GridLayoutHelper(2, 2)
        grid.setPadding(0, 12, 0, 12)
        grid.setMargin(0, 6, 0, 6)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(4).
                setLayoutHelper(grid).
                setViewType(2).
                setRes(R.layout.nearby_item).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 13 * 2)).
                setOnBindView({ itemView, position ->
                    val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                    imageView.setOnClickListener { Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show() }
                }).creatAdapter())

    }

    fun setScollListen(view: View) {
        val recycler = view!!.findViewById(R.id.recycler) as RecyclerView
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

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        bannerAdapter.view.stopBanner()
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
}// Required empty public constructor
