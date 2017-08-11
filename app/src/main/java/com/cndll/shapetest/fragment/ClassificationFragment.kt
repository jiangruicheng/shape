package com.cndll.shapetest.fragment


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.BaseExpandableListAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.cndll.shapetest.R
import com.cndll.shapetest.RXbus.EventType
import com.cndll.shapetest.RXbus.RxBus
import com.cndll.shapetest.activity.SearchActivity
import com.cndll.shapetest.api.ApiUtill
import com.cndll.shapetest.api.AppRequest
import com.cndll.shapetest.api.bean.response.ClassItemResponse
import com.cndll.shapetest.databinding.FragmentClassificationBinding
import kotlinx.android.synthetic.main.fragment_classification.*
import rx.Observer


/**
 * A simple [Fragment] subclass.
 * Use the [ClassificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassificationFragment : BaseFragment<FragmentClassificationBinding>() {
    override fun initBindingVar() {

    }

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    var select = 0

    var gridHeight = 0
    lateinit var tabAdapter: BaseAdapter
    lateinit var exAdapter: BaseExpandableListAdapter
    val titleList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    val classFragment = ClassificationItemFragment.newInstance("", "")
    fun setClassification() {
        activity.supportFragmentManager.beginTransaction().add(R.id.classic, classFragment).commit()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        initBinding(R.layout.fragment_classification, container)
        mView = binding.root
        gridHeight = activity.windowManager.defaultDisplay.height / 9
        initTitle()
        initTab()
        setClassification()
        initBusEvent()
        pullData()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun initBusEvent() {
        RxBus.getDefault().toObservable(EventType::class.java).subscribe(object : Observer<EventType> {
            override fun onCompleted() {

            }

            override fun onNext(t: EventType?) {
                if (t != null && t.type == EventType.LISTTAB) {
                    select = t.extra.toInt()
                    tablayout.smoothScrollToPosition(t.extra.toInt())
                    tabAdapter.notifyDataSetChanged()

                }
            }

            override fun onError(e: Throwable?) {

            }

        })
    }

    val classMode: ArrayList<ClassItemResponse.DatasBean> = ArrayList()
    fun pullData() {
        ApiUtill.getInstance().getApi(AppRequest.getAPI().ClassPage(), {
            baseResponse ->
            baseResponse as ClassItemResponse
            if (classMode.isNotEmpty()) {
                classMode.clear()
            }
            classMode.addAll(baseResponse.datas)
            tabAdapter.notifyDataSetChanged()
            classFragment.setData(baseResponse.datas as ArrayList<ClassItemResponse.DatasBean>)
        })
    }

    fun initTitle() {
        binding.titlebar.root.setBackgroundResource(R.color.titleRed)
        binding.titlebar.title.text = "众享消费"
        binding.titlebar.title.setTextColor(Color.WHITE)
        binding.titlebar.back.visibility = View.INVISIBLE
        binding.titlebar.menuDate.visibility = View.VISIBLE
        val drawable = resources.getDrawable(R.mipmap.search_title)
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight())
        binding.titlebar.menuDate.setImageDrawable(drawable)
        binding.titlebar.menuDate.setBackgroundResource(R.color.titleRed)
        binding.titlebar.menuDate.setOnClickListener { activity.startActivity(Intent(activity, SearchActivity::class.java)) }
    }

    fun initTab() {

        tabAdapter = object : BaseAdapter() {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val view = LayoutInflater.from(context).inflate(R.layout.tabitem, null, false)
                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (activity.windowManager.defaultDisplay.height / 10))
                view.layoutParams = params
                if (position == select) {
                    view.findViewById(R.id.title).setBackgroundResource(R.color.white)
                    view.findViewById(R.id.indicator).visibility = View.VISIBLE
                    (view.findViewById(R.id.title) as TextView).setTextColor(Color.rgb(0xf0, 0x39, 0x3c))

                }
                view.setOnClickListener {
                    select = position
                    RxBus.getDefault().post(EventType().setType(EventType.LISTCLASS).setExtra(select.toString()))
                    notifyDataSetChanged()
                }
                (view.findViewById(R.id.title) as TextView).setText(classMode[position].gc_name)

                return view
            }

            override fun getItem(position: Int): Any? {
                return null
            }

            override fun getItemId(position: Int): Long {

                return position.toLong()
            }

            override fun getCount(): Int {
                return classMode.size
            }

        }
        binding.tablayout.adapter = tabAdapter
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
         * @return A new instance of fragment ClassificationFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): ClassificationFragment {
            val fragment = ClassificationFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
