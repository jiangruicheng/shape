package com.cndll.shapetest.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.layout.LinearLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.weight.VLayoutHelper

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LineaOffShopFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LineaOffShopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LineaOffShopFragment : BaseVlayoutFragment() {

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

    override fun init() {
        super.init()
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
                setViewType(3).
                setRes(R.layout.item_lineoff_shop).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5 * 2)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        val FLAG = "合伙人线下体验店"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment LineaOffShopFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): LineaOffShopFragment {
            val fragment = LineaOffShopFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    // TODO: Rename method, update argument and hook method into UI event


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */

}// Required empty public constructor
