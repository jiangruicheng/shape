package com.cndll.shapetest.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView

import com.cndll.shapetest.R
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.HotSearchResponse
import com.cndll.shapetest.databinding.ActivitySearchBinding
import com.cndll.shapetest.weight.SearchTitle

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    override fun initTitle() {
    }

    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_search)
        binding.skew.setOnClickListener {

        }
        val searchTitle = SearchTitle()
        searchTitle.init(binding.search.root, {
            queryString ->
            startActivity(Intent(this, ResultActivity::class.java).putExtra(ResultActivity.MODE, ResultActivity.MODE_SEARCH).putExtra(ResultActivity.TYPE, ResultActivity.TYPE_COMMODIYT).putExtra(ResultActivity.SEARCHKE, queryString))
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
