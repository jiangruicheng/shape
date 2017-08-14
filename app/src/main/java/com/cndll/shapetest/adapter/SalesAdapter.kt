package com.cndll.shapetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.api.bean.response.OrderListResponse
import com.facebook.drawee.view.SimpleDraweeView
import com.zhy.android.percent.support.PercentLinearLayout

/**
 * Created by Administrator on 2017/7/14 0014.
 */
class SalesAdapter(private val context: Context, private val contentValues: List<OrderListResponse.DatasBean>, val onClickLoction: setOnClickLoction) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder = ViewHolder()
        convertView = LayoutInflater.from(context).inflate(R.layout.sales_all_item_layout, null)
        holder.shopLinList = convertView!!.findViewById(R.id.lin_order_list) as LinearLayout
        holder.shopAllText = convertView!!.findViewById(R.id.shop_all_text) as TextView
        holder.shopAllMoney = convertView!!.findViewById(R.id.shop_all_money) as TextView
        holder.shopAllApply = convertView!!.findViewById(R.id.shop_all_apply) as Button
        holder.shopAllAppeal = convertView!!.findViewById(R.id.shop_all_appeal) as Button
        holder.shopAllDetails = convertView!!.findViewById(R.id.shop_all_details) as Button
        if (contentValues!!.size <= position || contentValues == null) {
            return convertView
        }

        //全部----状态-支付状态判断
        holder.shopLinList.removeAllViews()
        for (i in 0..(contentValues[position].goods_list.size - 1)) {
            val view = LayoutInflater.from(context).inflate(R.layout.sales_item_new, null)
            val linGoods=view.findViewById(R.id.lin_sales_goods) as PercentLinearLayout
            val shopImg = view.findViewById(R.id.shop_all_img) as SimpleDraweeView
            val shopName = view.findViewById(R.id.shop_all_name) as TextView
            val shopType = view.findViewById(R.id.shop_all_type) as TextView
            val shopPrice = view.findViewById(R.id.shop_all_price) as TextView
            val shopCount = view.findViewById(R.id.shop_all_count) as TextView
            shopImg.setImageURI(contentValues[position].goods_list[i].img_url)
            shopName.text = contentValues[position].goods_list[i].goods_name
            shopType.text = contentValues[position].goods_list[i].goods_spec
            shopPrice.text = "￥" + contentValues[position].goods_list[i].goods_price
            shopCount.text = "x" + contentValues[position].goods_list[i].goods_num
            linGoods.setOnClickListener {
                //订单详情
                onClickLoction.shopOrderDetails(position)
            }
            holder.shopLinList.addView(view)
        }
        holder.shopAllMoney.text = contentValues[position].order_amount
        if (contentValues[position].order_state.equals("0")) {
            holder.shopAllAppeal.visibility = View.INVISIBLE
            holder.shopAllApply.text = "删除订单"
            holder.shopAllDetails.text = "交易已取消"
            holder.shopAllDetails.setBackgroundColor(context.resources.getColor(R.color.white))
        } else if (contentValues[position].order_state.equals("10")) {
            holder.shopAllAppeal.visibility = View.INVISIBLE
            holder.shopAllApply.text = "取消订单"
            holder.shopAllDetails.text = "立即支付"
            holder.shopAllDetails.setTextColor(context.resources.getColor(R.color.white))
            holder.shopAllDetails.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_button_red))
        } else if (contentValues[position].order_state.equals("20")) {
            holder.shopAllAppeal.visibility = View.INVISIBLE
            holder.shopAllApply.visibility = View.INVISIBLE
            holder.shopAllDetails.text = "提醒发货"
            holder.shopAllDetails.setTextColor(context.resources.getColor(R.color.white))
            holder.shopAllDetails.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_button_red))
        } else if (contentValues[position].order_state.equals("30")) {
            holder.shopAllApply.setTextColor(context.resources.getColor(R.color.white))
            holder.shopAllApply.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_button_red))
            holder.shopAllAppeal.text = "查看物流"
            holder.shopAllApply.text = "确认收货"
            holder.shopAllDetails.text = "确认激励"
        } else if (contentValues[position].order_state.equals("40")) {

            if (contentValues[position].order_state.equals("40") && contentValues[position].is_excitation_state.equals("0")) {
                holder.shopAllApply.setTextColor(context.resources.getColor(R.color.white))
                holder.shopAllApply.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_button_red))
                holder.shopAllAppeal.text = "查看物流"
                holder.shopAllApply.text = "确认激励"
                holder.shopAllDetails.text = "评价"
            } else if (contentValues[position].order_state.equals("40") && contentValues[position].evaluation_state.equals("1") && contentValues[position].evaluation_again_state.equals("1")) {
                holder.shopAllAppeal.visibility = View.INVISIBLE
                holder.shopAllApply.text = "删除订单"
                holder.shopAllDetails.text = "再次购买"
            } else if (contentValues[position].order_state.equals("40") && contentValues[position].evaluation_state.equals("1") && contentValues[position].evaluation_again_state.equals("0")) {
                holder.shopAllAppeal.text = "删除订单"
                holder.shopAllApply.text = "追加评价"
                holder.shopAllDetails.text = "再次购买"
            } else {
                holder.shopAllAppeal.visibility = View.INVISIBLE
                holder.shopAllApply.visibility = View.INVISIBLE
                holder.shopAllDetails.text = "评价"
                holder.shopAllDetails.setTextColor(context.resources.getColor(R.color.white))
                holder.shopAllDetails.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_button_red))
            }
        }
        holder.shopAllAppeal.setOnClickListener {
            onClickLoction.shopAllAppeal(position)
        }
        holder.shopAllApply.setOnClickListener {
            onClickLoction.shopAllApply(position)
        }
        holder.shopAllDetails.setOnClickListener {
            onClickLoction.shopAllDetails(position)
        }
        return convertView
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return contentValues?.size ?: 0
    }

    inner class ViewHolder {
        lateinit var shopLinList: LinearLayout
        lateinit var shopAllText: TextView
        lateinit var shopAllMoney: TextView
        lateinit var shopAllApply: Button
        lateinit var shopAllAppeal: Button
        lateinit var shopAllDetails: Button
    }

    interface setOnClickLoction {
        fun shopAllAppeal(position: Int)
        fun shopAllApply(position: Int)
        fun shopAllDetails(position: Int)
        fun shopOrderDetails(position: Int)
    }
}