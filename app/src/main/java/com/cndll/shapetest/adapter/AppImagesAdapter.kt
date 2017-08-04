package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.cndll.shapetest.R
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by Administrator on 2017/7/17 0017.
 */
class AppImagesAdapter(private  val context: Context, private val contentValues: List<ContentValues>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
      var convertView=convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate( R.layout.images_item,null)
            holder.images=convertView!!.findViewById(R.id.app_image) as SimpleDraweeView
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues.size <= position || contentValues==null){return convertView}
        if(position==0){
            holder.images.setBackgroundDrawable(context.resources.getDrawable(R.mipmap.up_images))
        }else{
            holder.images.setImageURI(contentValues[position].getAsString("img"))
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
        lateinit var images:SimpleDraweeView
    }
}