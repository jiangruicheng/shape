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
        if (getFragment(intent.action) != null) {
            supportFragmentManager.beginTransaction().add(R.id.frame, getFragment(intent.action)).commit()
        }
    }

    fun getFragment(flag: String): Fragment? {
        var f: Fragment? = null
        when (flag) {
            (LimitedSpikeFragment.FLAG) -> {
                f = LimitedSpikeFragment.newInstance("", "")
            }
            (MissionToFightFragment.FLAG) -> {
                f = MissionToFightFragment.newInstance("", "")
            }
            (CommodityInfoFragment.FLAG) -> {
                f = CommodityInfoFragment.newInstance("", "")
            }
            (LineaOffShopFragment.FLAG) -> {
                f = LineaOffShopFragment.newInstance("", "")
            }
            (NearByShopFoodFragment.FLAG) -> {
                f = NearByShopFoodFragment.newInstance("", "")
            }
            (NearByShopFoodStoreFragment.FLAG) -> {
                f = NearByShopFoodStoreFragment.newInstance("", "")
            }
            (BrandDiscountFragment.FLAG) -> {
                f = BrandDiscountFragment.newInstance("", "")
            }
            (ScoreFragment.FLAG) -> {
                f = ScoreFragment.newInstance("", "")
            }
            (MemberFragment.FLAG) -> {
                f = MemberFragment.newInstance("", "")
            }
            (MoreClassFragment.FLAG) -> {
                f = MoreClassFragment.newInstance("", "")
            }
            (AddOrderFragment.FLAG) -> {
                f = AddOrderFragment.newInstance("", "")
            }
        }
        if (f != null) {
            f.arguments = intent.extras
        }
        return f
    }
}
