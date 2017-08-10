package com.cndll.shapetest.fragment

import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cndll.shapetest.R
import com.cndll.shapetest.adapter.VouchersAdapter
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.BaseObservable
import com.cndll.shapetest.api.bean.BaseResponse
import com.cndll.shapetest.api.bean.response.ScoreIndexResponse
import com.cndll.shapetest.api.bean.response.VouchersResponse
import com.cndll.shapetest.databinding.FragmentVouchersBinding
import com.cndll.shapetest.tools.SharedPreferenceUtil
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2017/8/2 0002.
 * 抵用卷
 */

class VouchersFragment : BaseFragment<FragmentVouchersBinding>() {
    var adapter: VouchersAdapter? = null
    var moreList = ArrayList<VouchersResponse.DatasBean>()
    var mNumber: Int? = null
    fun newInstance(number: Int): Fragment {
        var bundle = Bundle()
        bundle.putInt("type", number)
        val fragment = VouchersFragment()
        fragment.arguments = bundle
        return fragment
    }

    override fun initBindingVar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNumber = arguments.getInt("type")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(R.layout.fragment_vouchers, null)
        mView = binding.root
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 加载数据
     * */
    private fun initView() {
        if (mNumber == 1) {
            if (adapter == null) {
                adapter = VouchersAdapter(moreList, context, 1, null)
                binding.vouchersList.adapter = adapter
            }
            httpVouchersRed()
        } else if (mNumber == 2) {
            httpVouchers()
        }

    }

    /**
     * 红包抵用卷
     * */
    private fun httpVouchersRed() {

        AppRequest.getAPI().vouchersRed("voucher", "redPacket", SharedPreferenceUtil.read("key", "")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as VouchersResponse
                if (t.code == 200) {
                    moreList.addAll(t.datas)
                    adapter!!.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, t.error_massage, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }
        })
    }

    /***
     * 通用抵用卷
     * */
    private fun httpVouchers() {
        AppRequest.getAPI().vouchers("voucher", "score_voucher", SharedPreferenceUtil.read("key", "")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : BaseObservable() {
            override fun onNext(t: BaseResponse?) {
                super.onNext(t)
                t as ScoreIndexResponse
                if (t.code == 200) {
                    var vou = VouchersResponse.DatasBean()
                    moreList.add(0, vou)
                    if (adapter == null) {
                        adapter = VouchersAdapter(moreList, context, 2, t.datas.score_voucher)
                        binding.vouchersList.adapter = adapter
                    }
                } else {
                    Toast.makeText(context, t.error_massage, Toast.LENGTH_SHORT).show()
                }


            }

            override fun onCompleted() {
                super.onCompleted()
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                e!!.printStackTrace()
            }
        })
    }

}
