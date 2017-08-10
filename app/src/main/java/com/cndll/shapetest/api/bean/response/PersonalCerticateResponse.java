package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class PersonalCerticateResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"id":"5","member_id":"4","name":"倪贤银","phone":"15888240650","bank_card_num":"6228430339420737671","card_num":"330382199212304515","card_start_time":"1343750400","card_end_time":"1627747200","card_img":["http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05556974511773031_240.jpg","http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05556974343933806_240.jpg","http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05556974581248259_240.jpg","http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05556974441324439_240.jpg"],"email":"403073816@qq.com","state":"2","time":"1502353463","describe_personal":"手持身份证不清晰请重新上传"}
     */

    private int code;
    private String error_massage;

    private DatasBean datas;

    public String getError_massage() {
        return error_massage;
    }

    public void setError_massage(String error_massage) {
        this.error_massage = error_massage;
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
         * id : 5
         * member_id : 4
         * name : 倪贤银
         * phone : 15888240650
         * bank_card_num : 6228430339420737671
         * card_num : 330382199212304515
         * card_start_time : 1343750400
         * card_end_time : 1627747200
         * card_img : ["http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05556974511773031_240.jpg","http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05556974343933806_240.jpg","http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05556974581248259_240.jpg","http://zhongxiang.51edn.com/data/upload/shop/member/4/4_05556974441324439_240.jpg"]
         * email : 403073816@qq.com
         * state : 2
         * time : 1502353463
         * describe_personal : 手持身份证不清晰请重新上传
         */

        private String id;
        private String member_id;
        private String name;
        private String phone;
        private String bank_card_num;
        private String card_num;
        private String card_start_time;
        private String card_end_time;
        private String email;
        private String state;
        private String time;
        private String describe_personal;
        private List<String> card_img;
        private List<String> img;

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }

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

        public List<String> getCard_img() {
            return card_img;
        }

        public void setCard_img(List<String> card_img) {
            this.card_img = card_img;
        }
    }
}
