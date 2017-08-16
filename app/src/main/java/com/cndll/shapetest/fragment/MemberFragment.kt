package com.cndll.shapetest.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import com.alibaba.android.vlayout.layout.LinearLayoutHelper

import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.MemberResponse
import com.cndll.shapetest.bean.CommodityVerInfoMode
import com.cndll.shapetest.bean.InfoMode
import com.cndll.shapetest.databinding.ItemCommodityVerBinding
import com.cndll.shapetest.databinding.ItemMemberHeadBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [MemberFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MemberFragment : BaseVlayoutFragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    lateinit var commodityAdapter: VLayoutAdapter
    var memberMode = arrayListOf<CommodityVerInfoMode>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun init() {
        super.init()
        pullData(MODE_PULL)
        commodityAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(memberMode.size).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(3).
                setRes(R.layout.item_commodity_ver).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 5 * 2)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemCommodityVerBinding
                    binding.info = memberMode[position]
                    binding.image.setImageURI(memberMode[position].imgUrl)
                }).creatAdapter()
    }

    override fun setVLayout() {
        super.setVLayout()
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(LinearLayoutHelper()).
                setViewType(8).
                setRes(R.layout.item_member_head).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 4)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemMemberHeadBinding
                    val info = InfoMode()
                    info.info = "小麦屋先生"
                    info.setmBoolean(false)
                    binding.info = info
                    binding.isvipimage = R.mipmap.vip
                    binding.notvipimage = R.mipmap.notvip
                }).creatAdapter())
        adapter.addAdapter(commodityAdapter)
    }

    override fun pullData(mode: Int): Boolean {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().memberPage(SharedPreferenceUtil.read("key", "")), {
            baseResponse ->
            when (mode) {
                (MODE_PULL) -> {
                    val b = true
                    for (i in (baseResponse as MemberResponse).datas.goods) {
                        val c = CommodityVerInfoMode()
                        c.imgUrl = i.goods_image
                        c.name = i.goods_name
                        c.nowPrice = i.goods_price
                        c.oldPrice = i.goods_marketprice
                        c.goodsID = i.goods_id
                        c.storeID = i.store_id
                        c.score = i.score
                        c.showButton = b
                        c.isButtonEnable = (baseResponse).datas.is_member == "1"
                        memberMode.add(c)
                    }
                    commodityAdapter.mCount = memberMode.size
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
        open val FLAG = "会员特权"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment MemberFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MemberFragment {
            val fragment = MemberFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
