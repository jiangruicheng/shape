package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cndll.shapetest.R
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by Administrator on 2017/7/14 0014.
 */
class PopularizeAdapter(private  val context: Context,private val contentValues: List<ContentValues>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView=convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate(R.layout.popularize_item,null)
            holder.popuImg=convertView!!.findViewById(R.id.popu_item_icon) as SimpleDraweeView
            holder.popuNick=convertView!!.findViewById(R.id.popu_item_name) as TextView
            holder.popuTime=convertView!!.findViewById(R.id.popu_item_time) as TextView
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues!!.size <= position || contentValues==null){return convertView}
        holder.popuImg.setImageURI(contentValues[position].getAsString("img"))
        holder.popuNick.text=contentValues[position].getAsString("name")
        holder.popuTime.text=contentValues[position].getAsString("time")
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
        lateinit var popuImg: SimpleDraweeView
        lateinit var popuNick:TextView
        lateinit var popuTime:TextView
    }
}