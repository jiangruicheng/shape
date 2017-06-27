package com.cndll.shapetest.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

/**
 * Created by Administrator on 2017/6/27 0027.
 *  动画
 */
class ObservableScrollView : ScrollView{

    interface ScrollViewListener{
        fun onScrollChanged(scrollView: ObservableScrollView, x: Int, y: Int,oldx: Int, oldy: Int)
    }

    private var scrollViewListener:ScrollViewListener?=null
    constructor(context: Context) : super(context)
    constructor(context: Context,attributeSet: AttributeSet) : super(context,attributeSet)
    constructor(context: Context,attributeSet: AttributeSet,defStyle:Int) : super(context,attributeSet,defStyle)
    fun setScrollViewListener(scrollViewListener: ScrollViewListener) {
        this.scrollViewListener = scrollViewListener
    }
    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if(scrollViewListener!=null){
            scrollViewListener!!.onScrollChanged(this,l, t, oldl, oldt);
    }}

}