package com.cndll.shapetest.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.ApplyActivity
import com.cndll.shapetest.activity.SettingActivity
import com.cndll.shapetest.databinding.FragmentMineBinding
import com.cndll.shapetest.view.ObservableScrollView
import com.cndll.shapetest.view.ObservableScrollView.ScrollViewListener


/**
 * Created by Administrator on 2017/7/3 0003.
 */
class MineFragment : BaseFragment<FragmentMineBinding>(){
    var imageHeight:Int=300
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.fragment_mine,container)
        mView = binding.root
        initSrco()
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 加载滑动标题栏变色
     * */
   private fun initSrco(){
        var vto=binding.mineIconTop.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.mineIconTop.viewTreeObserver.removeGlobalOnLayoutListener(this)
//                imageHeight = binding.mineTop.height
                binding.mineSrcollView.setScrollViewListener(object : ScrollViewListener {
                    override fun onScrollChanged(scrollView: ObservableScrollView, x: Int, y: Int, oldx: Int, oldy: Int) {
                        // TODO Auto-generated method stub
                        if (y <= 0) {
                            binding.mineTop.setBackgroundColor(Color.argb(0.toInt(), 0xfd, 0x91, 0x5b))//AGB由相关工具获得，或者美工提供
                        } else if (y > 0 && y <= imageHeight) {
                            val scale = y.toFloat() / imageHeight
                            val alpha = 255 * scale
                            // 只是layout背景透明(仿知乎滑动效果)白色透明
                            binding.mineTop.setBackgroundColor(Color.argb(alpha.toInt(), 0xfd, 0x91, 0x5b))
                        } else {
                            binding.mineTop.setBackgroundColor(Color.argb(255.toInt(), 0xfd, 0x91, 0x5b))
                        }

                    }
                })

            }
        })
    }

    /**
     * 加载视图---点击事件处理
     * */
    private fun initView(){
        //申请成为业务员
        binding.mineSalesMan.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("type", "sales")
            context.startActivity(Intent(context,ApplyActivity::class.java).putExtras(bundle))
        }
        //申请服务合伙人
        binding.mineLinPartner.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "par")
            context.startActivity(Intent(context,ApplyActivity::class.java).putExtras(bundle))
        }
        //申请管理合伙人
        binding.mineLinManaging.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "manag")
            context.startActivity(Intent(context,ApplyActivity::class.java).putExtras(bundle))
        }
        //申请商家
        binding.mineLinApply.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "apply")
            context.startActivity(Intent(context,ApplyActivity::class.java).putExtras(bundle)) }
        //我的拼团
        binding.mineLinBooking.setOnClickListener {  }
        //我的推广
        binding.mineLinGeneralize.setOnClickListener {  }
        //我的抵用卷
        binding.mineLinVouchers.setOnClickListener {  }
        //预约订单
        binding.mineLinAdvance.setOnClickListener {  }
        //设置
        binding.mineSetting.setOnClickListener{
            context.startActivity(Intent(context,SettingActivity::class.java))
        }
        //二维码处理
        binding.mineZixingCode.setOnClickListener {  }
        //消息
        binding.mineNews.setOnClickListener {  }
    }


    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        fun newInstance(param1: String, param2: String): MineFragment {
            val fragment = MineFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }



}