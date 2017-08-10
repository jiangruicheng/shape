package com.cndll.shapetest.fragment


import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.cndll.shapetest.R
import com.cndll.shapetest.RXbus.EventType
import com.cndll.shapetest.RXbus.RxBus
import com.cndll.shapetest.activity.ResultActivity
import com.cndll.shapetest.api.bean.response.ClassItemResponse
import com.cndll.shapetest.weight.VLayoutHelper
import rx.Observer
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [ClassificationItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassificationItemFragment : BaseVlayoutFragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    val item = ArrayList<ClassItemResponse.DatasBean>()
    val titlePositions = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun init() {
        super.init()
        Looper.myLooper()
        RxBus.getDefault().toObservable(EventType::class.java).subscribe(object : Observer<EventType> {
            override fun onNext(t: EventType?) {
                if (t != null && t.type == EventType.LISTCLASS) {
                    val position = titlePositions[t.extra.toInt()]
                    (recycler.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, 0)
                }
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }

        })
    }

    override fun scrollToDo() {
        super.scrollToDo()
        //Toast.makeText(context, (recycler.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition().toString(), Toast.LENGTH_SHORT).show()

        if (titlePositions.contains((recycler.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())) {
            RxBus.getDefault().post(EventType().setType(EventType.LISTTAB).setExtra(titlePositions.indexOf((recycler.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()).toString()))
            // Toast.makeText(context, (recycler.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition().toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun loadOver() {
        super.loadOver()
    }

    var titlePosition = 0
    override fun setVLayout() {
        titlePositions.add(0)
        for (i in 0..9) {
            adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                    setContext(context).
                    setCount(1).
                    setLayoutHelper(LinearLayoutHelper()).
                    setViewType(5).
                    setRes(R.layout.classification_head).
                    setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            windowManager.defaultDisplay.height / 10)).
                    setOnBindView({ itemView, position ->

                    }).creatAdapter())
            val detailHelper = GridLayoutHelper(3)
            detailHelper.setAutoExpand(false)
            adapter.addAdapter(object : VLayoutHelper.Builder() {}.
                    setContext(context).
                    setCount(11).
                    setLayoutHelper(detailHelper).
                    setViewType(6).
                    setRes(R.layout.classfication_body).
                    setParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            windowManager.defaultDisplay.height / 12)).
                    setOnBindView({ itemView, position ->
                        itemView.itemView.setOnClickListener { context.startActivity(Intent(context, ResultActivity::class.java).putExtra(ResultActivity.MODE, ResultActivity.SEARCH).putExtra(ResultActivity.TYPE, ResultActivity.COMMODIYT)) }
                    }).setOnBindViewOffset({ itemView, position ->
                (itemView.itemView.findViewById(R.id.text) as TextView).setText(position.toString())
            }).creatAdapter())
            titlePosition = titlePosition + 12
            titlePositions.add(titlePosition)
        }
    }

    override fun pullData(mode: Int): Boolean {
        return super.pullData(mode)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment ClassificationItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): ClassificationItemFragment {
            val fragment = ClassificationItemFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


}// Required empty public constructor
