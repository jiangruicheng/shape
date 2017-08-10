package com.cndll.shapetest.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.HomePageResponse
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
        binding.titlebar.root.setBackgroundResource(R.color.titleRed)
        binding.titlebar.title.text = "众享消费"
        binding.titlebar.title.setTextColor(Color.WHITE)
        binding.titlebar.back.visibility = View.INVISIBLE
        binding.titlebar.menu.visibility = View.GONE

        ApiUtill.getInstance().getApi(AppRequest.getAPI().homePage(/*"index",*/"1", "1"), {
            baseResponse ->
            baseResponse as HomePageResponse
            adapter.setData(baseResponse.datas.tag)
            Toast.makeText(context, (baseResponse as HomePageResponse).code.toString(), Toast.LENGTH_SHORT).show()

        })
        return super.onCreateView(inflater, container, savedInstanceState)
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
        private lateinit var fragmentlist: ArrayList<Fragment>
        override fun getItem(p0: Int): Fragment {
            return fragmentlist[p0]
        }

        open fun setData(list: List<HomePageResponse.DatasBean.TagBean>) {
            fragmentlist = ArrayList()
            binding.viewPage.offscreenPageLimit = list.size
            for (i in 0..list.size - 1) {
                this.list.add(list[i].gc_name)
                val fragment = PagerHomeFragment.newInstance("", "")
                val bundle = Bundle()
                bundle.putString("gc_id", list[i].gc_id)
                fragment.arguments = bundle
                fragmentlist.add(fragment)
            }
            notifyDataSetChanged()
        }

        open var list = arrayListOf<String>()

        override fun getCount(): Int {
            return list.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return list[position]
        }
    }
}// Required empty public constructor
