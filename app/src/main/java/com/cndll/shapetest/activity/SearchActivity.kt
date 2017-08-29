package com.cndll.shapetest.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView

import com.cndll.shapetest.R
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.HotSearchResponse
import com.cndll.shapetest.databinding.ActivitySearchBinding
import com.cndll.shapetest.databinding.PopviewSwitchCommodityandshopBinding
import com.cndll.shapetest.weight.PopUpViewUtil
import com.cndll.shapetest.weight.SearchTitle

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    override fun initTitle() {
    }

    override fun initBindingVar() {
    }

    var type = ResultActivity.TYPE_COMMODIYT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_search)
        binding.searchType.setOnClickListener {
            val pop = PopUpViewUtil.getInstance()
            val locations = intArrayOf(1, 2)
            binding.searchType.getLocationOnScreen(locations)
            locations[1] = locations[1] + binding.searchType.height
            locations[0] = locations[0] - binding.searchType.width / 3
            val view = LayoutInflater.from(this).inflate(R.layout.popview_switch_commodityandshop, null, false)
            val b = DataBindingUtil.bind<PopviewSwitchCommodityandshopBinding>(view)
            b.commodity.setOnClickListener {
                binding.searchType.setText("商品")
                pop.dismiss()
                type = ResultActivity.TYPE_COMMODIYT
            }
            b.shop.setOnClickListener {
                binding.searchType.setText("商铺")
                pop.dismiss()
                type = ResultActivity.TYPE_SHOP

            }
            pop.popListWindow(binding.searchType, view, windowManager.defaultDisplay.width / 10 * 3, windowManager.defaultDisplay.height / 7, Gravity.NO_GRAVITY, locations)

        }
        val searchTitle = SearchTitle()
        searchTitle.init(binding.search.root, {
            queryString ->
            startActivity(Intent(this, ResultActivity::class.java).
                    putExtra(ResultActivity.MODE, ResultActivity.MODE_SEARCH).
                    putExtra(ResultActivity.TYPE, type).
                    putExtra(ResultActivity.SEARCHKE, queryString))
            Log.e("key", queryString)
            return@init false
        })
        ApiUtill.getInstance().getApi(AppRequest.getAPI().hotSearch(), {
            baseResponse ->
            baseResponse as HotSearchResponse
            binding.hotSearch.removeAllViews()
            for (i in baseResponse.datas.list) {
                val view = layoutInflater.inflate(R.layout.item_hotsearche_textview, null, false) as TextView
                view.setText(i)
                binding.hotSearch.addView(view)
            }
        })
    }
}
