package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class PersonalCerticateResponse extends BaseResponse {


    /**
     * code : 200
     * datas : {"id":"3","member_id":"2","name":"test","phone ":"22222222","bank_card_num ":"22222222","card_num":"abcdef","card_start_time":"1343750400","card_end_time":"1627747200","hand_card":"default_goods_image_240.gif","just_card":"default_goods_image_240.gif","back_card":"default_goods_image_240.gif","other_card":"default_goods_image_240.gif","hand_card_img":"http://www.zhongxiang.com/data/upload/shop/common/default_goods_image_240.gif","just_card_img":"http://www.zhongxiang.com/data/upload/shop/common/default_goods_image_240.gif","back_card_img":"http://www.zhongxiang.com/data/upload/shop/common/default_goods_image_240.gif","other_card_img":"http://www.zhongxiang.com/data/upload/shop/common/default_goods_image_240.gif","email ":"0","state":"0","time":"1502282821","describe_personal":"1502282821"}
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
         * id : 3
         * member_id : 2
         * name : test
         * phone  : 22222222
         * bank_card_num  : 22222222
         * card_num : abcdef
         * card_start_time : 1343750400
         * card_end_time : 1627747200
         * hand_card : default_goods_image_240.gif
         * just_card : default_goods_image_240.gif
         * back_card : default_goods_image_240.gif
         * other_card : default_goods_image_240.gif
         * hand_card_img : http://www.zhongxiang.com/data/upload/shop/common/default_goods_image_240.gif
         * just_card_img : http://www.zhongxiang.com/data/upload/shop/common/default_goods_image_240.gif
         * back_card_img : http://www.zhongxiang.com/data/upload/shop/common/default_goods_image_240.gif
         * other_card_img : http://www.zhongxiang.com/data/upload/shop/common/default_goods_image_240.gif
         * email  : 0
         * state : 0
         * time : 1502282821
         * describe_personal : 1502282821
         */

        private String id;
        private String member_id;
        private String name;
        private String phone;
        private String bank_card_num;
        private String card_num;
        private String card_start_time;
        private String card_end_time;
        private String hand_card;
        private String just_card;
        private String back_card;
        private String other_card;
        private String hand_card_img;
        private String just_card_img;
        private String back_card_img;
        private String other_card_img;
        private String email;
        private String state;
        private String time;
        private String describe_personal;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBank_card_num() {
            return bank_card_num;
        }

        public void setBank_card_num(String bank_card_num) {
            this.bank_card_num = bank_card_num;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getCard_start_time() {
            return card_start_time;
        }

        public void setCard_start_time(String card_start_time) {
            this.card_start_time = card_start_time;
        }

        public String getCard_end_time() {
            return card_end_time;
        }

        public void setCard_end_time(String card_end_time) {
            this.card_end_time = card_end_time;
        }

        public String getHand_card() {
            return hand_card;
        }

        public void setHand_card(String hand_card) {
            this.hand_card = hand_card;
        }

        public String getJust_card() {
            return just_card;
        }

        public void setJust_card(String just_card) {
            this.just_card = just_card;
        }

        public String getBack_card() {
            return back_card;
        }

        public void setBack_card(String back_card) {
            this.back_card = back_card;
        }

        public String getOther_card() {
            return other_card;
        }

        public void setOther_card(String other_card) {
            this.other_card = other_card;
        }

        public String getHand_card_img() {
            return hand_card_img;
        }

        public void setHand_card_img(String hand_card_img) {
            this.hand_card_img = hand_card_img;
        }

        public String getJust_card_img() {
            return just_card_img;
        }

        public void setJust_card_img(String just_card_img) {
            this.just_card_img = just_card_img;
        }

        public String getBack_card_img() {
            return back_card_img;
        }

        public void setBack_card_img(String back_card_img) {
            this.back_card_img = back_card_img;
        }

        public String getOther_card_img() {
            return other_card_img;
        }

        public void setOther_card_img(String other_card_img) {
            this.other_card_img = other_card_img;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDescribe_personal() {
            return describe_personal;
        }

        public void setDescribe_personal(String describe_personal) {
            this.describe_personal = describe_personal;
        }
    }
}
