package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.api.bean.response.CollectResponse
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by Administrator on 2017/7/15 0015.
 */
class CollectGoodsAdapter(private val contentValues: List<CollectResponse.DatasBean.FavoritesListBean>?, private val context: Context) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.goods_item, null)
            holder.goodsSim = convertView!!.findViewById(R.id.goods_sim) as SimpleDraweeView
            holder.goodsName = convertView!!.findViewById(R.id.goods_name) as TextView
            holder.goodsNewPrice = convertView!!.findViewById(R.id.goods_new_price) as TextView
            holder.goodsOldPrice = convertView!!.findViewById(R.id.goods_old_price) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        if (contentValues!!.size <= position || contentValues == null) {
            return convertView
        }
        holder.goodsOldPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.goodsOldPrice.paint.isAntiAlias = true
        holder.goodsSim.setImageURI(contentValues[position].goods_image_url)
        holder.goodsName.text = contentValues[position].goods_name
        holder.goodsNewPrice.text = contentValues[position].goods_price
        holder.goodsOldPrice.text = contentValues[position].goods_marketprice
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
        lateinit var goodsSim: SimpleDraweeView
        lateinit var goodsName: TextView
        lateinit var goodsNewPrice: TextView
        lateinit var goodsOldPrice: TextView
    }

}