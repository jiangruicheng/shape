package com.cndll.shapetest.fragment


import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TabHost
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ItemLimitCommodityBinding
import com.cndll.shapetest.databinding.ItemLimitTabBinding
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [LimitedSpikeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LimitedSpikeFragment : BaseVlayoutFragment() {

    lateinit var onGesture: GestureDetector.OnGestureListener
    lateinit var gesture: GestureDetector
    var selectPosition = 0
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


    override fun setVLayout() {
        onGesture = object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return false
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return false

            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                Toast.makeText(context, "yidong", Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                return false

            }

            override fun onLongPress(e: MotionEvent?) {

            }

        }
        gesture = GestureDetector(onGesture)
        recycler.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }

            override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
                return gesture.onTouchEvent(e)
                /*if (e!!.action == MotionEvent.ACTION_HOVER_MOVE) {
                    Log.d("123", "123")
                }
                return false*/
            }

            override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {

            }

        })
        super.setVLayout()
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(2).
                setRes(R.layout.item_limited_tablayout).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 15)).
                setOnBindView({ itemView, position ->
                    val tabLayout: TabLayout = itemView.itemView.findViewById(R.id.tab) as TabLayout
                    //tabLayout.newTab().setText("1233")
                    val p = selectPosition
                    tabLayout.removeAllTabs()
                    for (i in 0..5) {
                        val tab: TabLayout.Tab = tabLayout.newTab()
                        val tabView = LayoutInflater.from(context).inflate(R.layout.item_limit_tab, tabLayout as ViewGroup, false)
                        val tabViewBinding = ItemLimitTabBinding.bind(tabView)
                        tab.setCustomView(tabView)


                        tabViewBinding.time.text = i.toString() + ":00"
                        tabViewBinding.status.text = "正在开枪"
                        tabLayout.addTab(tab)
                        if (i == p) {
                            (tabViewBinding.group?.parent as View).setBackgroundColor(Color.rgb(0xf0, 0x39, 0x3c))
                            tabViewBinding.group.setBackgroundColor(Color.rgb(0xf0, 0x39, 0x3c))
                            tabViewBinding.time.setTextColor(Color.WHITE)
                            tabViewBinding.status.setTextColor(Color.WHITE)
                        } else {
                            (tabViewBinding.group?.parent as View).setBackgroundColor(Color.rgb(0x28, 0x28, 0x28))
                        }
                    }
                    Toast.makeText(context, p.toString(), Toast.LENGTH_SHORT).show()
                    tabLayout.getTabAt(p)?.select()
                    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabReselected(tab: TabLayout.Tab?) {
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {
                            val group = tab!!.customView?.findViewById(R.id.group)
                            val time = tab.customView?.findViewById(R.id.time) as TextView
                            val status = tab.customView?.findViewById(R.id.status) as TextView
                            (group?.parent as View).setBackgroundColor(Color.rgb(0x28, 0x28, 0x28))
                            group.setBackgroundColor(Color.rgb(0x28, 0x28, 0x28))
                            time.setTextColor(Color.GRAY)
                            status.setTextColor(Color.GRAY)
                        }

                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            selectPosition = tab!!.position
                            val group = tab.customView?.findViewById(R.id.group)
                            val time = tab.customView?.findViewById(R.id.time) as TextView
                            val status = tab.customView?.findViewById(R.id.status) as TextView
                            (group?.parent as View).setBackgroundColor(Color.rgb(0xf0, 0x39, 0x3c))
                            group.setBackgroundColor(Color.rgb(0xf0, 0x39, 0x3c))
                            time.setTextColor(Color.WHITE)
                            status.setTextColor(Color.WHITE)

                        }
                    })
                }).creatAdapter())
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(14).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(3).
                setRes(R.layout.item_limit_commodity).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 4)).
                setOnBindView({ itemView, position ->
                    val commodity = itemView.dataBinding as ItemLimitCommodityBinding
                    commodity.info.button.visibility = View.VISIBLE
                    commodity.info.limitTime.visibility = View.VISIBLE
                    commodity.info.button.text = (position.toString())
                    commodity.info.button.setTextColor(Color.WHITE)

                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        val FLAG = "秒杀"
        val TITLE = ""
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment LimitedSpikeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): LimitedSpikeFragment {
            val fragment = LimitedSpikeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
