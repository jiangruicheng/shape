package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class HttpCodeResponse extends BaseResponse {

    /**
     * code : 200
     * datas : 1
     */

    private int code;
//    private String datas;
    private String error_massage;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_massage() {
        return error_massage;
    }

    public void setError_massage(String error_massage) {
        this.error_massage = error_massage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

//    public String getDatas() {
//        return datas;
//    }
//
//    public void setDatas(String datas) {
//        this.datas = datas;
//    }

}
