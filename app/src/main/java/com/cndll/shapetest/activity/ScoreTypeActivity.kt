package com.cndll.shapetest.activity

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.ScoreTypeAdapter
import com.cndll.shapetest.databinding.ActivityScoreTypeBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.view.AutoListView
import com.cndll.shapetest.view.CHScrollView.CHScrollViewHelper
import java.util.*


class ScoreTypeActivity : BaseActivity<ActivityScoreTypeBinding>(), AutoListView.OnRefreshListener, AutoListView.OnLoadListener, AdapterView.OnItemClickListener {
    /**
     * 下拉刷新
     * */
    override fun onRefresh() {
        loadData(AutoListView.REFRESH)
    }

    /**
     * 上拉加载
     * */
    override fun onLoad() {
        loadData(AutoListView.LOAD)
    }

    /**
     * item点击事件
     * */
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        try {
            val textView = view!!.findViewById(R.id.item_data2) as TextView

            Toast.makeText(this, "你点击了：" + textView.text, Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {

        }

    }

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.back.setOnClickListener { finish() }
        binding.titlebar.menuDate.visibility = View.VISIBLE
        binding.titlebar.menuDate.setImageResource(R.mipmap.date_sache)
    }


    var listDate = ArrayList<HashMap<String, String>>()
    var listTime = ArrayList<HashMap<String, String>>()
    lateinit var pwMyPopWindow: PopupWindow
    private var adapterScore: ScoreTypeAdapter? = null
    lateinit var context: Context
    var moreList = ArrayList<ContentValues>()
    var start_time: String = ""
    var end_time: String = ""
    lateinit var lvPopupList: ListView
    val NUM_OF_VISIBLE_LIST_ROWS: Int = 6


    private var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            val result = msg.obj as List<Map<String, String>>
            when (msg.what) {
                AutoListView.REFRESH -> {
                    binding.scrollList.onRefreshComplete()
                }
                AutoListView.LOAD -> {
                    binding.scrollList.onLoadComplete()
                }
            }
            binding.scrollList.setResultSize(moreList.size)//----第二次的数据程度
            adapterScore!!.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_score_type)
        context = this
        initView()
        initData()
    }

    /***
     *  加载控件
     */
    private fun initView() {
        CHScrollViewHelper.mHScrollViews.clear()
        // 添加头滑动事件
        CHScrollViewHelper.mHScrollViews.add(binding.itemScrollTitle)


        var bundle = this.intent.extras
        var type = bundle.getString("type")

        if (type.equals("subsidiary")) {
            binding.titlebar.title.text = "消费明细"
            binding.integralAllScore.text = "100000000"
            binding.scoreIt1.text = "积分：100"
            binding.scoreIt2.text = "最高可激励消费：100"
            binding.scoreIt3.text = "消费积分：200"


        } else if (type.equals("incentive")) {
            binding.titlebar.title.text = "激励积分"
            binding.integralChose.visibility = View.VISIBLE

            binding.scoreIt1.text = "待激励：100"
            binding.scoreIt2.visibility = View.GONE
            binding.scoreIt3.text = "已激励：200"

            /**
             * 待激励
             * */
            binding.integralWeting.setOnClickListener {
                binding.integralWeting.setTextColor(resources.getColor(R.color.titleRed))
                binding.integralWetingView.visibility = View.VISIBLE
                binding.integralFinish.setTextColor(resources.getColor(R.color.contents_text))
                binding.integralFinishView.visibility = View.GONE
                binding.score2.text = "最高可激励积分"
                binding.score3.text = "今日激励积分"
                binding.score4.text = "剩余待激励积分"


            }
            /***
             * 已激励
             * */
            binding.integralFinish.setOnClickListener {
                binding.integralWeting.setTextColor(resources.getColor(R.color.contents_text))
                binding.integralWetingView.visibility = View.GONE
                binding.integralFinish.setTextColor(resources.getColor(R.color.titleRed))
                binding.integralFinishView.visibility = View.VISIBLE
                binding.score2.text = "已激励积分"
                binding.score3.text = "方式"
                binding.score4.text = "激励积分"
                binding.score5.text = "转增/受赠ID"
                binding.score6.text = "剩余激励积分"
            }

        } else if (type.equals("vouchers")) {
            binding.linScore.visibility = View.GONE

            binding.titlebar.title.text = "使用方式"
            binding.score2.text = "店铺名称"
            binding.score3.text = "通用卷额度"
            binding.score4.text = "转增/受赠ID"
            binding.score5.text = "通用卷额度"
            binding.score6.text = "已使用额度"
            binding.score7.text = "未使用额度"

        }

        moreList.add(0, ContentValues())
        moreList.add(1, ContentValues())
        moreList.add(2, ContentValues())
        adapterScore = ScoreTypeAdapter(context, moreList, binding.scrollList)


        binding.scrollList.adapter = adapterScore
        binding.scrollList.setOnRefreshListener(this)
        binding.scrollList.setOnLoadListener(this)
        binding.scrollList.onItemClickListener = this

        initPopupWindow()
        binding.titlebar.menuDate.setOnClickListener {
            if (pwMyPopWindow.isShowing) {
                pwMyPopWindow.dismiss()// 关闭
            } else {
                var location = intArrayOf(1, 2)
                binding.titlebar.menuDate.getLocationOnScreen(location)
                pwMyPopWindow.showAtLocation(binding.titlebar.menuDate, Gravity.NO_GRAVITY, location[0], location[1] + binding.titlebar.menuDate.height)// 显示
            }


        }
    }


    /**
     * 刷新数据
     * */
    private fun initData() {
        loadData(AutoListView.REFRESH)
    }

    // 测试数据
    fun getData(): List<Map<String, String>> {
        val result = ArrayList<Map<String, String>>()
        var data: MutableMap<String, String>? = null
        for (i in 0..5) {
            data = HashMap<String, String>()
            data.put("title", "Title_" + i)
            data.put("data_" + 1, "Date_" + 1 + "_" + i)
            data.put("data_" + 2, "Date_" + 2 + "_" + i)
            data.put("data_" + 3, "Date_" + 3 + "_" + i)
            data.put("data_" + 4, "Date_" + 4 + "_" + i)
            data.put("data_" + 5, "Date_" + 5 + "_" + i)
            data.put("data_" + 6, "Date_" + 6 + "_" + i)
            result.add(data)
        }
        return result
    }

    private fun loadData(what: Int) {
        // 这里模拟从服务器获取数据
        Thread(Runnable {
            try {
                Thread.sleep(700)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val msg = handler.obtainMessage()
            msg.what = what
            msg.obj = getData()
            handler.sendMessage(msg)
        }).start()
    }

    /**
     * 获取日期------------
     * */
    private fun date() {
        var c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH) + 1
        if (month < 7) {
            for (i in 0..5) {
                var map = HashMap<String, String>()
                var mapTime = HashMap<String, String>()
                if (month == 1) {
                    month = 12
                    year = year - 1
                    map.put("share_key", "" + year + "年" + (month - 1) + "月")
                    mapTime.put("time", year.toString() + "-" + month)
                    listTime.add(mapTime)
                    listDate.add(map)
                    continue
                }
                map.put("share_key", "" + year + "年" + (month - 1) + "月")
                if (month == 11 || month == 10 || month == 12) {
                    mapTime.put("time", year.toString() + "-" + month)
                } else {
                    mapTime.put("time", year.toString() + "-0" + month)
                }
                month--
                listTime.add(mapTime)
                listDate.add(map)
            }
        } else {
            for (i in 0..5) {
                var map = HashMap<String, String>()
                var mapTime = HashMap<String, String>()
                var num = month - 1
                if (num == 11 || num == 10 || num == 12) {
                    mapTime.put("time", year.toString() + "-" + num)
                } else {
                    mapTime.put("time", year.toString() + "-0" + num)
                }
                map.put("share_key", "" + year + "年" + num + "月")
                month--
                listTime.add(mapTime)
                listDate.add(map)
            }
        }
    }


    /**
     * 日期选择----------弹
     * */
    private fun initPopupWindow() {
        date()
        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.task_detail_popupwindow, null)
        lvPopupList = layout.findViewById(R.id.lv_popup_list) as ListView
        pwMyPopWindow = PopupWindow(layout)
        pwMyPopWindow.isFocusable = true
        lvPopupList.adapter = SimpleAdapter(this@ScoreTypeActivity, listDate,
                R.layout.list_item_popupwindow, arrayOf("share_key"),
                intArrayOf(R.id.tv_list_item))
        lvPopupList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            start_time = Constants.dateToStamp(listTime.get(position).get("time").toString())
            end_time = Constants.dateToStamp(Constants.strEndTime(listTime.get(position).get("time").toString()))
            println("start_time:" + start_time + "end_time:" + end_time)
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
}
