package com.cndll.shapetest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.FavoriteFragmentBinding

/**
 * Created by Administrator on 2017/7/10 0010.
 */
class FavoriteFragmnet:BaseFragment<FavoriteFragmentBinding>(){
    override fun initBindingVar() {
    }

    var mNumber:Int?=null
    fun newInstance(number:Int):Fragment{
        var bundle= Bundle()
        bundle.putInt("type",number)
        var fragment=FavoriteFragmnet()
        fragment.arguments=bundle
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNumber = arguments.getInt("type")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.favorite_fragment,container)
        mView = binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initView(){


    }
}