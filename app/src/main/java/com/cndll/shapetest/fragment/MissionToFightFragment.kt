package com.cndll.shapetest.fragment


import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.vlayout.layout.LinearLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.FightResponse
import com.cndll.shapetest.databinding.ItemMissiontofightBinding
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [MissionToFightFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MissionToFightFragment : BaseVlayoutFragment() {

    val FightMode: ArrayList<FightResponse.DatasBean> = ArrayList()
    lateinit var itemAdapter: VLayoutAdapter
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
        itemAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(FightMode.size).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(4).
                setRes(R.layout.item_missiontofight).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 7 * 3)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemMissiontofightBinding
                    binding.item = FightMode[position]
                    binding.image.setImageURI(FightMode[position].img_url)
                    binding.signPrice.paint.isAntiAlias = true
                    binding.signPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG

                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter()
        pullData(MODE_PULL)
    }

    override fun pullData(mode: Int): Boolean {
        if (mode == MODE_PULL) {
            page = 1
        }
        ApiUtill.getInstance().getApi(AppRequest.getAPI().fightPage(page.toString()), {
            baseResponse ->
            when (mode) {
                (MODE_PULL) -> {
                    FightMode.clear()
                    FightMode.addAll((baseResponse as FightResponse).datas)
                    itemAdapter.mCount = FightMode.size
                    itemAdapter.notifyDataSetChanged()
                    page++
                    pullData(MODE_LOADMORE)

                }
                (MODE_LOADMORE) -> {
                    if ((baseResponse as FightResponse).datas.isEmpty()) {
                        loadOver()
                    }
                    FightMode.addAll((baseResponse as FightResponse).datas)
                    itemAdapter.mCount = FightMode.size
                    itemAdapter.notifyDataSetChanged()
                    loading = false
                    page++
                }
            }
        })
        return true
    }

    override fun setVLayout() {
        super.setVLayout()
        adapter.addAdapter(itemAdapter)
    }


    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        open val FLAG = "拼团"
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
