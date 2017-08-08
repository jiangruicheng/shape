package com.cndll.shapetest.adapter

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cndll.shapetest.R


/**
 * Created by Administrator on 2017/8/8 0008.
 * 银行卡
 */
class BankCardAdapter(private val contentValues: List<ContentValues>?, private val context: Context) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.bank_card_item, null)
            holder.bankCardImg = convertView!!.findViewById(R.id.bank_card_img) as ImageView
            holder.bankCardNum = convertView!!.findViewById(R.id.textView11) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        if (contentValues!!.size <= position || contentValues == null) {
            return convertView
        }
        holder.bankCardNum.text = contentValues[position].getAsString("nun")
        holder.bankCardImg.setOnClickListener {

            AlertDialog.Builder(context)//设置对话框标题

                    .setMessage("是否删除中国银行卡 1123")//设置显示的内容

                    .setPositiveButton("删除", DialogInterface.OnClickListener { dialog, which ->


                    }).setNegativeButton("取消", DialogInterface.OnClickListener { dialog, which ->


            }).show()

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
        lateinit var bankCardImg: ImageView
        lateinit var bankCardNum: TextView

    }

}