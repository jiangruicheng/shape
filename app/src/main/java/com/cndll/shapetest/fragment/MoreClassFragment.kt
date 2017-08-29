package com.cndll.shapetest.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.vlayout.layout.GridLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.activity.ResultActivity
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.MoreClassResponse
import com.cndll.shapetest.databinding.ItemCommodityVerBinding
import com.cndll.shapetest.weight.VLayoutHelper
import com.facebook.drawee.view.SimpleDraweeView


/**
 * A simple [Fragment] subclass.
 * Use the [MoreClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoreClassFragment : BaseVlayoutFragment() {

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

    lateinit var classAdapter: VLayoutAdapter
    val classMode = ArrayList<MoreClassResponse.DatasBean.SonArrayBean>()
    override fun init() {
        super.init()
        pullData(MODE_PULL)
        val detailHelper = GridLayoutHelper(4)
        detailHelper.setAutoExpand(false)
        classAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(classMode.size).
                setLayoutHelper(detailHelper).
                setViewType(3).
                setRes(R.layout.menubutton).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5)).
                setOnBindView({ itemView, position ->
                    (itemView.itemView.findViewById(R.id.image) as SimpleDraweeView)
                    itemView.itemView.setOnClickListener {
                        context.startActivity(Intent(context, ResultActivity::class.java).
                                putExtra(ResultActivity.MODE, ResultActivity.MODE_CLASS).
                                putExtra(ResultActivity.TYPE, ResultActivity.TYPE_COMMODIYT).
                                putExtra(ResultFragment.ID, classMode[position].gc_id).
                                putExtra(ResultActivity.TITLE, classMode[position].son_name))
                    }
                    (itemView.itemView.findViewById(R.id.image) as SimpleDraweeView).setImageURI(classMode[position].img_url)
                    (itemView.itemView.findViewById(R.id.text) as TextView).setText(classMode[position].son_name)
                }).creatAdapter()
    }

    override fun setVLayout() {
        super.setVLayout()
        adapter.addAdapter(classAdapter)
    }

    override fun pullData(mode: Int): Boolean {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().moreClassPage(arguments.getString(FLAG)), {
            baseResponse ->
            when (mode) {
                (MODE_PULL) -> {
                    baseResponse as MoreClassResponse
                    if (classMode.isNotEmpty()) {
                        classMode.clear()
                    }
                    if (baseResponse.datas.size == 1) {
                        classMode.addAll(baseResponse.datas[0].son_array)
                    }
                    classAdapter.mCount = classMode.size
                    classAdapter.notifyDataSetChanged()
                }
            }
        })

        return true
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        val FLAG = "更多分类"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment MoreClassFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MoreClassFragment {
            val fragment = MoreClassFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
