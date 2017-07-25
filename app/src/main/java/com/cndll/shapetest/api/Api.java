package com.cndll.shapetest.api;


import com.cndll.shapetest.api.bean.BaseRequest;
import com.cndll.shapetest.api.bean.response.AddressDetailsResponse;
import com.cndll.shapetest.api.bean.response.AddressListResponse;
import com.cndll.shapetest.api.bean.response.AddressResponse;
import com.cndll.shapetest.api.bean.response.HttpCodeResponse;
import com.cndll.shapetest.api.bean.response.TestResponse;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jiang_ruicheng on 16/10/27.
 */
public interface Api {
    @POST
    Observable<TestResponse> test(@Body BaseRequest request);

    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<AddressResponse> addressList(@Field("act") String act,@Field("op") String op);

    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<AddressListResponse> addressList(@Field("act") String act,@Field("op") String op,@Field("key") String key);

    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<AddressDetailsResponse> addressDetails(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("address_id") String addressId);

    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> updateAddress(@Field("act") String act,@Field("op") String op,@Field("key") String key,@Field("address_id") String addId ,@Field("is_default") String is_default,
                                               @Field("true_name") String true_name,@Field("area_id") String area_id,@Field("city_id") String city_id,@Field("tel_phone") String tel_phone,@Field("address") String address,@Field("area_info") String area_info );

    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> addAddress(@Field("act") String act,@Field("op") String op,@Field("key") String key,@Field("is_default") String is_default,
                                               @Field("true_name") String true_name,@Field("area_id") String area_id,@Field("city_id") String city_id,@Field("tel_phone") String tel_phone,@Field("address") String address,@Field("area_info") String area_info );

}

