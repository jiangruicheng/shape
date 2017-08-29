package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;
import com.cndll.shapetest.bean.anno.Variable;

/**
 * Created by jiangruicheng on 2017/8/18.
 */

public class GetOrderInfoResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"score_voucher":1,"redpacket_voucher":"2","shop_score":"0.00","address_info":{"address_id":"17","member_id":"42","true_name":"卢鹏琳","area_id":"37","city_id":"36","area_info":"北京 北京市 东城区","address":"新华大街都好都好","tel_phone":"454645461619","mob_phone":null,"is_default":"1","dlyp_id":"0"}}
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
         * score_voucher : 1
         * redpacket_voucher : 2
         * shop_score : 0.00
         * address_info : {"address_id":"17","member_id":"42","true_name":"卢鹏琳","area_id":"37","city_id":"36","area_info":"北京 北京市 东城区","address":"新华大街都好都好","tel_phone":"454645461619","mob_phone":null,"is_default":"1","dlyp_id":"0"}
         */

        private int score_voucher;
        private String redpacket_voucher;
        private String shop_score;
        private AddressInfoBean address_info;

        public int getScore_voucher() {
            return score_voucher;
        }

        public void setScore_voucher(int score_voucher) {
            this.score_voucher = score_voucher;
        }

        public String getRedpacket_voucher() {
            return redpacket_voucher;
        }

        public void setRedpacket_voucher(String redpacket_voucher) {
            this.redpacket_voucher = redpacket_voucher;
        }

        public String getShop_score() {
            return shop_score;
        }

        public void setShop_score(String shop_score) {
            this.shop_score = shop_score;
        }

        public AddressInfoBean getAddress_info() {
            return address_info;
        }

        public void setAddress_info(AddressInfoBean address_info) {
            this.address_info = address_info;
        }

        public static class AddressInfoBean {
            /**
             * address_id : 17
             * member_id : 42
             * true_name : 卢鹏琳
             * area_id : 37
             * city_id : 36
             * area_info : 北京 北京市 东城区
             * address : 新华大街都好都好
             * tel_phone : 454645461619
             * mob_phone : null
             * is_default : 1
             * dlyp_id : 0
             */

            private String address_id;
            private String member_id;

            @Variable(variable = "name")

            public String true_name;
            private String area_id;
            private String city_id;

            @Variable(variable = "area")

            public String area_info;

            @Variable(variable = "address")

            public String address;

            @Variable(variable = "tel")

            public String tel_phone;

            private Object mob_phone;
            private String is_default;
            private String dlyp_id;

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getTrue_name() {
                return true_name;
            }

            public void setTrue_name(String true_name) {
                this.true_name = true_name;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getArea_info() {
                return area_info;
            }

            public void setArea_info(String area_info) {
                this.area_info = area_info;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTel_phone() {
                return tel_phone;
            }

            public void setTel_phone(String tel_phone) {
                this.tel_phone = tel_phone;
            }

            public Object getMob_phone() {
                return mob_phone;
            }

            public void setMob_phone(Object mob_phone) {
                this.mob_phone = mob_phone;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public String getDlyp_id() {
                return dlyp_id;
            }

            public void setDlyp_id(String dlyp_id) {
                this.dlyp_id = dlyp_id;
            }
        }
    }
}

