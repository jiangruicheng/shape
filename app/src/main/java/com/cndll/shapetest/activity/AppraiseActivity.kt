package com.cndll.shapetest.activity

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.AppImagesAdapter
import com.cndll.shapetest.databinding.ActivityAppraiseBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.GetPathVideo
import com.cndll.shapetest.tools.PhotoTools
import java.io.File


/**
 * 评级
 * */
class AppraiseActivity : BaseActivity<ActivityAppraiseBinding>() {
    var photo = PhotoTools()
    var adapter: AppImagesAdapter? = null
    var moreList = ArrayList<ContentValues>()
    var moreFiles = ArrayList<File>()
    lateinit var context: Context
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "评价"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)//控制键盘
        initBinding(R.layout.activity_appraise)
        Constants.verifyStoragePermissions(this@AppraiseActivity)
        context = this
        binding.starApp.rating = 4.0.toFloat()
        binding.starApp.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this@AppraiseActivity, "评价了" + rating + "星", Toast.LENGTH_SHORT).show()
        }

        initView()
    }

    //加载控件
    private fun initView() {
        var con = ContentValues()
        moreList.add(0, con)

        if (adapter == null) {
            adapter = AppImagesAdapter(context, moreList)
            binding.appImgsList.adapter = adapter
        }
        binding.appImgsList.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                if (moreList.size >= 4) {
                    Toast.makeText(context, "只能添加三张照片！", Toast.LENGTH_SHORT).show()
                } else {
                    photo.getImageFromAlbum(this@AppraiseActivity)
                }
            }
        }

        //提交参数
        binding.appraiseSubmit.setOnClickListener {  }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == photo.ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.data
                var con1 = ContentValues()
                con1.put("img", "file://" + GetPathVideo.getPath(context, uri))
                moreList.add(con1)
                adapter!!.notifyDataSetChanged()
                var file=File(GetPathVideo.getPath(context, uri))
                moreFiles.add(file)
            }
        }
    }

}
