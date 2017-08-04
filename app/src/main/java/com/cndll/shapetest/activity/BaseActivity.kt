package com.cndll.shapetest.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.cndll.shapetest.tools.Constants

open abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!Constants.isNetworkAvailable(this@BaseActivity)){
            Toast.makeText(applicationContext, "检查网络！", Toast.LENGTH_LONG).show()
            return
        }

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
