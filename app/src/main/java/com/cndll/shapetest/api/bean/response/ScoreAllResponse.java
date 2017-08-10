package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class ScoreAllResponse extends BaseResponse {


    /**
     * code : 200
     * datas : [{"type":"0","score":"11.00","time":"1502258579","operation_type":"0"},{"type":"1","score":"111.00","time":"1502258579","operation_type":"1"}]
     */

    private int code;
    private String error_massage;

    public String getError_massage() {
        return error_massage;
    }

    public void setError_massage(String error_massage) {
        this.error_massage = error_massage;
    }

    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * type : 0
         * score : 11.00
         * time : 1502258579
         * operation_type : 0
         */

        private String type;
        private String score;
        private String time;
        private String operation_type;
        private String store_name;
        private String goods_name;


        private String id;
        private String operator_id;
        private String operator_name;
        private String operator_num;
        private String donation_id;
        private String donation_name;
        private String donation_num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOperator_id() {
            return operator_id;
        }

        public void setOperator_id(String operator_id) {
            this.operator_id = operator_id;
        }

        public String getOperator_name() {
            return operator_name;
        }

        public void setOperator_name(String operator_name) {
            this.operator_name = operator_name;
        }

        public String getOperator_num() {
            return operator_num;
        }

        public void setOperator_num(String operator_num) {
            this.operator_num = operator_num;
        }

        public String getDonation_id() {
            return donation_id;
        }

        public void setDonation_id(String donation_id) {
            this.donation_id = donation_id;
        }

        public String getDonation_name() {
            return donation_name;
        }

        public void setDonation_name(String donation_name) {
            this.donation_name = donation_name;
        }

        public String getDonation_num() {
            return donation_num;
        }

        public void setDonation_num(String donation_num) {
            this.donation_num = donation_num;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getOperation_type() {
            return operation_type;
        }

        public void setOperation_type(String operation_type) {
            this.operation_type = operation_type;
        }
    }
}
