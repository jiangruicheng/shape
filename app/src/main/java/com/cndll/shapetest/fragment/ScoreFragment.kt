package com.cndll.shapetest.fragment


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
import com.cndll.shapetest.api.bean.response.ScoreResponse
import com.cndll.shapetest.bean.CommodityVerInfoMode
import com.cndll.shapetest.bean.InfoMode
import com.cndll.shapetest.databinding.ItemScoreHeadBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [ScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScoreFragment : BrandDiscountFragment() {
    // TODO: Rename and change types of parameters
    private val mParam1: String? = null
    private val mParam2: String? = null
    override fun init() {
        super.init()
        headAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(4).
                setRes(R.layout.item_score_head).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5 * 2)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemScoreHeadBinding
                    binding.info = score
                }).creatAdapter()
    }

    lateinit var headAdapter: VLayoutAdapter
    var score = InfoMode()
    override fun setVLayout() {
        super.setVLayout()
        adapter.addAdapter(0, headAdapter)
    }

    override fun pullData(mode: Int): Boolean {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().scorePage(SharedPreferenceUtil.read("key", "")), {
            baseResponse ->
            when (mode) {
                (MODE_PULL) -> {
                    for (i in (baseResponse as ScoreResponse).datas.goods) {
                        val c = CommodityVerInfoMode()
                        c.storeID = i.store_id
                        c.name = i.goods_name
                        c.nowPrice = i.goods_price
                        c.oldPreci = i.goods_marketprice
                        c.orderScore = i.shop_score
                        c.imgUrl = i.img_url
                        c.score = i.score
                        c.goodsID = i.goods_id
                        brandMode.add(c)
                    }
                    score.info = baseResponse.datas.member_score
                    commodityAdapter.mCount = baseResponse.datas.goods.size
                    commodityAdapter.notifyDataSetChanged()
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
        open val FLAG = "消费积分专区"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment ScoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): ScoreFragment {
            val fragment = ScoreFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
