package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 * 抵用卷
 */

public class VouchersResponse extends BaseResponse {


    /**
     * code : 200
     * error_message : 请登陆
     * datas : [{"id":"1","code":"413300555418825019","member_id":"2","money":"0.50","store_id":"0","payment_type":"1","voucher_type":"1","state":"1","start_time":"1502035200","end_time":"1504627200","member_name":"xiaofei","store_name":null}]
     */

    private int code;
    private String error_message;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 1
         * code : 413300555418825019
         * member_id : 2
         * money : 0.50
         * store_id : 0
         * payment_type : 1
         * voucher_type : 1
         * state : 1
         * start_time : 1502035200
         * end_time : 1504627200
         * member_name : xiaofei
         * store_name : null
         */

        private String id;
        private String code;
        private String member_id;
        private String money;
        private String store_id;
        private String payment_type;
        private String voucher_type;
        private String state;
        private String start_time;
        private String end_time;
        private String member_name;
        private Object store_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public String getVoucher_type() {
            return voucher_type;
        }

        public void setVoucher_type(String voucher_type) {
            this.voucher_type = voucher_type;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public Object getStore_name() {
            return store_name;
        }

        public void setStore_name(Object store_name) {
            this.store_name = store_name;
        }
    }
}
