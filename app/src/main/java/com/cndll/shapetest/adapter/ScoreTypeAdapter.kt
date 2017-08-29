package com.cndll.shapetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.api.bean.response.ScoreInfoResponse
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.view.AutoListView
import com.cndll.shapetest.view.CHScrollView

/**
 * Created by Administrator on 2017/8/18 0018.
 */
class ScoreTypeAdapter(private val context: Context, private val contentValues: List<ScoreInfoResponse.DatasBean.ScoreInfoBean>, private var lstv: AutoListView, private var type: Int) : BaseAdapter() {
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
        if (type == 1) {
            holder.itemTitle.text = Constants.strDate(contentValues[position].time)
            holder.itemData1.text = contentValues[position].so_score
            holder.itemData2.text = opType(contentValues[position].operation_type)
            holder.itemData3.text = contentValues[position].operation_symbol + contentValues[position].score
            holder.itemData4.text = contentValues[position].giver_num
            holder.itemData5.text = contentValues[position].now_score
            holder.itemData4.visibility = View.VISIBLE
            holder.itemData5.visibility = View.VISIBLE
            holder.itemData6.visibility = View.GONE
        }
        if (type == 2) {
            holder.itemTitle.text = Constants.strDate(contentValues[position].time)
            holder.itemData1.text = contentValues[position].max_score
            holder.itemData2.text = contentValues[position].today_score
            holder.itemData3.text = contentValues[position].overplus_score
            holder.itemData4.visibility = View.GONE
            holder.itemData5.visibility = View.GONE
            holder.itemData6.visibility = View.GONE
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

    inner class ViewHolder {
        lateinit var itemTitle: TextView
        lateinit var itemData1: TextView
        lateinit var itemData2: TextView
        lateinit var itemData3: TextView
        lateinit var itemData4: TextView
        lateinit var itemData5: TextView
        lateinit var itemData6: TextView
    }


    private fun opType(op: String): String {
        var opType = ""
        if (op.equals("0")) {
            opType = "消费"
        } else if (op.equals("1")) {
            opType = "直捐"
        } else if (op.equals("2")) {
            opType = "转赠"
        } else if (op.equals("3")) {
            opType = "回购"
        } else if (op.equals("4")) {
            opType = "抵用券转化"
        } else if (op.equals("5")) {
            opType = "受赠"
        }
        return opType
    }


}