package com.cndll.shapetest.fragment


import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.BannerAdapter
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.databinding.ItemNearbyBinding
import com.cndll.shapetest.weight.VLayoutHelper
import com.umeng.socialize.utils.Log


/**
 * A simple [Fragment] subclass.
 * Use the [PagerHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagerHomeFragment : BaseVlayoutFragment() {


    lateinit var bannerAdapter: BannerAdapter

    override fun setVLayout() {

        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val llh = LinearLayoutHelper()
        bannerAdapter = BannerAdapter(context, llh, 1, layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, windowManager.defaultDisplay.height / 2))
        adapter.addAdapter(bannerAdapter)

        val mLayoutParams = ViewGroup.LayoutParams(windowManager.defaultDisplay.height / 12, windowManager.defaultDisplay.height / 12)
        val mScrollFixLayoutHelper = ScrollFixLayoutHelper(ScrollFixLayoutHelper.BOTTOM_RIGHT, 12, 12)
        mScrollFixLayoutHelper.setItemCount(1)
        mScrollFixLayoutHelper.showType = ScrollFixLayoutHelper.SHOW_ON_LEAVE
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setLayoutHelper(mScrollFixLayoutHelper).
                setParams(mLayoutParams).
                setViewType(1).
                setCount(1).
                setRes(R.layout.button_vlayout).setOnBindView({ itemView, position ->
            val button = itemView.findViewById(R.id.back_top)
            button.setOnClickListener { gotoFirstItem() }
        }).
                creatAdapter())


        val grid = GridLayoutHelper(2, 2)
        grid.setPadding(0, 12, 0, 12)
        grid.setMargin(0, 6, 0, 6)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(4).
                setLayoutHelper(grid).
                setViewType(2).
                setRes(R.layout.item_nearby).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 13 * 2)).
                setOnBindView({ itemView, position ->
                    val binding: ItemNearbyBinding = DataBindingUtil.bind(itemView)
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                    binding.image.setOnClickListener { Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show() }
                }).creatAdapter())

        val linear = LinearLayoutHelper()
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(4).
                setLayoutHelper(linear).
                setViewType(3).
                setRes(R.layout.item_home_commodity).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 3)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())

    }


    override fun loadMore() {
        super.loadMore()
        if (!canLoad) {
            return
        }
        val linear = LinearLayoutHelper()
        Log.d("COUNT", (adapter.findAdapterByIndex(3) as VLayoutAdapter).mCount.toString())
        if ((adapter.findAdapterByIndex(3) as VLayoutAdapter).mCount >= 12) {
            adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                    setContext(context).
                    setCount(1).
                    setLayoutHelper(linear).
                    setViewType(1).
                    setRes(R.layout.button_vlayout).

                    setOnBindView({ itemView, position ->
                        // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                    }).creatAdapter())
            canLoad = false
            return
        }
        (adapter.findAdapterByIndex(3) as VLayoutAdapter).mCount = (adapter.findAdapterByIndex(3) as VLayoutAdapter).mCount + 3
        (adapter.findAdapterByIndex(3) as VLayoutAdapter).notifyDataSetChanged()
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
