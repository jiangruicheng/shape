package com.cndll.shapetest.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.*
import com.cndll.shapetest.databinding.FragmentMineBinding
import com.cndll.shapetest.view.ObservableScrollView
import com.cndll.shapetest.view.ObservableScrollView.ScrollViewListener
import com.cndll.shapetest.zixing.android.CaptureActivity


/**
 * Created by Administrator on 2017/7/3 0003.
 */
class MineFragment : BaseFragment<FragmentMineBinding>(){
    var imageHeight:Int=300
    var REQUEST_CODE_SCAN:Int=0x0000
    var DECODED_CONTENT_KEY:String="codedContent"
    var DECODED_BITMAP_KEY:String="codedBitmap"
    private var mParam1: String? = null
    private var mParam2: String? = null
    val bundle = Bundle()
    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.fragment_mine,container)
        mView = binding.root
        initSrco()
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 加载滑动标题栏变色
     * */
   private fun initSrco(){
        var vto=binding.mineIconTop.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.mineIconTop.viewTreeObserver.removeGlobalOnLayoutListener(this)
//                imageHeight = binding.mineTop.height
                binding.mineSrcollView.setScrollViewListener(object : ScrollViewListener {
                    override fun onScrollChanged(scrollView: ObservableScrollView, x: Int, y: Int, oldx: Int, oldy: Int) {
                        // TODO Auto-generated method stub
                        if (y <= 0) {
                            binding.mineTop.setBackgroundColor(Color.argb(0.toInt(), 0xfd, 0x91, 0x5b))//AGB由相关工具获得，或者美工提供
                        } else if (y > 0 && y <= imageHeight) {
                            val scale = y.toFloat() / imageHeight
                            val alpha = 255 * scale
                            // 只是layout背景透明(仿知乎滑动效果)白色透明
                            binding.mineTop.setBackgroundColor(Color.argb(alpha.toInt(), 0xfd, 0x91, 0x5b))
                        } else {
                            binding.mineTop.setBackgroundColor(Color.argb(255.toInt(), 0xfd, 0x91, 0x5b))
                        }

                    }
                })

            }
        })
    }

    /**
     * 加载视图---点击事件处理
     * */
    private fun initView(){
        // 头像，个人资料
        binding.mineIcon.setImageURI("http://qmy.51edn.com/upload/images/20170706/cbea4d76ccbebfe8bdc3d3735ac690ce.jpg")
        binding.mineIcon.setOnClickListener {
            context.startActivity(Intent(context,UserInfoActivity::class.java))
        }
        //申请成为业务员
        binding.mineSalesMan.setOnClickListener{
            bundle.putString("type", "sales")
            context.startActivity(Intent(context,ApplyActivity::class.java).putExtras(bundle))
        }
        //申请服务合伙人
        binding.mineLinPartner.setOnClickListener {
            bundle.putString("type", "par")
            context.startActivity(Intent(context,ApplyActivity::class.java).putExtras(bundle))
        }
        //申请管理合伙人
        binding.mineLinManaging.setOnClickListener {
            bundle.putString("type", "manag")
            context.startActivity(Intent(context,ApplyActivity::class.java).putExtras(bundle))
        }
        //申请商家
        binding.mineLinApply.setOnClickListener {
            bundle.putString("type", "apply")
            context.startActivity(Intent(context,ApplyActivity::class.java).putExtras(bundle)) }
        //我的拼团
        binding.mineLinBooking.setOnClickListener {
            bundle.putString("type", "booking")
            context.startActivity(Intent(context,VouchersActivity::class.java).putExtras(bundle))
        }
        //我的推广
        binding.mineLinGeneralize.setOnClickListener {
            context.startActivity(Intent(context,PopularizeActivity::class.java))
        }
        //我的抵用卷
        binding.mineLinVouchers.setOnClickListener {
            bundle.putString("type", "voucher")
            context.startActivity(Intent(context,VouchersActivity::class.java).putExtras(bundle))
        }
        //预约订单
        binding.mineLinAdvance.setOnClickListener {
            bundle.putString("type", "advance")
            context.startActivity(Intent(context,VouchersActivity::class.java).putExtras(bundle))
        }
        //设置
        binding.mineSetting.setOnClickListener{
            context.startActivity(Intent(context,SettingActivity::class.java))
        }
        //二维码处理
        binding.mineZixingCode.setOnClickListener {
            startActivityForResult(Intent(context,CaptureActivity::class.java),REQUEST_CODE_SCAN)
        }
        //消息
        binding.mineNews.setOnClickListener {
            //////////////////////////////
            context.startActivity(Intent(context,AppraiseActivity::class.java))
        }

        //我的激励
        binding.mineStimu.setOnClickListener {
            context.startActivity(Intent(context,StimulateActivity::class.java))
        }
        //我的红包记录
        binding.mineLinPack.setOnClickListener {
        context.startActivity(Intent(context,RedPacketActivity::class.java))
        }
        //我的收藏
        binding.mineLinCollect.setOnClickListener {
            bundle.putString("type", "collect")
            context.startActivity(Intent(context,FavoriteActivity::class.java).putExtras(bundle))
        }
        //退貨，換貨
        binding.mineLinRefund.setOnClickListener {
            bundle.putString("type", "refund")
            context.startActivity(Intent(context,FavoriteActivity::class.java).putExtras(bundle))
        }
        //查看更多
        binding.mineAllOrder.setOnClickListener {
            bundle.putInt("count", 0)
            context.startActivity(Intent(context,OrdersListActivity::class.java).putExtras(bundle)) }
        //待付款
        binding.mineLinPayment.setOnClickListener {
            bundle.putInt("count", 1)
            context.startActivity(Intent(context,OrdersListActivity::class.java).putExtras(bundle)) }
        //待发货
        binding.mineLinShipments.setOnClickListener {
            bundle.putInt("count", 2)
            context.startActivity(Intent(context,OrdersListActivity::class.java).putExtras(bundle)) }
        //待收货
        binding.mineLinGoods.setOnClickListener {
            bundle.putInt("count", 3)
            context.startActivity(Intent(context,OrdersListActivity::class.java).putExtras(bundle)) }
        //待激励
        binding.mineLinAware.setOnClickListener {
            bundle.putInt("count", 4)
            context.startActivity(Intent(context,OrdersListActivity::class.java).putExtras(bundle)) }
        //待评价
        binding.mineLinRate.setOnClickListener {
            bundle.putInt("count", 5)
            context.startActivity(Intent(context,OrdersListActivity::class.java).putExtras(bundle)) }
    }


    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        fun newInstance(param1: String, param2: String): MineFragment {
            val fragment = MineFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==REQUEST_CODE_SCAN && resultCode == Activity.RESULT_OK){
            if(data!=null){
                val content = data.getStringExtra(DECODED_CONTENT_KEY)
                //返回生成的二维码
//                val bitmap = data.getParcelableExtra<Bitmap>(DECODED_BITMAP_KEY)

                println("content:"+content)

            }
        }
    }


}