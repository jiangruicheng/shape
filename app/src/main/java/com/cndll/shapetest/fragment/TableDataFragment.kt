package com.cndll.shapetest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.TableDataLayoutBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.view.StereoView

/**
 * Created by Administrator on 2017/7/4 0004.
 * 数据
 */
class TableDataFragment : BaseFragment<TableDataLayoutBinding>() {
    private var translateX: Int = 0
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
        binding.titlebar.title.setTextColor(resources.getColor(R.color.white))


        binding.str.setStartScreen(1)
        binding.str.post(Runnable {
            val location = IntArray(2)
            binding.str.getLocationOnScreen(location)
            translateX = location[1]
        })

            //滑动的页数
//        binding.str.setiStereoListener(object : StereoView.IStereoListener() {
//            fun toPre(curScreen: Int) {
//            }
//
//            fun toNext(curScreen: Int) {
//            }
//        })
        //翻页-----半数
//        binding.ssa.setOnClickListener {
//            binding.str.setItem(2)
//            binding.str.toPre()
//            Constants.startExitAnim(binding.str,translateX)
//        }
//        binding.tts.setOnClickListener { binding.str.setItem(1)
//            binding.str.toPre()
//            Constants.startExitAnim(binding.str,translateX)
//        }
//        binding.sxd.setOnClickListener { binding.str.setItem(0)
//            binding.str.toPre()
//            Constants.startExitAnim(binding.str,translateX)
//        }

        binding.dataTime.text="2017 年 6 月 15 号"
        //会员积分值
        binding.dataMemberInter.text="60%"
        //商家积分值
        binding.dataShopInter.text="40%"

        //会员人数
        binding.dataMemberNum.text="88万"
        //店铺总数量
        binding.dataShopNum.text="18万"

        //当日收到捐款额
        binding.dataDayAccount.text="20W"
        //累计收到捐款额
        binding.dataReceiveAccount.text="20W"
        //累计投放捐赠额
        binding.dataPutInAccount.text="5W"

        //? 查看列表
        binding.dataCountDetails.setOnClickListener {  }

        httpDatas()
    }

    /**
     * 获取数据
     * */
    private fun httpDatas(){
        Toast.makeText(context,"获取数据",Toast.LENGTH_LONG).show()
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

