package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class UserInfoResponse extends BaseResponse{


    /**
     * code : 200
     * error_massage : 请先登录
     * datas : {"error":"199","member_id":"4","member_name":"13048875254","member_username":"。雨神！","member_code":"15011520901165","member_num":"3006923","member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/avatar/avatar_4_new.jpg","download_url":"www.baidu.com","my_relation":[{"member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","member_nick":"four","member_time":"2017.07.26"},{"member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","member_nick":"three","member_time":"2017.07.26"},{"member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","member_nick":"two","member_time":"2017.07.26"},{"member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","member_nick":"one","member_time":"2017.07.26"}]}
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

    public static class DatasBean implements Serializable{
        /**
         * error : 199
         * member_id : 4
         * member_name : 13048875254
         * member_username : 。雨神！
         * member_code : 15011520901165
         * member_num : 3006923
         * member_avatar : http://zhongxiang.51edn.com/data/upload/shop/avatar/avatar_4_new.jpg
         * download_url : www.baidu.com
         * my_relation : [{"member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","member_nick":"four","member_time":"2017.07.26"},{"member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","member_nick":"three","member_time":"2017.07.26"},{"member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","member_nick":"two","member_time":"2017.07.26"},{"member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","member_nick":"one","member_time":"2017.07.26"}]
         */

        private String error;
        private String member_id;
        private String member_name;
        private String member_username;
        private String member_code;
        private String member_num;
        private String member_avatar;
        private String download_url;
        private List<MyRelationBean> my_relation;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
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

        public String getMember_username() {
            return member_username;
        }

        public void setMember_username(String member_username) {
            this.member_username = member_username;
        }

        public String getMember_code() {
            return member_code;
        }

        public void setMember_code(String member_code) {
            this.member_code = member_code;
        }

        public String getMember_num() {
            return member_num;
        }

        public void setMember_num(String member_num) {
            this.member_num = member_num;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public List<MyRelationBean> getMy_relation() {
            return my_relation;
        }

        public void setMy_relation(List<MyRelationBean> my_relation) {
            this.my_relation = my_relation;
        }

        public static class MyRelationBean implements Serializable {
            /**
             * member_avatar : http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif
             * member_nick : four
             * member_time : 2017.07.26
             */

            private String member_avatar;
            private String member_nick;
            private String member_time;

            public String getMember_avatar() {
                return member_avatar;
            }

            public void setMember_avatar(String member_avatar) {
                this.member_avatar = member_avatar;
            }

            public String getMember_nick() {
                return member_nick;
            }

            public void setMember_nick(String member_nick) {
                this.member_nick = member_nick;
            }

            public String getMember_time() {
                return member_time;
            }

            public void setMember_time(String member_time) {
                this.member_time = member_time;
            }
        }
    }
}
