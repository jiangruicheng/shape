package com.cndll.shapetest.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cndll.shapetest.R
import com.cndll.shapetest.databinding.ActivitySettingBinding
import com.cndll.shapetest.tools.FilesUtlis


/**
 * 设置-主界面
 * */
class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    lateinit var context:Context
    var futils=FilesUtlis()
    override fun initBindingVar() {
    }

    override fun initTitle() {
        binding.titlebar.title.text = "设置"
        binding.titlebar.back.setOnClickListener{
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_setting)
        context=this
        initView()
    }

    /**
     * 加载控件
     * */
    private fun initView(){
        /** 清楚缓存 */
        binding.setAllCache.text=futils.getTotalCacheSize(context)
        binding.setAllCache.setOnClickListener {
            futils.clearAllCache(context)
            binding.setAllCache.text="0M"
        }
        /** 版本 */
        binding.setVersion.text=futils.getVersion(context)

        /** 地址管理 */
        binding.setAddress.setOnClickListener{
            context.startActivity(Intent(context,AddressActivity::class.java))
        }
        // 绑定手机号
        binding.setPhone.setOnClickListener{
            var bundle=Bundle()
            bundle.putString("type","bPhone")
            context.startActivity(Intent(context,SetPhoneActivity::class.java).putExtras(bundle))
        }
        //修改密码
        binding.setUpdatePwd.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("type", "pwd")
            context.startActivity(Intent(context,SetPwdActivity::class.java).putExtras(bundle))
        }
        //推送消息
        binding.mTogBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                println("选中")
                context.startActivity(Intent(context,AuthenticationActivity::class.java))
            }else{
                println("未选中")
            }
        }
        //关于我们
        binding.setUsers.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "users")
            context.startActivity(Intent(context,SetPwdActivity::class.java).putExtras(bundle))
        }
        //退出登录
        binding.outLogin.setOnClickListener { finish() }
    }

}
