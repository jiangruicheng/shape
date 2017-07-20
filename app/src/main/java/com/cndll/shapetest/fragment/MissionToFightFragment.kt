package com.cndll.shapetest.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.vlayout.layout.LinearLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [MissionToFightFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MissionToFightFragment : BaseVlayoutFragment() {


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

    override fun updataRecycle() {
        super.updataRecycle()
    }

    override fun loadMore(): Boolean {
        return super.loadMore()
    }

    override fun setVLayout() {
        super.setVLayout()
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(4).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(4).
                setRes(R.layout.item_missiontofight).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 7 * 3)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        open val FLAG = "拼团"
        open val TITLE = ""
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment MissionToFightFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MissionToFightFragment {
            val fragment = MissionToFightFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
