package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class BankCardResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"bank_info":[{"id":"1","member_id":"2","card_num":"622222233942073111","brand":"金穗通宝银卡","bank_name":"中国农业银行-农业银行","card_type":"借记卡","time":"2147483647","img":"http://www.zhongxiang.com/data/upload/shop/common/nonghang.png"}],"card_num":"330382193312345678","card_name":"fly"}
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
         * bank_info : [{"id":"1","member_id":"2","card_num":"622222233942073111","brand":"金穗通宝银卡","bank_name":"中国农业银行-农业银行","card_type":"借记卡","time":"2147483647","img":"http://www.zhongxiang.com/data/upload/shop/common/nonghang.png"}]
         * card_num : 330382193312345678
         * card_name : fly
         */

        private String card_num;
        private String card_name;
        private List<BankInfoBean> bank_info;

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public List<BankInfoBean> getBank_info() {
            return bank_info;
        }

        public void setBank_info(List<BankInfoBean> bank_info) {
            this.bank_info = bank_info;
        }

        public static class BankInfoBean {
            /**
             * id : 1
             * member_id : 2
             * card_num : 622222233942073111
             * brand : 金穗通宝银卡
             * bank_name : 中国农业银行-农业银行
             * card_type : 借记卡
             * time : 2147483647
             * img : http://www.zhongxiang.com/data/upload/shop/common/nonghang.png
             */

            private String id;
            private String member_id;
            private String card_num;
            private String brand;
            private String bank_name;
            private String card_type;
            private String time;
            private String img;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getCard_num() {
                return card_num;
            }

            public void setCard_num(String card_num) {
                this.card_num = card_num;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getCard_type() {
                return card_type;
            }

            public void setCard_type(String card_type) {
                this.card_type = card_type;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
