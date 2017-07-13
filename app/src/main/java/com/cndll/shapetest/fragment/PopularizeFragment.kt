package com.cndll.shapetest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.FragmentPopularizeBinding
import com.cndll.shapetest.zixing.encode.CodeCreator

/**
 * Created by Administrator on 2017/7/10 0010.
 */
class PopularizeFragment : BaseFragment<FragmentPopularizeBinding>(){

    var mNumber:Int?=null
    fun newInstance(number: Int): Fragment {
        var bundle=Bundle()
        bundle.putInt("type",number)
        val fragment = PopularizeFragment()
        fragment.arguments=bundle
        return fragment
    }



    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNumber = arguments.getInt("type")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.fragment_popularize,container)
        mView=binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 加载视图
     * */
    private fun initView(){
        if(mNumber==1){
            val bitmap = CodeCreator.createQRCode("123456")
            binding.popularImage.setImageBitmap(bitmap)
        }

    }

}