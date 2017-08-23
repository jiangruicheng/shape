package com.cndll.shapetest.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityTurnOnBinding
import com.cndll.shapetest.fragment.*

class TurnOnActivity : BaseActivity<ActivityTurnOnBinding>() {
    override fun initBindingVar() {
    }

    val fragmentMap = HashMap<String, Fragment>()
    override fun initTitle() {
        if (intent.action == CommodityInfoFragment.FLAG) {
            binding.titlebar.root.visibility = View.GONE
            return
        }
        if (intent.action == BrandDiscountFragment.FLAG) {
            when (intent.extras.get("id")) {
                ("1") -> {
                    binding.titlebar.title.text = "品牌折扣"

                }
                ("2") -> {
                    binding.titlebar.title.text = "服饰箱包"
                }
                ("3") -> {
                    binding.titlebar.title.text = "进口海淘"

                }
            }
            return
        }
        when (intent.action) {
            (LineaOffShopFragment.FLAG) -> {
                val drawable = resources.getDrawable(R.mipmap.down)
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight())
                binding.titlebar.menu.visibility = View.VISIBLE
                binding.titlebar.menu.setCompoundDrawables(null, null, drawable, null)
            }
        }
        binding.titlebar.title.text = intent.action
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_turn_on)

        putFragment(LimitedSpikeFragment.FLAG, LimitedSpikeFragment.newInstance("", ""))
        putFragment(MissionToFightFragment.FLAG, MissionToFightFragment.newInstance("", ""))
        putFragment(CommodityInfoFragment.FLAG, CommodityInfoFragment.newInstance("", ""))
        putFragment(LineaOffShopFragment.FLAG, LineaOffShopFragment.newInstance("", ""))
        putFragment(NearByShopFoodFragment.FLAG, NearByShopFoodFragment.newInstance("", ""))
        putFragment(NearByShopFoodStoreFragment.FLAG, NearByShopFoodStoreFragment.newInstance("", ""))
        putFragment(BrandDiscountFragment.FLAG, BrandDiscountFragment.newInstance("", ""))
        putFragment(ScoreFragment.FLAG, ScoreFragment.newInstance("", ""))
        putFragment(MemberFragment.FLAG, MemberFragment.newInstance("", ""))
        putFragment(MoreClassFragment.FLAG, MoreClassFragment.newInstance("", ""))
        putFragment(AddOrderFragment.FLAG, AddOrderFragment.newInstance("", ""))

        if (fragmentMap.containsKey(intent.action)) {
            supportFragmentManager.beginTransaction().add(R.id.frame, fragmentMap.get(intent.action)).commit()
        }
    }

    fun putFragment(flag: String, fragment: Fragment) {
        fragmentMap.put(flag, fragment)
        fragmentMap.get(flag)!!.arguments = intent.extras
    }
}
