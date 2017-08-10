package com.cndll.shapetest.activity

import android.os.Bundle
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityResultBinding
import com.cndll.shapetest.fragment.ResultFragment

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    companion object {
        val SEARCH = 0
        val CLASS = 1
        val MODE = "MODE"
        val TYPE = "TYPE"
        val SHOP = 2
        val COMMODIYT = 3
    }

    override fun initBindingVar() {
    }

    override fun initTitle() {
        when (intent.extras.getInt(MODE)) {
            (SEARCH) -> {
                binding.searchTitle.root.visibility = View.VISIBLE
                binding.classiTitle.root.visibility = View.GONE
            }
            (CLASS) -> {
                binding.searchTitle.root.visibility = View.GONE
                binding.classiTitle.root.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_result)
        val f = ResultFragment.newInstance("", "")
        val b = Bundle()
        b.putInt(TYPE, intent.extras.getInt(TYPE))
        f.arguments = b
        supportFragmentManager.beginTransaction().add(R.id.frame_result, f).commit()
    }
}
