package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.cndll.shapetest.R
import com.zhy.android.percent.support.PercentLinearLayout

/**
 * Created by Administrator on 2017/7/14 0014.
 */
class VouchersAdapter(private val contentValues: List<ContentValues>?, private val context: Context,private val type:Int):BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate(R.layout.vouchers_item,null)
            holder.vouchersLin=convertView!!.findViewById(R.id.lin_bg_quan) as PercentLinearLayout
            holder.vouchersTime=convertView!!.findViewById(R.id.vouchers_time) as TextView
            holder.vouchersPrice=convertView!!.findViewById(R.id.vouchers_price) as TextView
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues!!.size <= position || contentValues==null){
            return convertView
        }

        if(type==1){
            holder.vouchersLin.setBackgroundDrawable(context.resources.getDrawable(R.mipmap.qtwo))
            holder.vouchersTime.visibility=View.GONE
            holder.vouchersPrice.text=contentValues[position].getAsString("price")
        }else{
            holder.vouchersLin.setBackgroundDrawable(context.resources.getDrawable(R.mipmap.qone))
            holder.vouchersTime.visibility=View.VISIBLE
            holder.vouchersTime.text=contentValues[position].getAsString("time")
            holder.vouchersPrice.text=contentValues[position].getAsString("price")
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
        lateinit var vouchersLin: PercentLinearLayout
        lateinit var vouchersTime:TextView
        lateinit var vouchersPrice:TextView
    }

}