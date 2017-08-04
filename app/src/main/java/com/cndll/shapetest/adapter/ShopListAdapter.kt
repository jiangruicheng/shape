package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.cndll.shapetest.R
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by Administrator on 2017/7/15 0015.
 */
class ShopListAdapter(private val contentValues: List<ContentValues>?, private val context: Context) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.shop_item, null)
            holder.shopSim = convertView!!.findViewById(R.id.shop_sim) as SimpleDraweeView
            holder.shopAccount = convertView!!.findViewById(R.id.shop_account) as TextView
            holder.shopAllAccount = convertView!!.findViewById(R.id.shop_all_account) as TextView
            holder.shopName = convertView!!.findViewById(R.id.shop_name) as TextView
            holder.shopGoto = convertView!!.findViewById(R.id.shop_goto) as Button
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        if (contentValues!!.size <= position || contentValues == null) {
            return convertView
        }
        holder.shopSim.setImageURI(contentValues[position].getAsString("shopSim"))
        holder.shopName.text=contentValues[position].getAsString("shopName")
        holder.shopAccount.text=contentValues[position].getAsString("shopAccount")
        holder.shopAllAccount.text=contentValues[position].getAsString("shopAllAccount")
        holder.shopGoto.setOnClickListener {
         Toast.makeText(context,"this is test!",Toast.LENGTH_LONG).show()
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
        lateinit var shopSim: SimpleDraweeView
        lateinit var shopName: TextView
        lateinit var shopAccount: TextView
        lateinit var shopAllAccount: TextView
        lateinit var shopGoto: Button
    }
}