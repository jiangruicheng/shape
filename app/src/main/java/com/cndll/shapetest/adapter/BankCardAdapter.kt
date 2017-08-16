package com.cndll.shapetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.api.bean.response.BankCardResponse
import com.cndll.shapetest.tools.Constants
import com.nostra13.universalimageloader.core.ImageLoader


/**
 * Created by Administrator on 2017/8/8 0008.
 * 银行卡
 */
class BankCardAdapter(private val contentValues: List<BankCardResponse.DatasBean.BankInfoBean>?, private val context: Context, val onClick: setOnClick) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.bank_card_item, null)
            holder.bankCardImg = convertView!!.findViewById(R.id.bank_card_img) as ImageView
            holder.bankCardName = convertView!!.findViewById(R.id.bank_card_name) as TextView
            holder.bankCardType = convertView!!.findViewById(R.id.bank_card_type) as TextView
            holder.bankCardNum = convertView!!.findViewById(R.id.bank_card_code) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        if (contentValues!!.size <= position || contentValues == null) {
            return convertView
        }

        ImageLoader.getInstance().displayImage(contentValues[position].img, holder.bankCardImg)
        holder.bankCardName.text = contentValues[position].bank_name
        holder.bankCardType.text = contentValues[position].card_type
        holder.bankCardNum.text = Constants.replaceCard(contentValues[position].card_num)
        holder.bankCardImg.setOnClickListener {
            onClick.bankDelete(position)
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
        lateinit var bankCardName: TextView
        lateinit var bankCardType: TextView
        lateinit var bankCardNum: TextView
    }

    interface setOnClick {
        fun bankDelete(position: Int)
    }

}