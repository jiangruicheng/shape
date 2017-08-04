package com.cndll.shapetest.api;


import com.cndll.shapetest.api.bean.BaseRequest;

import com.cndll.shapetest.api.bean.BaseResponse;
import com.cndll.shapetest.api.bean.response.BrandResponse;
import com.cndll.shapetest.api.bean.response.CommodityResponse;
import com.cndll.shapetest.api.bean.response.FightResponse;
import com.cndll.shapetest.api.bean.response.HomePageResponse;
import com.cndll.shapetest.api.bean.response.LimitedResponse;
import com.cndll.shapetest.api.bean.response.TestResponse;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jiang_ruicheng on 16/10/27.
 */
public interface Api {
    @POST("/index/")
    Observable<TestResponse> test(@Body BaseRequest request);


    @GET("/mobile/index.php?act=index")
    Observable<HomePageResponse> homePage(/*@Query("act") String act,*/ @Query("gc_id") String id, @Query("page") String page);


    @GET("/mobile/index.php?act=goods&op=goodsSeckill")
    Observable<LimitedResponse> limitidPage(/*@Query("act") String act,*/ @Query("page") String page, @Query("time_part") String timePart);

    @GET("/mobile/index.php?act=fight_groups_goods&op=index")
    Observable<FightResponse> fightPage(/*@Query("act") String act,*/ @Query("page") String page);

    /*@GET("/mobile/index.php?act=goods&op=index&goods_id=7")
    Observable<CommodityResponse> commodiytPage();*/
    @GET("/mobile/index.php?act=goods&op=index&goods_id=80&fight_groups_id=13")
    Observable<CommodityResponse> commodiytPage();

    @GET("/mobile/index.php?act=goods&op=goods_type&goods_type=2&page=1")
    Observable<BrandResponse> brandPage();
}
