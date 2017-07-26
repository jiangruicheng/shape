package com.cndll.shapetest.api;


import com.cndll.shapetest.api.bean.BaseRequest;
import com.cndll.shapetest.api.bean.response.AddressDetailsResponse;
import com.cndll.shapetest.api.bean.response.AddressListResponse;
import com.cndll.shapetest.api.bean.response.AddressResponse;
import com.cndll.shapetest.api.bean.response.HttpCodeResponse;
import com.cndll.shapetest.api.bean.response.RegisterResponse;
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

    /**地区列表**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<AddressResponse> addressList(@Field("act") String act,@Field("op") String op);

    /**地址列表**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<AddressListResponse> addressList(@Field("act") String act,@Field("op") String op,@Field("key") String key);

    /**地址详情**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<AddressDetailsResponse> addressDetails(@Field("act") String act, @Field("op") String op, @Field("key") String key, @Field("address_id") String addressId);

    /**编辑地址**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> updateAddress(@Field("act") String act,@Field("op") String op,@Field("key") String key,@Field("address_id") String addId ,@Field("is_default") String is_default,
                                               @Field("true_name") String true_name,@Field("area_id") String area_id,@Field("city_id") String city_id,@Field("tel_phone") String tel_phone,@Field("address") String address,@Field("area_info") String area_info );
    /**添加地址**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> addAddress(@Field("act") String act,@Field("op") String op,@Field("key") String key,@Field("is_default") String is_default,
                                               @Field("true_name") String true_name,@Field("area_id") String area_id,@Field("city_id") String city_id,@Field("tel_phone") String tel_phone,@Field("address") String address,@Field("area_info") String area_info );
    /**删除地址**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> deleteAddress(@Field("act") String act,@Field("op") String op,@Field("key") String key,@Field("address_id") String address_id);

    /**注册**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<RegisterResponse> register(@Field("act") String act,@Field("op") String op,@Field("username") String username,@Field("password") String password,@Field("password_confirm") String password_confirm,@Field("code") String code,@Field("client") String client);

    /**发送验证码**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<HttpCodeResponse> sendCode(@Field("act") String act,@Field("op") String op,@Field("mobile") String mobile);

    /**登录**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<RegisterResponse> login(@Field("act") String act,@Field("op") String op,@Field("username") String username,@Field("password") String password,@Field("client") String client);

    /**忘记密码**/
    @FormUrlEncoded
    @POST("mobile/index.php/")
    Observable<RegisterResponse> newLoginPwd(@Field("act") String act,@Field("op") String op,@Field("username") String username,@Field("password") String password,@Field("code") String code);
}

