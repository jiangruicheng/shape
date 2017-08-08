package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class ScoreIndexResponse extends BaseResponse {

    /**
     * code : 200
     * error_massage : 未登录
     * datas : {"voucher_num":1,"shop_score":"0.00","member_excitation_score":"0.00","bank_card_num":"0","state":9,"fund":0}
     */

    private int code;
    private String error_massage;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError_massage() {
        return error_massage;
    }

    public void setError_massage(String error_massage) {
        this.error_massage = error_massage;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * voucher_num : 1
         * shop_score : 0.00
         * member_excitation_score : 0.00
         * bank_card_num : 0
         * state : 9
         * fund : 0
         */

        private int voucher_num;
        private String shop_score;
        private String member_excitation_score;
        private String bank_card_num;
        private int state;
        private int fund;
        private String score_voucher;

        public String getScore_voucher() {
            return score_voucher;
        }

        public void setScore_voucher(String score_voucher) {
            this.score_voucher = score_voucher;
        }

        public int getVoucher_num() {
            return voucher_num;
        }

        public void setVoucher_num(int voucher_num) {
            this.voucher_num = voucher_num;
        }

        public String getShop_score() {
            return shop_score;
        }

        public void setShop_score(String shop_score) {
            this.shop_score = shop_score;
        }

        public String getMember_excitation_score() {
            return member_excitation_score;
        }

        public void setMember_excitation_score(String member_excitation_score) {
            this.member_excitation_score = member_excitation_score;
        }

        public String getBank_card_num() {
            return bank_card_num;
        }

        public void setBank_card_num(String bank_card_num) {
            this.bank_card_num = bank_card_num;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getFund() {
            return fund;
        }

        public void setFund(int fund) {
            this.fund = fund;
        }
    }
}
