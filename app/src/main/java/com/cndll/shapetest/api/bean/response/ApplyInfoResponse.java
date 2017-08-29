package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class ApplyInfoResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"id":"8","invite_name":"004","member_id":"8","member_name":"sdfasfsd","code":"asdgfafdhhdafgas","invite_type":"1","zx_usage":"0","create_time":"1502104845","status":"1","superior_code":null,"status_text":"申请通过"}
     */

    private int code;
    private DatasBean datas;

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
         * id : 8
         * invite_name : 004
         * member_id : 8
         * member_name : sdfasfsd
         * code : asdgfafdhhdafgas
         * invite_type : 1
         * zx_usage : 0
         * create_time : 1502104845
         * status : 1
         * superior_code : null
         * status_text : 申请通过
         */

        private String id;
        private String error;
        private String invite_name;
        private String member_id;
        private String member_name;
        private String code;
        private String invite_type;
        private String zx_usage;
        private String create_time;
        private String status;
        private Object superior_code;
        private String status_text;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInvite_name() {
            return invite_name;
        }

        public void setInvite_name(String invite_name) {
            this.invite_name = invite_name;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getInvite_type() {
            return invite_type;
        }

        public void setInvite_type(String invite_type) {
            this.invite_type = invite_type;
        }

        public String getZx_usage() {
            return zx_usage;
        }

        public void setZx_usage(String zx_usage) {
            this.zx_usage = zx_usage;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getSuperior_code() {
            return superior_code;
        }

        public void setSuperior_code(Object superior_code) {
            this.superior_code = superior_code;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }
    }
}
