package com.cndll.shapetest.api;


import com.cndll.shapetest.api.bean.BaseRequest;
import com.cndll.shapetest.api.bean.BaseResponse;
import com.cndll.shapetest.api.bean.response.TestResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jiang_ruicheng on 16/10/27.
 */
public interface Api {
    @POST
    Observable<TestResponse> test(@Body BaseRequest request);
}
