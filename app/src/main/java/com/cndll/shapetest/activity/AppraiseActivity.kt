package com.cndll.shapetest.activity

import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityAppraiseBinding


/**
 * 评级
 * */
class AppraiseActivity : BaseActivity<ActivityAppraiseBinding>() {
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="评价"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_appraise)
        binding.starApp.rating=2.0.toFloat()
        binding.starApp.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this@AppraiseActivity, "评价了" + rating + "星", Toast.LENGTH_SHORT).show()
        }
    }
}
