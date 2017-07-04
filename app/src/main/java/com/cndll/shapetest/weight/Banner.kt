package com.cndll.shapetest.weight

import android.view.View
import android.widget.Toast
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.cndll.shapetest.R

/**
 * Created by jiangruicheng on 2017/7/4.
 */
class Banner {
    fun setBanner(view: View, dataList: List<MenuGrid.BannerBean>) {
        val urls = ArrayList<String>()
        for (r in dataList) {
            urls.add(r.imageUrl)
        }
        val banner: ConvenientBanner<String> = view.findViewById(R.id.banner) as ConvenientBanner<String>
        banner.setPages(CBViewHolderCreator { MenuGrid.LocalImageHolderView() }, urls).isCanLoop = true
        banner.setOnItemClickListener { position -> Toast.makeText(banner.context, dataList[position].gotoUrl, Toast.LENGTH_SHORT).show() }
    }
}