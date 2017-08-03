package com.cndll.shapetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.api.bean.response.LogisticsResponse

/**
 * Created by Administrator on 2017/7/21 0021.
 */
class LogisticsAdapter(private val contentValues: List<LogisticsResponse.DatasBean.DeliverInfoBean>?, private val context: Context): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       var convertView = convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate(R.layout.logistics_item,null)
            holder.logisChage=convertView!!.findViewById(R.id.logis_item_chang) as TextView
            holder.logisCompany=convertView!!.findViewById(R.id.logis_item_company) as TextView
            holder.logisTime=convertView!!.findViewById(R.id.logis_item_time) as TextView
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues!!.size <= position || contentValues==null){
            return convertView
        }

        holder.logisChage.setBackgroundDrawable(context.resources.getDrawable(R.drawable.log))
        holder.logisCompany.setTextColor(context.resources.getColor(R.color.gray))
        holder.logisTime.setTextColor(context.resources.getColor(R.color.gray))
        if(position==0){
            holder.logisChage.setBackgroundDrawable(context.resources.getDrawable(R.drawable.logis))
            holder.logisCompany.setTextColor(context.resources.getColor(R.color.logis))
            holder.logisTime.setTextColor(context.resources.getColor(R.color.logis))
        }
        holder.logisCompany.text=contentValues[position].context
        holder.logisTime.text=contentValues[position].time

        return convertView
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return contentValues!!.size ?: 0
    }

    inner class ViewHolder{
        lateinit var logisChage:TextView
        lateinit var logisCompany:TextView
        lateinit var logisTime:TextView
    }

}