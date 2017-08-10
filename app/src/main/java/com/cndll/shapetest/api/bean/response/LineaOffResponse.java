package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/5.
 */

public class LineaOffResponse extends BaseResponse {

    /**
     * code : 200
     * datas : [{"store_id":"2","store_address":"西丽镇","store_slide":"05531007446800901.jpg,05531007471791244.jpg,05531007492294851.jpg,05531007512916334.jpg,05531007536729613.jpg","store_name":"小飞","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531007446800901.jpg"}]
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
         * store_id : 2
         * store_address : 西丽镇
         * store_slide : 05531007446800901.jpg,05531007471791244.jpg,05531007492294851.jpg,05531007512916334.jpg,05531007536729613.jpg
         * store_name : 小飞
         * img_url : http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531007446800901.jpg
         */

        private String store_id;
        private String store_address;
        private String store_slide;
        private String store_name;
        private String img_url;

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public String getStore_slide() {
            return store_slide;
        }

        public void setStore_slide(String store_slide) {
            this.store_slide = store_slide;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
