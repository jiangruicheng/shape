package com.cndll.shapetest.adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.cndll.shapetest.R
import com.zhy.android.percent.support.PercentLinearLayout

/**
 * Created by Administrator on 2017/7/17 0017.
 */
class AddressAdapter(private val contentValues: List<ContentValues>?, private val context: Context, val onClick: setOnClickLoction):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView=convertView
        var holder:ViewHolder
        if(convertView==null){
            holder=ViewHolder()
            convertView=LayoutInflater.from(context).inflate(R.layout.address_item,null)
            holder.arsName=convertView!!.findViewById(R.id.ars_userName) as TextView
            holder.arsPhone=convertView!!.findViewById(R.id.ars_userPhone) as TextView
            holder.arsDetalis=convertView!!.findViewById(R.id.ars_userAddress) as TextView
            holder.arsChose=convertView!!.findViewById(R.id.ars_userChose) as CheckBox
            holder.arsEdit=convertView!!.findViewById(R.id.ars_userEdit) as PercentLinearLayout
            holder.arsDelete=convertView!!.findViewById(R.id.ars_userDelete) as PercentLinearLayout
            convertView.tag=holder
        }else{
            holder=convertView.tag as ViewHolder
        }
        if(contentValues!!.size <= position || contentValues==null){
            return convertView
        }
        holder.arsName.text=contentValues[position].getAsString("arsName")
        holder.arsPhone.text=contentValues[position].getAsString("arsPhone")
        holder.arsDetalis.text=contentValues[position].getAsString("arsDetails")
        holder.arsChose.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                holder.arsChose.setHintTextColor(context.resources.getColor(R.color.titleRed))
            }else{
                holder.arsChose.setHintTextColor(context.resources.getColor(R.color.text))
            }

        }
        //选择默认地址
        holder.arsChose.setOnClickListener {
            onClick.chose(position)
        }
        //编辑地址
        holder.arsEdit.setOnClickListener {
            onClick.edit(position)
        }
        //删除地址
        holder.arsDelete.setOnClickListener {
            onClick.delete(position)
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
        lateinit var arsName:TextView
        lateinit var arsPhone:TextView
        lateinit var arsDetalis:TextView
        lateinit var arsChose:CheckBox
        lateinit var arsEdit: PercentLinearLayout
        lateinit var arsDelete:PercentLinearLayout
    }

    interface setOnClickLoction {
        fun edit(posit: Int)
        fun delete(position: Int)
        fun chose(position: Int)
    }
}