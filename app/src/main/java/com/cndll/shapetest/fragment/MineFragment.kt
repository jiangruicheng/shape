package com.cndll.shapetest.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.FragmentMineBinding
import com.cndll.shapetest.view.ObservableScrollView
import com.cndll.shapetest.view.ObservableScrollView.ScrollViewListener


/**
 * Created by Administrator on 2017/7/3 0003.
 */
class MineFragment : BaseFragment<FragmentMineBinding>(){
    var imageHeight:Int=0
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
                imageHeight = binding.mineTop.height
                binding.mineSrcollView.setScrollViewListener(object : ScrollViewListener {
                    override fun onScrollChanged(scrollView: ObservableScrollView, x: Int, y: Int, oldx: Int, oldy: Int) {
                        // TODO Auto-generated method stub
                        if (y <= 0) {
                            binding.mineTop.setBackgroundColor(Color.argb(0.toInt(), 227, 29, 26))//AGB由相关工具获得，或者美工提供
                        } else if (y > 0 && y <= imageHeight) {
                            val scale = y.toFloat() / imageHeight
                            val alpha = 255 * scale
                            // 只是layout背景透明(仿知乎滑动效果)白色透明
                            binding.mineTop.setBackgroundColor(Color.argb(alpha.toInt(), 227, 29, 26))
                        } else {
                            binding.mineTop.setBackgroundColor(Color.argb(255.toInt(), 227, 29, 26))
                        }

                    }
                })

            }
        })
    }

    /**
     * 加载视图
     * */
    private fun initView(){}


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