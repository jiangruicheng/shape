package com.cndll.shapetest.weight

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.annotation.LayoutRes
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.bean.response.CommodityResponse
import com.cndll.shapetest.bean.CommodityInfoMode
import com.cndll.shapetest.databinding.PopviewGoodstypeBinding

/**
 * Created by jiangruicheng on 2017/8/2.
 */
class CommodityInfo {
    var i = 1
    lateinit var view: View
    lateinit var binding: PopviewGoodstypeBinding
    fun initView(context: Context): CommodityInfo {
        val infoMode = CommodityInfoMode()
        view = LayoutInflater.from(context).inflate(R.layout.popview_goodstype, null, false)
        binding = DataBindingUtil.bind(view)
        binding.info = infoMode
        /*infoMode.price = "12"
        val choose = CommodityResponse.DatasBean.GoodsBean()
        choose.goods_type_name = "美容"
        infoMode.choose = choose
        infoMode.commodityCount = "123123"
        infoMode.count = "2"*/
        binding.countConfig.edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Toast.makeText(context, infoMode.count.toString(), Toast.LENGTH_SHORT).show()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        binding.sure.setOnClickListener {
            infoMode.price = (12 + i).toString()
            val choose = CommodityResponse.DatasBean.GoodsBean()
            choose.goods_type_name = "美容" + i.toString()
            infoMode.choose = choose
            infoMode.commodityCount = (123123 + i).toString()
            infoMode.count = (2 + i).toString()
            i++
        }
        return this
    }

    fun popview(location: View, context: Context) {
        val p = PopUpViewUtil.getInstance()
        initView(context)
        p.popListWindow(location, view, p.getWindowManager(context).defaultDisplay.width, p.getWindowManager(context).defaultDisplay.height / 2, Gravity.BOTTOM, null)

    }
}