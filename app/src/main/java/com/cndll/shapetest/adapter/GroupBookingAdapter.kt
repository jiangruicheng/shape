package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.GroupBookingDetailsActivity
import com.cndll.shapetest.activity.ReimburseActivity
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by Administrator on 2017/7/14 0014.
 */
class GroupBookingAdapter(private  val context: Context, private val contentValues: List<ContentValues>,var type:Int):BaseAdapter(){
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
            holder.shopMoney=convertView!!.findViewById(R.id.shop_money) as TextView
            holder.shopAppeal=convertView!!.findViewById(R.id.shop_appeal) as Button
            holder.shopDetails=convertView!!.findViewById(R.id.shop_details) as Button
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues!!.size <= position || contentValues==null){return convertView}
        holder.shopImg.setImageURI(contentValues[position].getAsString("shopImg"))
        holder.shopName.text=contentValues[position].getAsString("shopName")
        holder.shopType.text=contentValues[position].getAsString("shopType")
        holder.shopPrice.text=contentValues[position].getAsString("shopPrice")

        if(type==0){
            holder.shopNum.visibility=View.INVISIBLE
            holder.shopAppeal.visibility=View.INVISIBLE
            holder.shopMoney.visibility=View.INVISIBLE
            holder.shopOldPrice.paint.flags=Paint.STRIKE_THRU_TEXT_FLAG
            holder.shopOldPrice.paint.isAntiAlias=true
            holder.shopText.setTextColor(context.resources.getColor(R.color.tabTextSelect))
            holder.shopOldPrice.text=contentValues[position].getAsString("shopOldPrice")
            holder.shopText.text="破in团长"
            holder.shopDetails.text="查看订单详情"
            holder.shopDetails.setOnClickListener {
                context.startActivity(Intent(context,GroupBookingDetailsActivity::class.java))
            }
        }else if(type==1){
            holder.shopOldPrice.visibility=View.INVISIBLE
            holder.shopAppeal.visibility=View.INVISIBLE
            holder.shopNum.text="x1"
            holder.shopText.text="退款金额：￥"
            holder.shopMoney.text=contentValues[position].getAsString("shopOldPrice")
            holder.shopDetails.text="查看退款详情"
            holder.shopDetails.setOnClickListener {
                var bundle=Bundle()
                bundle.putInt("type",1)
                context.startActivity(Intent(context,ReimburseActivity::class.java).putExtras(bundle))
            }
        }else if(type==2){
            holder.shopOldPrice.visibility=View.INVISIBLE
            holder.shopNum.text="x1"
            holder.shopText.text="退款金额：￥"
            holder.shopMoney.text=contentValues[position].getAsString("shopOldPrice")
            holder.shopDetails.text="查看退款详情"
            holder.shopAppeal.setOnClickListener { //申诉

            }
            holder.shopDetails.setOnClickListener {
                var bundle=Bundle()
                bundle.putInt("type",2)
                context.startActivity(Intent(context, ReimburseActivity::class.java).putExtras(bundle))
            }
        }else if(type==3){
            holder.shopOldPrice.visibility=View.INVISIBLE
            holder.shopAppeal.visibility=View.INVISIBLE
            holder.shopNum.text="x1"
            holder.shopText.text="退款金额："+contentValues[position].getAsString("shopOldPrice")
            holder.shopText.text="退款金额：￥"
            holder.shopMoney.text=contentValues[position].getAsString("shopOldPrice")
            holder.shopDetails.setOnClickListener {
                var bundle=Bundle()
                bundle.putInt("type",3)
                context.startActivity(Intent(context,ReimburseActivity::class.java).putExtras(bundle))
            }
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
        lateinit var shopMoney:TextView
        lateinit var shopAppeal:Button
        lateinit var shopDetails:Button
    }

}