package com.cndll.shapetest.activity

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.cndll.shapetest.App
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.AppImagesAdapter
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.FileResponse
import com.cndll.shapetest.api.bean.response.HttpCodeResponse
import com.cndll.shapetest.databinding.ActivityAppraiseBinding
import com.cndll.shapetest.tools.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File


/**
 * 评级
 * */
class AppraiseActivity : BaseActivity<ActivityAppraiseBinding>() {
    var photo = PhotoTools()
    var adapter: AppImagesAdapter? = null
    var moreList = ArrayList<ContentValues>()
    var moreListName = ArrayList<String>()
    var moreFiles:File? = null
    lateinit var context: Context
    var rat:Float=4.toFloat()
    var isFish:Boolean=false

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
            rat=rating
        }

        initView()
    }

    //加载控件
    private fun initView() {
        var bundle=this.intent.extras
        var orderId=bundle.getString("order_id")
        var rec_id=bundle.getString("rec_id")
        var opType=bundle.getString("opType")

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
        binding.appraiseSubmit.setOnClickListener {
            var msg=""
            if(binding.appraiseContext.text.toString().trim().equals("")){
                isFish=false
                msg="请输入评价内容"
            }

            if(isFish) {
                httpAppraise(orderId, rec_id,opType)
            }else{
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == photo.ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.data
                var con1 = ContentValues()
                con1.put("img", "file://" + GetPathVideo.getPath(context, uri))
                moreList.add(con1)
                adapter!!.notifyDataSetChanged()
                var bm= ImageFactory.getSmallBitmap(GetPathVideo.getPath(context,uri))
                moreFiles= ImageFactory.saveFile(bm,"shape.jpg")
                uploadFile(moreFiles)
            }
        }
    }

    /**
     * 评价
     * */
    private fun httpAppraise(order_id:String,rec_id:String,opType:String){
        println("order_id:"+order_id+"rec_id:"+rec_id)
        var parmes = HashMap<String,RequestBody>()
        parmes.put("act",toreRequestBody("member_evaluate"))
        parmes.put("op",toreRequestBody(opType))
        parmes.put("key",toreRequestBody(SharedPreferenceUtil.read("key","")))
        parmes.put("order_id",toreRequestBody(order_id))
        parmes.put("goods["+rec_id+"][score]",toreRequestBody(rat.toString()))
        parmes.put("goods["+rec_id+"][comment]",toreRequestBody(binding.appraiseContext.text.toString().trim()))
        for (i in 0..(moreListName.size-1)){
            parmes.put("goods["+rec_id+"][evaluate_image]["+i+"]",toreRequestBody(moreListName[i]))
        }
        AppRequest.getAPI().appraise(parmes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as HttpCodeResponse
                if(t.code==200){
                    Toast.makeText(context,"上传成功",Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(context,"上传失败",Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    /**
     * 上查文件
     * */
    private fun uploadFile(file:File?){
        var parmes=HashMap<String,RequestBody>()
        parmes.put("act",toreRequestBody("sns_album"))
        parmes.put("op",toreRequestBody("file_upload"))
        parmes.put("key",toreRequestBody(SharedPreferenceUtil.read("key","")))
        parmes.put("file\";filename=\""+file!!.name,RequestBody.create(MediaType.parse("image/jpg"), file))
        AppRequest.getAPI().uploadFile(parmes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as FileResponse
                if (t.code==200){
                    Toast.makeText(context,t.datas.file_name,Toast.LENGTH_SHORT).show()
                    moreListName.add(t.datas.file_name)
                    isFish=true
                }else{
                    Toast.makeText(context,t.datas.error,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCompleted() {
                super.onCompleted()
                isFish=true
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
                isFish=true
            }
        })
    }
    private fun toreRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }
}
