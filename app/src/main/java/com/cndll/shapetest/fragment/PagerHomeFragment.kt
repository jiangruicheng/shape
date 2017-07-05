package com.cndll.shapetest.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.ArrayMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AbsListView
import com.cndll.shapetest.R
import com.cndll.shapetest.weight.MenuGrid
import kotlinx.android.synthetic.main.fragment_pager_home.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [PagerHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagerHomeFragment : Fragment() {

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

    var offsetX = 0
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_pager_home, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        val wm: WindowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val height = wm.defaultDisplay.height
        recycler.adapter = RecyclerAdapter()
        val layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        back_top.setOnClickListener { recycler.smoothScrollToPosition(0) }
        recycler.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                offsetX = offsetX + dy
                if (offsetX > height) {
                    back_top.visibility = View.VISIBLE
                } else {
                    back_top.visibility = View.GONE

                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }
        })
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
         * @return A new instance of fragment PagerHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): PagerHomeFragment {
            val fragment = PagerHomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ItemView>() {
        override fun onBindViewHolder(p0: RecyclerAdapter.ItemView?, p1: Int) {
        }

        lateinit var mitems: ArrayMap<String, Objects>
        override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): RecyclerAdapter.ItemView {
            //val view = LayoutInflater.from(p0?.context).inflate(R.layout.banner, p0, false)
            val bean1 = MenuGrid.MenuBean()
            bean1.title = "美妆"
            val bean2 = MenuGrid.MenuBean()
            bean2.title = "家电"
            val bean3 = MenuGrid.MenuBean()
            bean3.title = "美女"
            val dataList = listOf<MenuGrid.MenuBean>(bean1, bean2, bean3)

            val view = MenuGrid(p0)
            val params = view.view.layoutParams
            val windowManager = p0?.context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            params.height = windowManager.defaultDisplay.height / 2
            view.setMenuData(dataList)
            val bannerBeans = ArrayList<MenuGrid.BannerBean>()
            bannerBeans.add(MenuGrid.BannerBean("http://zhongxiang.51edn.com/data/upload/shop/goods_class/05513824560521848.jpg", "http:www.baidu.com"))
            bannerBeans.add(MenuGrid.BannerBean("http://zhongxiang.51edn.com/data/upload/shop/goods_class/05513824560521848.jpg", "http:www.baidu.com"))
            bannerBeans.add(MenuGrid.BannerBean("http://zhongxiang.51edn.com/data/upload/shop/goods_class/05513824560521848.jpg", "http:www.baidu.com"))
            view.setBanner(bannerBeans)
            return ItemView(view.view)

        }

        override fun getItemCount(): Int {
            return 22
        }

        override fun getItemId(position: Int): Long {
            return super.getItemId(position)
        }

        override fun getItemViewType(position: Int): Int {
            return super.getItemViewType(position)
        }

        inner class ItemView(view: View) : RecyclerView.ViewHolder(view) {}

    }
}// Required empty public constructor
