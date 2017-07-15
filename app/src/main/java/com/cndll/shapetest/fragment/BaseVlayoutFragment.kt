package com.cndll.shapetest.fragment

import `in`.srain.cube.views.ptr.PtrClassicDefaultHeader
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager

import com.cndll.shapetest.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BaseVlayoutFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BaseVlayoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class BaseVlayoutFragment : Fragment() {

    var isFirstRecycler = true
    var offsetX = 0
    val adapterList = ArrayList<DelegateAdapter.Adapter<out RecyclerView.ViewHolder>>()
    lateinit var adapter: DelegateAdapter
    lateinit var recycler: RecyclerView

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
        recycler = view!!.findViewById(R.id.recycler) as RecyclerView
        val vlayout = VirtualLayoutManager(context)
        recycler.layoutManager = vlayout
        adapter = DelegateAdapter(vlayout, true)
        recycler.adapter = adapter
        initDownRefresh(view)
        setScollListen(view)
        setVLayout()
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event

    fun initDownRefresh(view: View) {
        val storeHousePtrFrame: PtrFrameLayout = view.findViewById(R.id.store_house_ptr_frame) as PtrFrameLayout
        storeHousePtrFrame.disableWhenHorizontalMove(true)
        storeHousePtrFrame.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout) {
                frame.postDelayed({
                    updataRecycle()
                    storeHousePtrFrame.refreshComplete()
                }, 1800)
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
                // 默认实现，根据实际情况做改动
                return isFirstRecycler
                // return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header)
            }
        })
        val header = PtrClassicDefaultHeader(context)
        header.setPadding(0, PtrLocalDisplay.dp2px(15F), 0, 0)
        storeHousePtrFrame.headerView = header
        storeHousePtrFrame.addPtrUIHandler(header)
    }

    open var canLoad = true
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
                        loadMore()
                    }
                }
            }
        })
    }

    open fun gotoFirstItem() {
        if (adapter.itemCount > 12)
            recycler.scrollToPosition(12)
        recycler.smoothScrollToPosition(0)
    }

    open fun updataRecycle() {

    }

    open fun loadMore() {

    }

    open fun setVLayout() {

    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */

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
         * @return A new instance of fragment BaseVlayoutFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): BaseVlayoutFragment {
            val fragment = BaseVlayoutFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
