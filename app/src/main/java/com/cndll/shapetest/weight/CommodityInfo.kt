package com.cndll.shapetest.weight

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.cndll.shapetest.R
import com.cndll.shapetest.api.bean.response.CommodityResponse
import com.cndll.shapetest.bean.CommodityInfoMode
import com.cndll.shapetest.databinding.ItemGoodstypeBinding
import com.cndll.shapetest.databinding.PopviewGoodstypeBinding

/**
 * Created by jiangruicheng on 2017/8/2.
 */
open class CommodityInfo {
    lateinit var view: View
    lateinit var binding: PopviewGoodstypeBinding
    val infoMode = CommodityInfoMode()
    lateinit var goods: List<CommodityResponse.DatasBean.GoodsBean>
    fun initView(builder: Builder): CommodityInfo {

        infoMode.avatar = builder.modeCommodity.store_label
        if (builder.modeCommodity.goods != null && !builder.modeCommodity.goods.isEmpty()) {
            infoMode.choose = builder.modeCommodity.goods[0]
        }
        infoMode.commodityCount = infoMode.choose.goods_info.goods_storage
        infoMode.price = infoMode.choose.goods_info.now_price
        infoMode.count = "1"
        view = LayoutInflater.from(builder.context).inflate(R.layout.popview_goodstype, null, false)
        binding = DataBindingUtil.bind(view)
        binding.info = infoMode
        val a = Adapter()
        val layout = GridLayoutManager(builder.context, goods.size, 1, false)
        binding.recycler.layoutManager = layout as RecyclerView.LayoutManager?
        binding.recycler.adapter = a

        binding.sure.setOnClickListener {

        }
        return this
    }

    fun popview(builder: Builder) {
        this.goods = builder.goods
        val p = PopUpViewUtil.getInstance()
        initView(builder)
        p.setOnDismissAction { builder.event() }
        p.popListWindow(builder.location, view,
                p.getWindowManager(builder.context).defaultDisplay.width,
                p.getWindowManager(builder.context).defaultDisplay.height / 2,
                Gravity.BOTTOM, null)
    }

    inner class Adapter : RecyclerView.Adapter<Adapter.ItemView>() {
        var select = 0
        override fun onBindViewHolder(holder: Adapter.ItemView?, position: Int) {
            holder!!.binding.name = goods[position].goods_type_name
            holder!!.binding.select = select == position
            holder!!.binding.text.setOnClickListener {

                infoMode.choose = goods[position]
                select = position
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Adapter.ItemView {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_goodstype, null, false)
            val p = ViewGroup.LayoutParams((context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.width / 5, ViewGroup.LayoutParams.MATCH_PARENT)
            view.layoutParams = p
            val item = ItemView(view)
            return item
        }

        override fun getItemCount(): Int {
            if (goods == null) {
                return 0
            }
            return goods.size
        }

        inner class ItemView(view: View) : RecyclerView.ViewHolder(view) {
            lateinit var binding: ItemGoodstypeBinding

            init {
                binding = DataBindingUtil.bind(view)
            }
        }
    }

    companion object Builder {
        lateinit var context: Context
        lateinit var location: View
        lateinit var goods: List<CommodityResponse.DatasBean.GoodsBean>
        lateinit var event: () -> Unit
        lateinit var modeCommodity: CommodityResponse.DatasBean

        fun msetContext(context: Context): Builder {
            this.context = context
            return this
        }

        fun msetLocation(location: View): Builder {
            this.location = location
            return this

        }

        fun msetGoods(goods: List<CommodityResponse.DatasBean.GoodsBean>): Builder {
            this.goods = goods
            return this
        }

        fun msetEvent(event: () -> Unit): Builder {
            this.event = event
            return this
        }

        fun msetModeCommodity(modeCommodity: CommodityResponse.DatasBean): Builder {
            this.modeCommodity = modeCommodity
            return this
        }
    }
}