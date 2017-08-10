package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.api.bean.response.VouchersResponse
import com.cndll.shapetest.tools.Constants
import com.zhy.android.percent.support.PercentLinearLayout

/**
 * Created by Administrator on 2017/7/14 0014.
 */
class VouchersAdapter(private val contentValues: List<VouchersResponse.DatasBean>?, private val context: Context, private val type: Int, private val vouNum: String?) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.vouchers_item, null)
            holder.vouchersLin = convertView!!.findViewById(R.id.lin_bg_quan) as PercentLinearLayout
            holder.vouchersTime = convertView!!.findViewById(R.id.vouchers_time) as TextView
            holder.vouchersPrice = convertView!!.findViewById(R.id.vouchers_price) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        if (contentValues!!.size <= position || contentValues == null) {
            return convertView
        }

        if (type == 1) {
            holder.vouchersLin.setBackgroundDrawable(context.resources.getDrawable(R.mipmap.qtwo))
            holder.vouchersTime.visibility = View.VISIBLE
            holder.vouchersPrice.text = "￥" + contentValues[position].money
            holder.vouchersTime.text = Constants.strDate(contentValues[position].start_time) + "-" + Constants.strDate(contentValues[position].end_time)
        } else {
            holder.vouchersLin.setBackgroundDrawable(context.resources.getDrawable(R.mipmap.qone))
            holder.vouchersTime.visibility = View.GONE
            holder.vouchersPrice.text = "￥" + vouNum
        }
        holder.vouchersLin.setOnClickListener {
            //选择抵用卷
            Toast.makeText(context, "选择抵用卷", Toast.LENGTH_SHORT).show()
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
        lateinit var vouchersLin: PercentLinearLayout
        lateinit var vouchersTime: TextView
        lateinit var vouchersPrice: TextView
    }

}