package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.GroupBookingDetailsActivity
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by Administrator on 2017/7/14 0014.
 */
class GroupBookingAdapter(private  val context: Context, private val contentValues: List<ContentValues>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView=convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate(R.layout.sales_item_layout,null)
            holder.shopImg=convertView!!.findViewById(R.id.shop_img) as SimpleDraweeView
            holder.shopName=convertView!!.findViewById(R.id.shop_name) as TextView
            holder.shopType=convertView!!.findViewById(R.id.shop_type) as TextView
            holder.shopPrice=convertView!!.findViewById(R.id.shop_price) as TextView
            holder.shopOldPrice=convertView!!.findViewById(R.id.shop_old_price) as TextView
            holder.shopNum=convertView!!.findViewById(R.id.shop_count) as TextView
            holder.shopText=convertView!!.findViewById(R.id.shop_type_text) as TextView
            holder.shopAppeal=convertView!!.findViewById(R.id.shop_appeal) as Button
            holder.shopDetails=convertView!!.findViewById(R.id.shop_details) as Button
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues!!.size <= position || contentValues==null){return convertView}
        holder.shopNum.visibility=View.INVISIBLE
        holder.shopAppeal.visibility=View.INVISIBLE
        holder.shopOldPrice.paint.flags=Paint.STRIKE_THRU_TEXT_FLAG
        holder.shopOldPrice.paint.isAntiAlias=true
        holder.shopText.setTextColor(context.resources.getColor(R.color.titleRed))
        holder.shopImg.setImageURI(contentValues[position].getAsString("shopImg"))
        holder.shopName.text=contentValues[position].getAsString("shopName")
        holder.shopType.text=contentValues[position].getAsString("shopType")
        holder.shopPrice.text=contentValues[position].getAsString("shopPrice")
        holder.shopOldPrice.text=contentValues[position].getAsString("shopOldPrice")
        holder.shopText.text="破in团长"
        holder.shopDetails.text="查看订单详情"
        holder.shopDetails.setOnClickListener {
            context.startActivity(Intent(context,GroupBookingDetailsActivity::class.java))
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
        return contentValues?.size?:0
    }

    inner class ViewHolder{
        lateinit var shopImg:SimpleDraweeView
        lateinit var shopName:TextView
        lateinit var shopType:TextView
        lateinit var shopPrice:TextView
        lateinit var shopOldPrice:TextView
        lateinit var shopNum:TextView
        lateinit var shopText:TextView
        lateinit var shopAppeal:Button
        lateinit var shopDetails:Button
    }

}