package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class RegisterResponse extends BaseResponse {

    /**
     * code : 200
     * message : 验证码错误
     * datas : {"username":"15888240650","userid":"16","key":"1f45d0441b5fb9fbebff807f5fc28813","error":"199"}
     */

    private int code;
    private String error_message;
    private String message;
    private DatasBean datas;

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }


    public static class DatasBean {
        /**
         * username : 15888240650
         * userid : 16
         * key : 1f45d0441b5fb9fbebff807f5fc28813
         * error : 199
         */

        private String username;
        private String userid;
        private String key;
        private String error;
        private String member_name;
        private String token;

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
