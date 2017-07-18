package com.cndll.shapetest.activity

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.AppImagesAdapter
import com.cndll.shapetest.databinding.ActivityAppraiseBinding


/**
 * 评级
 * */
class AppraiseActivity : BaseActivity<ActivityAppraiseBinding>() {
    var adapter:AppImagesAdapter?=null
    var moreList=ArrayList<ContentValues>()
    lateinit var context:Context
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text="评价"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_appraise)
        context=this
        binding.starApp.rating=4.0.toFloat()
        binding.starApp.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this@AppraiseActivity, "评价了" + rating + "星", Toast.LENGTH_SHORT).show()
        }

        initView()
    }
    //加载控件
    private fun initView(){
        var con=ContentValues()
        moreList.add(0,con)

        if(adapter==null){
            adapter= AppImagesAdapter(context,moreList)
            binding.appImgsList.adapter=adapter
        }
        binding.appImgsList.setOnItemClickListener { parent, view, position, id ->
            if(position==0){
                var con1=ContentValues()
                con1.put("img","http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
                moreList.add(con1)
                adapter!!.notifyDataSetChanged()
                Toast.makeText(context, "只能添加三张照片！", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
