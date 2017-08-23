package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.view.AutoListView
import com.cndll.shapetest.view.CHScrollView

/**
 * Created by Administrator on 2017/8/18 0018.
 */
class ScoreTypeAdapter(private val context: Context, private val contentValues: List<ContentValues>, private var lstv: AutoListView) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.auto_listview_item, null)
            holder.itemTitle = convertView!!.findViewById(R.id.item_title) as TextView
            CHScrollView.CHScrollViewHelper.addHViews(convertView!!.findViewById(R.id.item_scroll) as CHScrollView, lstv)
            holder.itemData1 = convertView!!.findViewById(R.id.item_data1) as TextView
            holder.itemData2 = convertView!!.findViewById(R.id.item_data2) as TextView
            holder.itemData3 = convertView!!.findViewById(R.id.item_data3) as TextView
            holder.itemData4 = convertView!!.findViewById(R.id.item_data4) as TextView
            holder.itemData5 = convertView!!.findViewById(R.id.item_data5) as TextView
            holder.itemData6 = convertView!!.findViewById(R.id.item_data6) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        if (contentValues!!.size <= position || contentValues == null) {
            return convertView
        }
        holder.itemTitle.text = "110"
        holder.itemData1.text = "112"
        holder.itemData2.text = "113"
        holder.itemData3.text = "114"
        holder.itemData4.text = "115"
        holder.itemData5.text = "116"
        holder.itemData6.text = "117"
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
        lateinit var itemTitle: TextView
        lateinit var itemData1: TextView
        lateinit var itemData2: TextView
        lateinit var itemData3: TextView
        lateinit var itemData4: TextView
        lateinit var itemData5: TextView
        lateinit var itemData6: TextView
    }


}