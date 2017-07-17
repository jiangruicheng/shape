package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.AdvanceOrderActivity
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by Administrator on 2017/7/15 0015.
 */
class AdvanceOrderAdapter(private val contentValues: List<ContentValues>?, private val context: Context):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate(R.layout.advance_order_item,null)
            holder.advSim=convertView!!.findViewById(R.id.adv_sim) as SimpleDraweeView
            holder.advDetails=convertView!!.findViewById(R.id.adv_details) as TextView
            holder.advName=convertView!!.findViewById(R.id.adv_name) as TextView
            holder.advType=convertView!!.findViewById(R.id.adv_type) as TextView
            holder.advTime=convertView!!.findViewById(R.id.adv_time) as TextView
            holder.advDate=convertView!!.findViewById(R.id.adv_date) as TextView
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues!!.size <= position || contentValues==null){
            return convertView
        }
        if(contentValues[position].getAsString("advType").equals("等待用餐")){
            holder.advDate.visibility=View.VISIBLE
            holder.advDetails.setOnClickListener {
                context.startActivity(Intent(context,AdvanceOrderActivity::class.java))
            }
        }else if(contentValues[position].getAsString("advType").equals("订单已完成")){
            holder.advDate.visibility=View.INVISIBLE
            holder.advDetails.setOnClickListener {
                context.startActivity(Intent(context,AdvanceOrderActivity::class.java))
            }
        }
        holder.advSim.setImageURI(contentValues[position].getAsString("advSim"))
        holder.advName.text=contentValues[position].getAsString("advName")
        holder.advType.text=contentValues[position].getAsString("advType")
        holder.advTime.text=contentValues[position].getAsString("advTime")
        holder.advDate.text=contentValues[position].getAsString("advData")
        holder.advDetails.text=contentValues[position].getAsString("advDetails")
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
        lateinit var advSim:SimpleDraweeView
        lateinit var advDetails:TextView
        lateinit var advName:TextView
        lateinit var advType:TextView
        lateinit var advTime:TextView
        lateinit var advDate:TextView
    }
}