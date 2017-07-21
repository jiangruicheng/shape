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
 * Created by Administrator on 2017/7/21 0021.
 */
class PacketAdapter(private val contentValues: List<ContentValues>?, private val context: Context):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView=convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate(R.layout.red_packet_item,null)
            holder.packetType=convertView!!.findViewById(R.id.packet_type) as TextView
            holder.packetTime=convertView!!.findViewById(R.id.packet_time) as TextView
            holder.packetAccount=convertView!!.findViewById(R.id.packet_account) as TextView
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues!!.size <= position || contentValues==null){
            return convertView
        }
        holder.packetType.text=contentValues[position].getAsString("packetType")
        holder.packetTime.text=contentValues[position].getAsString("packetTime")
        holder.packetAccount.text=contentValues[position].getAsString("packetAccount")

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
        lateinit var packetType: TextView
        lateinit var packetTime: TextView
        lateinit var packetAccount: TextView
    }
}