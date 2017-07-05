package com.cndll.shapetest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.TableDataLayoutBinding

/**
 * Created by Administrator on 2017/7/4 0004.
 * 数据
 */
class TableDataFragment : BaseFragment<TableDataLayoutBinding>() {
    override fun initBindingVar() {
    }

    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.table_data_layout, container)
        mView = binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    /**
    * 加载控件
    * */
    private fun initView(){
        binding.titlebar.root.setBackgroundResource(R.color.titleRed)
        binding.titlebar.back.visibility=View.INVISIBLE
        binding.titlebar.title.text="众享消费（消费激励）"
        binding.titlebar.title.setTextColor(R.color.white)
    }


    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        fun newInstance(param1: String, param2: String): TableDataFragment {
            val fragment = TableDataFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}

