package com.cndll.shapetest.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.ArrayMap
import android.view.View

import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityTurnOnBinding
import com.cndll.shapetest.fragment.CommodityInfoFragment
import com.cndll.shapetest.fragment.LimitedSpikeFragment
import com.cndll.shapetest.fragment.MissionToFightFragment

class TurnOnActivity : BaseActivity<ActivityTurnOnBinding>() {
    override fun initBindingVar() {
    }

    val fragmentMap = HashMap<String, Fragment>()
    override fun initTitle() {
        if (intent.action.equals(CommodityInfoFragment.FLAG)) {
            binding.titlebar.root.visibility = View.GONE
            return
        }
        binding.titlebar.title.text = intent.action

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_turn_on)
        fragmentMap.put(LimitedSpikeFragment.FLAG, LimitedSpikeFragment.newInstance("", ""))
        fragmentMap.put(MissionToFightFragment.FLAG, MissionToFightFragment.newInstance("", ""))
        fragmentMap.put(CommodityInfoFragment.FLAG, CommodityInfoFragment.newInstance("", ""))

        supportFragmentManager.beginTransaction().add(R.id.frame, fragmentMap.get(intent.action)).commit()
    }
}
