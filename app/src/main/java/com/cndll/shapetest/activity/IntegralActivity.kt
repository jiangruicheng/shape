package com.cndll.shapetest.activity

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityIntegralBinding
import java.util.*
import android.widget.PopupWindow
import kotlin.collections.ArrayList

/**
 * 积分数据列表
 * */
class IntegralActivity : BaseActivity<ActivityIntegralBinding>() {
    var listDate = ArrayList<HashMap<String, String>>()

    lateinit var pwMyPopWindow:PopupWindow
    lateinit var lvPopupList:ListView
    val NUM_OF_VISIBLE_LIST_ROWS:Int=6

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.menu.visibility=View.VISIBLE
        binding.titlebar.menu.setImageResource(R.mipmap.date_sache)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_integral)
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        var bundle=this.intent.extras
        var type=bundle.getString("type")

        if(type.equals("incentive")){
            binding.titlebar.title.text="激励积分"
            binding.manageTx.visibility=View.GONE

        }else if(type.equals("score")){
            binding.titlebar.title.text="消费积分"
            binding.manageTx.visibility=View.GONE
            binding.funTx.visibility=View.GONE
            binding.typeTx.text="消费店铺"

        }else if(type.equals("subsidiary")){
            binding.titlebar.title.text="积分明细"
            binding.manageTx.visibility=View.GONE
            binding.funTx.visibility=View.GONE
            binding.typeTx.text="商品名称"
        }else if (type.equals("fund")){
            binding.titlebar.title.text="基金捐款"
            binding.manageTx.visibility=View.GONE
            binding.typeTx.visibility=View.GONE
        }else if(type.equals("buyBack")){
            binding.titlebar.title.text="回购记录"
            binding.integralDataTable.visibility=View.GONE
            binding.typeTx.visibility=View.GONE
            binding.funTx.visibility=View.GONE
            binding.manageTx.visibility=View.GONE
            binding.accountTx.text="回购金额"
        }else if (type.equals("donate")){
            binding.titlebar.title.text="直捐记录"
            binding.integralDataTable.visibility=View.GONE
            binding.typeTx.visibility=View.GONE
            binding.funTx.visibility=View.GONE
            binding.manageTx.visibility=View.GONE
            binding.accountTx.text="捐赠积分"
        }else if(type.equals("remain")){
            binding.titlebar.title.text="转增记录"
            binding.integralDataTable.visibility=View.GONE
            binding.typeTx.visibility=View.GONE
            binding.funTx.text="昵称"
            binding.manageTx.text="ID号"
        }

        initPopupWindow()
        binding.titlebar.menu.setOnClickListener {
                if (pwMyPopWindow.isShowing) {
                    pwMyPopWindow.dismiss()// 关闭
                } else {
                    Toast.makeText(this@IntegralActivity,"点击",Toast.LENGTH_LONG).show()
                    var location = intArrayOf(1,2)
                    binding.titlebar.menu.getLocationOnScreen(location)
                    pwMyPopWindow.showAtLocation( binding.titlebar.menu,Gravity.NO_GRAVITY,location[0],location[1]+binding.titlebar.menu.height)// 显示
                }

        }
    }
    /**
     * 获取日期
     * */
    private fun date(){
        var c=Calendar.getInstance()
        var year=c.get(Calendar.YEAR)
        var month=c.get(Calendar.MONTH)+1
        if(month<7){
            for (i in 0..5) {
                var map = HashMap<String, String>()
                if(month==1){
                    month=12
                    year=year - 1
                    map.put("share_key",""+year+"年"+(month-1)+"月")
                    listDate.add(map)
                    continue
                }
                map.put("share_key",""+year+"年"+(month-1)+"月")
                month--
                listDate.add(map)
            }
        }else{
            for (i in 0..5) {
                var map = HashMap<String, String>()
                var num=month-1
                map.put("share_key",""+year+"年"+num+"月")
                println("map: "+map)
                month--
                listDate.add(map)
            }
        }
        listDate.forEach (::println)
    }


    private fun initPopupWindow(){
        date()
        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.task_detail_popupwindow, null)
        lvPopupList = layout.findViewById(R.id.lv_popup_list) as ListView
        pwMyPopWindow = PopupWindow(layout)
        pwMyPopWindow.isFocusable = true
        lvPopupList.adapter = SimpleAdapter(this@IntegralActivity, listDate,
                R.layout.list_item_popupwindow, arrayOf("share_key"),
                intArrayOf(R.id.tv_list_item))
        lvPopupList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this@IntegralActivity,
                    listDate.get(position).get("share_key"),
                    Toast.LENGTH_LONG).show()
        }
            // 控制popupwindow的宽度和高度自适应
            lvPopupList.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED)
            pwMyPopWindow.width = lvPopupList.measuredWidth
            pwMyPopWindow.height = lvPopupList.measuredHeight * NUM_OF_VISIBLE_LIST_ROWS

            // 控制popupwindow点击屏幕其他地方消失
            pwMyPopWindow.setBackgroundDrawable(this.resources.getDrawable(
                    R.drawable.bg_popupwindow))// 设置背景图片，不能在布局中设置，要通过代码来设置
            pwMyPopWindow.isOutsideTouchable = true// 触摸popupwindow外部，popupwindow消失。这个要求你的popupwindow要有背景图片才可以成功，如上


    }






}
