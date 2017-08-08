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
 * Created by Administrator on 2017/8/7 0007.
 */
class IntergralRecodeAdapter(private val context: Context, private val contentValues: List<ContentValues>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.intefral_record_item, null)
            holder.interTime = convertView!!.findViewById(R.id.record_time_tx) as TextView
            holder.interType = convertView!!.findViewById(R.id.record_type_tx) as TextView
            holder.interFun = convertView!!.findViewById(R.id.record_fun_tx) as TextView
            holder.interManage = convertView!!.findViewById(R.id.record_manage_tx) as TextView
            holder.interCode = convertView!!.findViewById(R.id.record_account_tx) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        if (contentValues!!.size <= position || contentValues == null) {
            return convertView
        }
        holder.interTime.text = "2014-1-4"
        holder.interType.text = "112"
        holder.interFun.text = "1010"
        holder.interManage.text = "10"
        holder.interCode.text = "-4"
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

    inner class ViewHolder {
        lateinit var interTime: TextView
        lateinit var interType: TextView
        lateinit var interFun: TextView
        lateinit var interManage: TextView
        lateinit var interCode: TextView
    }

}