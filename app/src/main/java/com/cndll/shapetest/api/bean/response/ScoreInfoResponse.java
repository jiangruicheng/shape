package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class ScoreInfoResponse extends BaseResponse {


    /**
     * code : 200
     * datas : {"max_score":"12.60","not_score":12.6,"get_score":"0.00","member_score":"9820.00","score_info":[{"time":"1503558364","operation_type":"2","operation_symbol":"-","score":"30.00","giver_num":"3495509","so_score":"9950.00","now_score":"9920.00"},{"time":"1498838400","operation_type":"2","operation_symbol":"-","score":"100.00","giver_num":"3495509","so_score":"9920.00","now_score":"9820.00"}]}
     */

    private int code;
    private String error_message;
    private DatasBean datas;

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
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
         * max_score : 12.60
         * not_score : 12.6
         * get_score : 0.00
         * member_score : 9820.00
         * score_info : [{"time":"1503558364","operation_type":"2","operation_symbol":"-","score":"30.00","giver_num":"3495509","so_score":"9950.00","now_score":"9920.00"},{"time":"1498838400","operation_type":"2","operation_symbol":"-","score":"100.00","giver_num":"3495509","so_score":"9920.00","now_score":"9820.00"}]
         */
        private String max_score;
        private String not_score;
        private String get_score;
        private String member_score;
        private String shop_score;
        private String shop_money;
        private String score;
        private String excitation_score;
        private List<ScoreInfoBean> score_info;

        public String getShop_money() {
            return shop_money;
        }

        public void setShop_money(String shop_money) {
            this.shop_money = shop_money;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getExcitation_score() {
            return excitation_score;
        }

        public void setExcitation_score(String excitation_score) {
            this.excitation_score = excitation_score;
        }

        public String getShop_score() {
            return shop_score;
        }

        public void setShop_score(String shop_score) {
            this.shop_score = shop_score;
        }

        public String getMax_score() {
            return max_score;
        }

        public void setMax_score(String max_score) {
            this.max_score = max_score;
        }

        public String getNot_score() {
            return not_score;
        }

        public void setNot_score(String not_score) {
            this.not_score = not_score;
        }

        public String getGet_score() {
            return get_score;
        }

        public void setGet_score(String get_score) {
            this.get_score = get_score;
        }

        public String getMember_score() {
            return member_score;
        }

        public void setMember_score(String member_score) {
            this.member_score = member_score;
        }

        public List<ScoreInfoBean> getScore_info() {
            return score_info;
        }

        public void setScore_info(List<ScoreInfoBean> score_info) {
            this.score_info = score_info;
        }

        public static class ScoreInfoBean {
            /**
             * time : 1503558364
             * operation_type : 2
             * operation_symbol : -
             * score : 30.00
             * giver_num : 3495509
             * so_score : 9950.00
             * now_score : 9920.00
             */

            private String time;
            private String operation_type;
            private String operation_symbol;
            private String score;
            private String giver_num;
            private String so_score;
            private String now_score;
            private String role_type;
            private String max_score;
            private String today_score;
            private String overplus_score;
            private String store_name;
            private String pay_type;
            private String order_amount;
            private String settlement_price;
            private String excitation_score;
            private String shop_score;

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getSettlement_price() {
                return settlement_price;
            }

            public void setSettlement_price(String settlement_price) {
                this.settlement_price = settlement_price;
            }

            public String getExcitation_score() {
                return excitation_score;
            }

            public void setExcitation_score(String excitation_score) {
                this.excitation_score = excitation_score;
            }

            public String getShop_score() {
                return shop_score;
            }

            public void setShop_score(String shop_score) {
                this.shop_score = shop_score;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getRole_type() {
                return role_type;
            }

            public void setRole_type(String role_type) {
                this.role_type = role_type;
            }

            public String getMax_score() {
                return max_score;
            }

            public void setMax_score(String max_score) {
                this.max_score = max_score;
            }

            public String getToday_score() {
                return today_score;
            }

            public void setToday_score(String today_score) {
                this.today_score = today_score;
            }

            public String getOverplus_score() {
                return overplus_score;
            }

            public void setOverplus_score(String overplus_score) {
                this.overplus_score = overplus_score;
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

            public String getOperation_symbol() {
                return operation_symbol;
            }

            public void setOperation_symbol(String operation_symbol) {
                this.operation_symbol = operation_symbol;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getGiver_num() {
                return giver_num;
            }

            public void setGiver_num(String giver_num) {
                this.giver_num = giver_num;
            }

            public String getSo_score() {
                return so_score;
            }

            public void setSo_score(String so_score) {
                this.so_score = so_score;
            }

            public String getNow_score() {
                return now_score;
            }

            public void setNow_score(String now_score) {
                this.now_score = now_score;
            }
        }
    }
}
