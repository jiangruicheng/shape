package com.cndll.shapetest.fragment

import `in`.srain.cube.views.ptr.PtrClassicDefaultHeader
import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class
HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initBindingVar() {
    }

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    private lateinit var adapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        initBinding(R.layout.fragment_home, container)
        mView = binding.root
        initPageView()
        initDownRefresh()

        binding.titlebar.root.setBackgroundResource(R.color.titleRed)
        binding.titlebar.title.text = "众享消费"
        binding.titlebar.title.setTextColor(Color.WHITE)
        binding.titlebar.back.visibility = View.GONE
        binding.titlebar.menu.visibility = View.GONE
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun initDownRefresh() {
        binding.storeHousePtrFrame.disableWhenHorizontalMove(true)
        binding.storeHousePtrFrame.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout) {
                frame.postDelayed({ binding.storeHousePtrFrame.refreshComplete() }, 1800)
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
                // 默认实现，根据实际情况做改动
                return (adapter.getItem(binding.viewPage.currentItem) as PagerHomeFragment).offsetX <= 0
                // return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header)
            }
        })
        val header = PtrClassicDefaultHeader(context)
        header.setPadding(0, PtrLocalDisplay.dp2px(15F), 0, 0)
        binding.storeHousePtrFrame.headerView = header
        binding.storeHousePtrFrame.addPtrUIHandler(header)
    }

    fun initPageView() {
        adapter = PagerAdapter(activity.supportFragmentManager)
        binding.pageTab.tabMode = TabLayout.MODE_SCROLLABLE
        binding.pageTab.setupWithViewPager(binding.viewPage)
        binding.viewPage.adapter = adapter
        binding.pageTab.setupWithViewPager(binding.viewPage)

    }

    fun updatePageView(list: ArrayList<String>) {
        adapter.list = list
        adapter.notifyDataSetChanged()
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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private var fragmentlist: ArrayList<Fragment>
        override fun getItem(p0: Int): Fragment {
            return fragmentlist[p0]
        }

        open var list = arrayListOf<String>()

        init {
            fragmentlist = ArrayList()
            list.add("首页")
            list.add("农产")
            list.add("美食")
            list.add("水果")
            list.add("家居")
            list.add("电器")
            list.add("美妆")
            list.add("海淘")

            for (i in list) {
                fragmentlist.add(PagerHomeFragment.newInstance("", ""))
            }
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return list[position]
        }
    }
}// Required empty public constructor
