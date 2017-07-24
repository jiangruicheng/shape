package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cndll.shapetest.R

/**
 * Created by Administrator on 2017/7/24 0024.
 */
class AdvOrderAdapter(private val contentValues: List<ContentValues>?, private val context: Context):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
      var convertView=convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate(R.layout.adv_order_item,null)
            holder.name=convertView!!.findViewById(R.id.adv_order_item_name) as TextView
            holder.num=convertView!!.findViewById(R.id.adv_order_item_account) as TextView
            holder.account=convertView!!.findViewById(R.id.adv_order_item_price) as TextView
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }

        if(contentValues!!.size <= position || contentValues==null){
            return convertView
        }
        holder.name.text=contentValues[position].getAsString("name")
        holder.num.text=contentValues[position].getAsString("num")
        holder.account.text=contentValues[position].getAsString("account")

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
        lateinit var name:TextView
        lateinit var num:TextView
        lateinit var account:TextView
    }

}