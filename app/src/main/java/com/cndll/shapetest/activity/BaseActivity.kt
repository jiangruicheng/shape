package com.cndll.shapetest.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        initBindingVar()
        initTitle()
    }

    fun initBinding(layout: Int) {
        binding = DataBindingUtil.setContentView<T>(this, layout)!!
    }

    abstract fun initBindingVar()
    abstract fun initTitle()
}
