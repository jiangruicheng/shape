package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class AuthenticationResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"id":"3","member_id":"4","card_num":"610328199203140917","tel_name":"樊荣超","tel":"13048875254","email":"2804743667@qq.com","company_bank":"6230580000051955008","opening_bank":"平安银行","hand_card":"4_05566405805260325.jpg","just_card":"4_05566405953757829.jpg","back_card":"4_05566406085766716.jpg","business_license":"4_05566406462336554.jpg","opening_permit":"4_05566406219343552.jpg","other":"4_05566406346860076.jpg","credit_code":"123456","com_name":"cd","com_address":"","com_city":"0","industry":"0","nature":"0","images":"","numbers":"0","status":"0","create_time":"1503296649","describe_info":"","hand_card_img":"http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566405805260325_240.jpg","just_card_img":"http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566405953757829_240.jpg","back_card_img":"http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566406085766716_240.jpg","business_license_img":"http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566406462336554_240.jpg","opening_permit_img":"http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566406219343552_240.jpg","other_card_img":""}
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
         * member_id : 4
         * card_num : 610328199203140917
         * tel_name : 樊荣超
         * tel : 13048875254
         * email : 2804743667@qq.com
         * company_bank : 6230580000051955008
         * opening_bank : 平安银行
         * hand_card : 4_05566405805260325.jpg
         * just_card : 4_05566405953757829.jpg
         * back_card : 4_05566406085766716.jpg
         * business_license : 4_05566406462336554.jpg
         * opening_permit : 4_05566406219343552.jpg
         * other : 4_05566406346860076.jpg
         * credit_code : 123456
         * com_name : cd
         * com_address :
         * com_city : 0
         * industry : 0
         * nature : 0
         * images :
         * numbers : 0
         * status : 0
         * create_time : 1503296649
         * describe_info :
         * hand_card_img : http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566405805260325_240.jpg
         * just_card_img : http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566405953757829_240.jpg
         * back_card_img : http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566406085766716_240.jpg
         * business_license_img : http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566406462336554_240.jpg
         * opening_permit_img : http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05566406219343552_240.jpg
         * other_card_img :
         */

        private String id;
        private String member_id;
        private String card_num;
        private String tel_name;
        private String tel;
        private String email;
        private String company_bank;
        private String opening_bank;
        private String hand_card;
        private String just_card;
        private String back_card;
        private String business_license;
        private String opening_permit;
        private String other;
        private String credit_code;
        private String com_name;
        private String com_address;
        private String com_city;
        private String industry;
        private String nature;
        private String images;
        private String numbers;
        private String status;
        private String create_time;
        private String describe_info;
        private String hand_card_img;
        private String just_card_img;
        private String back_card_img;
        private String business_license_img;
        private String opening_permit_img;
        private String other_card_img;

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

        public String getTel_name() {
            return tel_name;
        }

        public void setTel_name(String tel_name) {
            this.tel_name = tel_name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCompany_bank() {
            return company_bank;
        }

        public void setCompany_bank(String company_bank) {
            this.company_bank = company_bank;
        }

        public String getOpening_bank() {
            return opening_bank;
        }

        public void setOpening_bank(String opening_bank) {
            this.opening_bank = opening_bank;
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

        public String getBusiness_license() {
            return business_license;
        }

        public void setBusiness_license(String business_license) {
            this.business_license = business_license;
        }

        public String getOpening_permit() {
            return opening_permit;
        }

        public void setOpening_permit(String opening_permit) {
            this.opening_permit = opening_permit;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getCredit_code() {
            return credit_code;
        }

        public void setCredit_code(String credit_code) {
            this.credit_code = credit_code;
        }

        public String getCom_name() {
            return com_name;
        }

        public void setCom_name(String com_name) {
            this.com_name = com_name;
        }

        public String getCom_address() {
            return com_address;
        }

        public void setCom_address(String com_address) {
            this.com_address = com_address;
        }

        public String getCom_city() {
            return com_city;
        }

        public void setCom_city(String com_city) {
            this.com_city = com_city;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getNumbers() {
            return numbers;
        }

        public void setNumbers(String numbers) {
            this.numbers = numbers;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDescribe_info() {
            return describe_info;
        }

        public void setDescribe_info(String describe_info) {
            this.describe_info = describe_info;
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

        public String getBusiness_license_img() {
            return business_license_img;
        }

        public void setBusiness_license_img(String business_license_img) {
            this.business_license_img = business_license_img;
        }

        public String getOpening_permit_img() {
            return opening_permit_img;
        }

        public void setOpening_permit_img(String opening_permit_img) {
            this.opening_permit_img = opening_permit_img;
        }

        public String getOther_card_img() {
            return other_card_img;
        }

        public void setOther_card_img(String other_card_img) {
            this.other_card_img = other_card_img;
        }
    }
}
