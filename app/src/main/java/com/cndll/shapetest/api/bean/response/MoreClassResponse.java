package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/15.
 */

public class MoreClassResponse extends BaseResponse {

    /**
     * code : 200
     * datas : [{"gc_id":"2","gc_name":"农产","son_array":[{"son_name":"农产1","gc_id":"22","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产2","gc_id":"23","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产3","gc_id":"24","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产4","gc_id":"25","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产5","gc_id":"26","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产6","gc_id":"46","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产7","gc_id":"47","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"}]}]
     */

    private int code;
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
         * gc_id : 2
         * gc_name : 农产
         * son_array : [{"son_name":"农产1","gc_id":"22","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产2","gc_id":"23","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产3","gc_id":"24","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产4","gc_id":"25","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产5","gc_id":"26","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产6","gc_id":"46","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"},{"son_name":"农产7","gc_id":"47","img_url":"http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg"}]
         */

        private String gc_id;
        private String gc_name;
        private List<SonArrayBean> son_array;

        public String getGc_id() {
            return gc_id;
        }

        public void setGc_id(String gc_id) {
            this.gc_id = gc_id;
        }

        public String getGc_name() {
            return gc_name;
        }

        public void setGc_name(String gc_name) {
            this.gc_name = gc_name;
        }

        public List<SonArrayBean> getSon_array() {
            return son_array;
        }

        public void setSon_array(List<SonArrayBean> son_array) {
            this.son_array = son_array;
        }

        public static class SonArrayBean {
            /**
             * son_name : 农产1
             * gc_id : 22
             * img_url : http://zhongxiang.51edn.com/data/upload/shop/brand/04397471910652190_sm.jpg
             */

            private String son_name;
            private String gc_id;
            private String img_url;

            public String getSon_name() {
                return son_name;
            }

            public void setSon_name(String son_name) {
                this.son_name = son_name;
            }

            public String getGc_id() {
                return gc_id;
            }

            public void setGc_id(String gc_id) {
                this.gc_id = gc_id;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }
}
