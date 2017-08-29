package com.cndll.shapetest.fragment


import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.activity.AddressActivity
import com.cndll.shapetest.activity.VouchersActivity
import com.cndll.shapetest.adapter.VLayoutAdapter
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.GetOrderInfoResponse
import com.cndll.shapetest.bean.AddressMode
import com.cndll.shapetest.bean.OrderInfoMode
import com.cndll.shapetest.bean.anno.Voluation
import com.cndll.shapetest.databinding.ItemOrderAdressBinding
import com.cndll.shapetest.databinding.ItemOrderInfoBinding
import com.cndll.shapetest.databinding.ItemOrderPaymethodBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import com.cndll.shapetest.weight.VLayoutHelper


/**
 * A simple [Fragment] subclass.
 * Use the [AddOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddOrderFragment : BaseVlayoutFragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    val addressMode = AddressMode()
    lateinit var payMethodAdapter: VLayoutAdapter
    override fun init() {
        super.init()
        val l = LinearLayoutHelper()
        l.setDividerHeight(2)
        l.setMargin(0, 12, 0, windowManager.defaultDisplay.height / 5)
        l.setPadding(12, 0, 12, 0)
        payMethodAdapter = object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(3).
                setLayoutHelper(l).
                setViewType(6).
                setRes(R.layout.item_order_paymethod).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 10)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemOrderPaymethodBinding
                    itemView.itemView.setOnClickListener {
                        payChoose = position
                        payMethodAdapter.notifyDataSetChanged()
                    }
                    if (position == payChoose) {
                        binding.choose.visibility = View.VISIBLE
                    } else {
                        binding.choose.visibility = View.GONE
                    }
                    var drawable: Drawable? = null
                    when (position) {
                        (0) -> {
                            drawable = resources.getDrawable(R.mipmap.wecaticon)
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                                    drawable.getMinimumHeight())
                            binding.title.setText("微信支付")
                        }
                        (1) -> {
                            drawable = resources.getDrawable(R.mipmap.alipayicon)
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                                    drawable.getMinimumHeight())
                            binding.title.setText("支付宝支付")
                        }
                        (2) -> {
                            drawable = resources.getDrawable(R.mipmap.uniicon)
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                                    drawable.getMinimumHeight())
                            binding.title.setText("银联支付")
                        }
                    }
                    binding.title.setCompoundDrawables(drawable, null, null, null)
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter()
    }

    var payChoose = 0
    val voucher = OrderInfoMode()
    val score = OrderInfoMode()
    override fun setVLayout() {
        super.setVLayout()
        val mScrollFixLayoutHelperB = ScrollFixLayoutHelper(ScrollFixLayoutHelper.BOTTOM_RIGHT, 0, 0)
        mScrollFixLayoutHelperB.showType = ScrollFixLayoutHelper.SHOW_ALWAYS
        /*val l4 = LinearLayoutHelper()
        l4.setDividerHeight(2)
        l4.setMargin(0, 12, 0, 12)
        l4.setPadding(12, 0, 12, 0)*/
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(mScrollFixLayoutHelperB).
                setViewType(8).
                setRes(R.layout.item_order_pay).
                setParams(ViewGroup.LayoutParams(windowManager.defaultDisplay.width,
                        windowManager.defaultDisplay.height / 14)).
                setOnBindView({ itemView, position ->

                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val l = LinearLayoutHelper()
        l.setDividerHeight(2)
        l.setMargin(0, 12, 0, 12)
        l.setPadding(12, 0, 12, 0)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(l).
                setViewType(4).
                setRes(R.layout.item_order_adress).
                setParams(ViewGroup.LayoutParams(windowManager.defaultDisplay.width,
                        windowManager.defaultDisplay.height / 7)).
                setOnBindView({ itemView, position ->
                    itemView.itemView.setOnClickListener { activity.startActivity(Intent(activity, AddressActivity::class.java)) }
                    val binding = itemView.dataBinding as ItemOrderAdressBinding
                    addressMode.setName("王小二")
                    addressMode.setTel("15001372759")
                    addressMode.setAddress("打发地方大师傅")
                    addressMode.setArea("盛大的首发得分")
                    binding.info = addressMode
                    pullData(MODE_PULL)
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val l1 = LinearLayoutHelper()
        l1.setDividerHeight(2)
        l1.setMargin(0, 12, 0, 0)
        l1.setPadding(12, 0, 12, 0)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(l1).
                setViewType(5).
                setRes(R.layout.item_order_commodity).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 6)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val l2 = LinearLayoutHelper()
        l2.setDividerHeight(2)
        l2.setMargin(0, 0, 0, 0)
        l2.setPadding(12, 0, 12, 0)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(1).
                setLayoutHelper(l2).
                setViewType(6).
                setRes(R.layout.item_order_count).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 10)).
                setOnBindView({ itemView, position ->
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        val l3 = LinearLayoutHelper()
        l3.setDividerHeight(2)
        l3.setMargin(0, 2, 0, 24)
        l3.setPadding(12, 0, 12, 0)
        adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                setContext(context).
                setCount(3).
                setLayoutHelper(l3).
                setViewType(7).
                setRes(R.layout.item_order_info).
                setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        windowManager.defaultDisplay.height / 10)).
                setOnBindView({ itemView, position ->
                    val binding = itemView.dataBinding as ItemOrderInfoBinding
                    val drawable = resources.getDrawable(R.mipmap.right)
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                            drawable.getMinimumHeight())
                    when (position) {
                        (0) -> {
                            voucher.title = "抵用券"
                            voucher.orderInfo = "无"
                            binding.info = voucher
                            binding.content.setCompoundDrawables(null, null, drawable, null)
                            itemView.itemView.setOnClickListener { activity.startActivity(Intent(activity, VouchersActivity::class.java).putExtra("type", "voucher")) }
                        }
                        (1) -> {
                            score.title = "消费积分"
                            score.orderInfo = "无"
                            binding.info = score
                        }
                        (2) -> {
                            val l = OrderInfoMode()
                            l.title = "运费"
                            l.orderInfo = "免运费"
                            binding.info = l

                        }
                    }
                    // val imageView: SimpleDraweeView = itemView.findViewById(R.id.image) as SimpleDraweeView
                }).creatAdapter())
        adapter.addAdapter(payMethodAdapter)
    }

    override fun pullData(mode: Int): Boolean {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().getOrderInfo(SharedPreferenceUtil.read("key", "")), {
            baseResponse ->
            baseResponse as GetOrderInfoResponse
            val v = Voluation<GetOrderInfoResponse.DatasBean.AddressInfoBean, AddressMode>()
            v.setVariBind(baseResponse.datas.address_info, addressMode)
            voucher.orderInfo = (baseResponse.datas.redpacket_voucher.toInt() + baseResponse.datas.score_voucher).toString()
            score.orderInfo = baseResponse.datas.shop_score
        })
        return true
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        val FLAG = "提交订单"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment AddOrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): AddOrderFragment {
            val fragment = AddOrderFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            fragment.isShowLastItem = false
            fragment.isBackTop = false
            fragment.isDownRefresh = false
            return fragment
        }
    }


}// Required empty public constructor
