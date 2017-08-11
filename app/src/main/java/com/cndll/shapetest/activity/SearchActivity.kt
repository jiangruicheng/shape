package com.cndll.shapetest.activity

import android.content.Intent
import android.os.Bundle

import com.cndll.shapetest.R
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
            startActivity(Intent(this, ResultActivity::class.java).putExtra(ResultActivity.MODE, ResultActivity.MODE_SEARCH).putExtra(ResultActivity.TYPE,ResultActivity.TYPE_COMMODIYT).putExtra(ResultActivity.SEARCHKE,queryString))
            return@init false
        })
    }
}
