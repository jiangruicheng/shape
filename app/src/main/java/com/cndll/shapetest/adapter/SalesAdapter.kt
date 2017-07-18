package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.cndll.shapetest.R
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by Administrator on 2017/7/14 0014.
 */
class SalesAdapter(private  val context: Context, private val contentValues: List<ContentValues>,var type:Int):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView=convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate(R.layout.sales_all_item_layout,null)
            holder.shopAllImg=convertView!!.findViewById(R.id.shop_all_img) as SimpleDraweeView
            holder.shopAllName=convertView!!.findViewById(R.id.shop_all_name) as TextView
            holder.shopAllType=convertView!!.findViewById(R.id.shop_all_type) as TextView
            holder.shopAllPrice=convertView!!.findViewById(R.id.shop_all_price) as TextView
            holder.shopAllNum=convertView!!.findViewById(R.id.shop_all_count) as TextView
            holder.shopAllText=convertView!!.findViewById(R.id.shop_all_text) as TextView
            holder.shopAllMoney=convertView!!.findViewById(R.id.shop_all_money) as TextView
            holder.shopAllApply=convertView!!.findViewById(R.id.shop_all_apply) as Button
            holder.shopAllAppeal=convertView!!.findViewById(R.id.shop_all_appeal) as Button
            holder.shopAllDetails=convertView!!.findViewById(R.id.shop_all_details) as Button
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues!!.size <= position || contentValues==null){return convertView}
        holder.shopAllImg.setImageURI(contentValues[position].getAsString("shopAllImg"))
        //全部----状态-支付状态判断
        if(type==1){

        }else if (type==2){
            //待付款
            holder.shopAllDetails.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_button_red))
        }else if(type==3){
            //待发货

        }else if(type==4){
            //待收货

        }else if(type==5){
            //待激励
            holder.shopAllDetails.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_button_red))
        }else if (type==6){
            //待评价

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

    inner class ViewHolder{
        lateinit var shopAllImg:SimpleDraweeView
        lateinit var shopAllName:TextView
        lateinit var shopAllType:TextView
        lateinit var shopAllPrice:TextView
        lateinit var shopAllNum:TextView
        lateinit var shopAllText:TextView
        lateinit var shopAllMoney:TextView
        lateinit var shopAllApply:Button
        lateinit var shopAllAppeal:Button
        lateinit var shopAllDetails:Button
    }

}