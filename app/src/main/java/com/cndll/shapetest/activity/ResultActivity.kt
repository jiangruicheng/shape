package com.cndll.shapetest.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityResultBinding
import com.cndll.shapetest.databinding.PopviewSwitchCommodityandshopBinding
import com.cndll.shapetest.fragment.ResultFragment
import com.cndll.shapetest.tools.ImageFactory
import com.cndll.shapetest.weight.PopUpViewUtil

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    companion object {
        val TITLE = "TITLE"
        val MODE_SEARCH = 0
        val MODE_CLASS = 1
        val MODE = "MODE"
        val TYPE = "TYPE"
        val TYPE_SHOP = 2
        val TYPE_COMMODIYT = 3
        val SEARCHKE = "KEY"
        val GOODS_ORDER_DEFAULT = "goods.is_own_shop desc,goods.goods_id desc"
        val GOODS_ORDER_SALENUM = "goods.goods_salenum desc"
        val SHOP_ORDER_DEFAULT = "store.store_id desc"
    }

    override fun initBindingVar() {
    }

    override fun initTitle() {
        when (intent.extras.getInt(MODE)) {
            (MODE_SEARCH) -> {
                binding.searchTitle.root.visibility = View.VISIBLE
                binding.classiTitle.root.visibility = View.GONE
                binding.searchTitle.searchEdit.searchEdit.setText(intent.extras.getString(SEARCHKE))
                binding.searchTitle.searchEdit.searchEdit.setOnClickListener { finish() }
                binding.searchTitle.searchEdit.delete.setOnClickListener { finish() }
                binding.searchTitle.back.setOnClickListener { finish() }
            }
            (MODE_CLASS) -> {
                binding.searchTitle.root.visibility = View.GONE
                binding.classiTitle.title.setText(intent.extras.getString(TITLE))
                binding.classiTitle.root.visibility = View.VISIBLE
                binding.classiTitle.menu.visibility = View.VISIBLE
                binding.classiTitle.menuDate.visibility = View.GONE
                binding.classiTitle.menu.setText("商品")
                binding.classiTitle.menu.setCompoundDrawables(null, null, ImageFactory.getDrawableWithRes(R.mipmap.down, this), null)
                binding.classiTitle.menu.setOnClickListener {
                    val pop = PopUpViewUtil.getInstance()
                    val locations = intArrayOf(1, 2)
                    binding.classiTitle.menu.getLocationOnScreen(locations)
                    locations[1] = locations[1] + binding.classiTitle.menu.height
                    locations[0] = locations[0] - binding.classiTitle.menu.width / 3
                    val view = LayoutInflater.from(this).inflate(R.layout.popview_switch_commodityandshop, null, false)
                    val b = DataBindingUtil.bind<PopviewSwitchCommodityandshopBinding>(view)
                    b.commodity.setOnClickListener {
                        binding.classiTitle.menu.setText("商品")
                        pop.dismiss()
                        fragment.switchType(TYPE_COMMODIYT)
                    }
                    b.shop.setOnClickListener {
                        binding.classiTitle.menu.setText("商铺")
                        pop.dismiss()
                        fragment.switchType(TYPE_SHOP)
                    }
                    pop.popListWindow(binding.classiTitle.menu, view, windowManager.defaultDisplay.width / 10 * 3, windowManager.defaultDisplay.height / 7, Gravity.NO_GRAVITY, locations)
                }
            }
        }
    }

    lateinit var fragment: ResultFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_result)
        fragment = ResultFragment.newInstance("", "")
        val b = Bundle()
        b.putAll(intent.extras)
        fragment.arguments = b
        supportFragmentManager.beginTransaction().add(R.id.frame_result, fragment).commit()
    }
}
