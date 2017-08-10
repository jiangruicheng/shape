package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/8.
 */

public class ClassItemResponse extends BaseResponse {

    /**
     * code : 200
     * datas : [{"gc_id":"2","gc_name":"农产","son_array":[]},{"gc_id":"3","gc_name":"美食","son_array":[]},{"gc_id":"4","gc_name":"水果","son_array":[]},{"gc_id":"5","gc_name":"家居","son_array":[]},{"gc_id":"6","gc_name":"电器","son_array":[]},{"gc_id":"7","gc_name":"美妆","son_array":[{"son_name":"上新","url":""},{"son_name":"面部护理","url":""},{"son_name":"美发护理","url":""},{"son_name":"身体护理","url":""},{"son_name":"面膜","url":""},{"son_name":"美妆工具","url":""},{"son_name":"彩妆","url":""},{"son_name":"口红唇膏","url":""},{"son_name":"乳霜面霜","url":""},{"son_name":"纤体瘦身","url":""},{"son_name":"男士护理","url":""}]}]
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
         * son_array : []
         */

        private String gc_id;
        private String gc_name;
        private List<?> son_array;

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

        public List<?> getSon_array() {
            return son_array;
        }

        public void setSon_array(List<?> son_array) {
            this.son_array = son_array;
        }
    }
}
