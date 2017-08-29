package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityApplyBuyBackBinding

/**
 * 激励-申请回购
 * */
class ApplyBuyBackActivity : BaseActivity<ActivityApplyBuyBackBinding>() {
    lateinit var context: Context
    lateinit var lvPopupList: ListView
    val NUM_OF_VISIBLE_LIST_ROWS: Int = 3
    lateinit var pwMyPopWindow: PopupWindow
    var listType = ArrayList<HashMap<String, String>>()

    private fun initPopupWindow() {

        var map = HashMap<String, String>()
        map.put("share_key", "现金")
        var map1 = HashMap<String, String>()
        map1.put("share_key", "抵用卷")
        listType.add(map)
        listType.add(map1)

        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.task_detail_popupwindow, null)
        lvPopupList = layout.findViewById(R.id.lv_popup_list) as ListView
        pwMyPopWindow = PopupWindow(layout)
        pwMyPopWindow.isFocusable = true
        lvPopupList.adapter = SimpleAdapter(this@ApplyBuyBackActivity, listType,
                R.layout.list_item_popupwindow, arrayOf("share_key"),
                intArrayOf(R.id.tv_list_item))
        lvPopupList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            if (listType[position].get("share_key").equals("现金")) {
                binding.applyMenu.text = "现金"
                binding.linCard.visibility = View.VISIBLE
                binding.applyBuyMoney.text = "提现金额"
                binding.applyTitleTip.text = "提现手续费5元，并将转换金额的4%捐给基金会"
                binding.applyBuySubmit.text = "确认提现"
            } else {
                binding.applyMenu.text = "抵用卷"
                binding.linCard.visibility = View.GONE
                binding.applyBuyMoney.text = "转换抵用卷金额"
                binding.applyTitleTip.text = "将转换金额的4%捐给基金会"
                binding.applyBuySubmit.text = "确认转换"
            }

            pwMyPopWindow.dismiss()
        }
        // 控制popupwindow的宽度和高度自适应
        lvPopupList.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        pwMyPopWindow.width = lvPopupList.measuredWidth
        pwMyPopWindow.height = lvPopupList.measuredHeight * NUM_OF_VISIBLE_LIST_ROWS

        // 控制popupwindow点击屏幕其他地方消失
        pwMyPopWindow.setBackgroundDrawable(this.resources.getDrawable(
                R.drawable.bg_popupwindow))// 设置背景图片，不能在布局中设置，要通过代码来设置
        pwMyPopWindow.isOutsideTouchable = true// 触摸popupwindow外部，popupwindow消失。这个要求你的popupwindow要有背景图片才可以成功，如上


    }

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.applyBack.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_apply_buy_back)
        context = this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView() {
        binding.applyMenu.text = "现金"
        initPopupWindow()
        binding.applyLinChose.setOnClickListener {
            //切换
            if (pwMyPopWindow.isShowing) {
                pwMyPopWindow.dismiss()// 关闭
            } else {
                var location = intArrayOf(1, 2)
                binding.applyLinChose.getLocationOnScreen(location)
                pwMyPopWindow.showAtLocation(binding.applyLinChose, Gravity.NO_GRAVITY, location[0], location[1] + binding.applyLinChose.height)// 显示
            }

        }

        //查看协议
        binding.applyDeal.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("type", "applyDeal")
            context.startActivity(Intent(context, UserMessageActivity::class.java).putExtras(bundle))
        }

        //切换银行卡
        binding.linCard.setOnClickListener {
            context.startActivity(Intent(context, BankCardActivity::class.java))

        }

        //阅读协议
        binding.applyBuyChose.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.applyBuySubmit.isClickable = true
                binding.applyBuySubmit.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_red))
            } else {
                binding.applyBuySubmit.isClickable = false
                binding.applyBuySubmit.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_button_gray))
            }
        }

        //提交
        binding.applyBuySubmit.setOnClickListener { isNull() }

    }

    /**
     * 验证非空
     * */
    private fun isNull() {
        var msg = ""
        var isNull = true
        if (binding.editMoney.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入金额"
        }
        if (binding.payPwd.text.toString().trim().equals("")) {
            isNull = false
            msg = "请输入支付密码"
        }

        if (isNull) {

        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            return
        }


    }

}
