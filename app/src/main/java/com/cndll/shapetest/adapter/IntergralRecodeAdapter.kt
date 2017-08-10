package com.cndll.shapetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.api.bean.response.ScoreAllResponse
import com.cndll.shapetest.tools.Constants

/**
 * Created by Administrator on 2017/8/7 0007.
 */
class IntergralRecodeAdapter(private val context: Context?, private val contentValues: List<ScoreAllResponse.DatasBean>, private val type: Int) : BaseAdapter() {
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

        if (type == 1) {//激励积分
            holder.interTime.text = Constants.strDate(contentValues[position].time)
            if (contentValues[position].type.equals("0")) {
                holder.interType.text = "支入"
            } else if (contentValues[position].type.equals("1")) {
                holder.interType.text = "支出"
            }

            holder.interFun.visibility = View.GONE
            if (contentValues[position].operation_type.equals("0")) {
                holder.interManage.text = "消费"
            } else if (contentValues[position].operation_type.equals("1")) {
                holder.interManage.text = "直捐"
            } else if (contentValues[position].operation_type.equals("2")) {
                holder.interManage.text = "转赠"
            } else if (contentValues[position].operation_type.equals("3")) {
                holder.interManage.text = "回购"
            }

            holder.interCode.text = contentValues[position].score
        } else if (type == 2) {
            holder.interTime.text = Constants.strDate(contentValues[position].time)
            holder.interType.visibility = View.GONE
            holder.interFun.visibility = View.GONE
            holder.interManage.text = contentValues[position].store_name
            holder.interCode.text = contentValues[position].score
        } else if (type == 3) {
            holder.interTime.text = Constants.strDate(contentValues[position].time)
            holder.interType.visibility = View.GONE
            holder.interFun.visibility = View.GONE
            holder.interManage.text = contentValues[position].goods_name
            holder.interCode.text = contentValues[position].score
        }else if(type==4){
            holder.interTime.text = Constants.strDate(contentValues[position].time)
            if(contentValues[position].type.equals("0")){
                holder.interType.text="激励积分"
            }else if (contentValues[position].type.equals("1")){
                holder.interType.text="通用抵用卷"
            }
            holder.interFun.visibility = View.GONE
            holder.interManage.text = contentValues[position].donation_num
            holder.interCode.text = contentValues[position].score
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
        lateinit var interTime: TextView
        lateinit var interType: TextView
        lateinit var interFun: TextView
        lateinit var interManage: TextView
        lateinit var interCode: TextView
    }

}