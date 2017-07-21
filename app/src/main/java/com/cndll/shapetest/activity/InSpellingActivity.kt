package com.cndll.shapetest.activity

import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityInSpellingBinding

/**
 * 拼团---
 * */
class InSpellingActivity : BaseActivity<ActivityInSpellingBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="拼团中"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_in_spelling)
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){


    }
}
