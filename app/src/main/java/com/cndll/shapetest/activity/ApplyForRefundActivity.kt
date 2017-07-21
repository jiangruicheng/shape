package com.cndll.shapetest.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivityApplyForRefundBinding
import com.cndll.shapetest.tools.Constants
import com.cndll.shapetest.tools.GetPathVideo
import com.cndll.shapetest.tools.PhotoTools
import java.io.File

/**
 * 订单---申请退款
 * */
class ApplyForRefundActivity : BaseActivity<ActivityApplyForRefundBinding>() {
    var photo = PhotoTools()
    lateinit var context: Context
    var type = 1
    var applySimA: File?=null
    var applySimB: File?=null
    var applySimC: File?=null

    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "订单详情"
        binding.titlebar.back.setOnClickListener { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_apply_for_refund)
        context = this
        Constants.verifyStoragePermissions(this@ApplyForRefundActivity)
        initView()
    }

    //加载控件
    private fun initView() {
        // 退货类型
        binding.applyRefundLin.setOnClickListener {
            binding.applyRefund.visibility = View.VISIBLE
            binding.applySalesRefund.visibility = View.GONE
        }
        binding.applySalesRefundLin.setOnClickListener {
            binding.applyRefund.visibility = View.GONE
            binding.applySalesRefund.visibility = View.VISIBLE
        }
        // 收货类型
        binding.applyNotShopLin.setOnClickListener {
            binding.applyNotShop.visibility = View.VISIBLE
            binding.applyShop.visibility = View.GONE
        }
        binding.applyShopLin.setOnClickListener {
            binding.applyNotShop.visibility = View.GONE
            binding.applyShop.visibility = View.VISIBLE
        }
        binding.applyMoney.text = "最多100"
        //输入的金额
        binding.applyRefundPrice.text
        // 退款说明
        binding.applyRefundDetails.text

        binding.applyRefundDetails.addTextChangedListener(mTextWatcher)

        //上传凭证
        binding.applySimA.setOnClickListener {
            type = 1
            photo.getImageFromAlbum(this@ApplyForRefundActivity)
        }
        binding.applySimB.setOnClickListener {
            type = 2
            photo.getImageFromAlbum(this@ApplyForRefundActivity)
        }
        binding.applySimC.setOnClickListener {
            type = 3
            photo.getImageFromAlbum(this@ApplyForRefundActivity)
        }
        binding.submitApplyRefund.setOnClickListener {
            isNull()
        }
        if(binding.applyRefund.visibility == View.VISIBLE){
            Toast.makeText(context,"visibility",Toast.LENGTH_LONG).show()
        }



    }

    //非空判断
    private fun isNull(){
        var isNull=true
        var msg=""
        if(applySimC==null){
            isNull=false
            msg="请上传凭证"
        }
        if(applySimB==null){
            isNull=false
            msg="请上传凭证"
        }
        if(applySimA==null){
            isNull=false
            msg="请上传凭证"
        }
        if(binding.applyRefundDetails.text.toString().trim().equals("")){
            isNull=false
            msg="请输入退款说明"
        }
        if (binding.applyRefundPrice.text.toString().trim().equals("")){
            isNull=false
            msg="请输入退款金额"
        }

        if (isNull){

        }else{
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
            return
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == photo.ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.data
                if(type==1){
                    binding.applySimA.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    applySimA= File(GetPathVideo.getPath(context, uri))
                }
                if(type==2){
                    binding.applySimB.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    applySimB= File(GetPathVideo.getPath(context, uri))
                }
                if(type==3){
                    binding.applySimC.setImageURI("file://" + GetPathVideo.getPath(context, uri))
                    applySimC= File(GetPathVideo.getPath(context, uri))
                }
            }
        }
    }

    internal var mTextWatcher: TextWatcher = object : TextWatcher {
        private var temp: CharSequence? = null
        private var editStart: Int = 0
        private var editEnd: Int = 0

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // TODO Auto-generated method stub
            temp = s
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                       after: Int) {
            // TODO Auto-generated method stub
            //          mTextView.setText(s);//将输入的内容实时显示
        }

        override fun afterTextChanged(s: Editable) {
            // TODO Auto-generated method stub
            editStart = binding.applyRefundDetails.selectionStart
            editEnd = binding.applyRefundDetails.selectionEnd
            binding.applySize.text=temp!!.length.toString() + "/300"
            if (temp!!.length > 300) {
                Toast.makeText(context, "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT).show()
                s.delete(editStart - 1, editEnd)
                val tempSelection = editStart
                binding.applyRefundDetails.text=s
                binding.applyRefundDetails.setSelection(tempSelection)
            }
        }
    }

}
