package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class FileResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"file_id":25,"file_name":"21_05550147561356804.jpg","origin_file_name":"9.jpg","file_url":"http://www.zhongxiang.com/data/upload/shop/member/21/21_05550147561356804_240.jpg"}
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
         * file_id : 25
         * file_name : 21_05550147561356804.jpg
         * origin_file_name : 9.jpg
         * file_url : http://www.zhongxiang.com/data/upload/shop/member/21/21_05550147561356804_240.jpg
         */

        private int file_id;
        private String file_name;
        private String origin_file_name;
        private String file_url;

        public int getFile_id() {
            return file_id;
        }

        public void setFile_id(int file_id) {
            this.file_id = file_id;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getOrigin_file_name() {
            return origin_file_name;
        }

        public void setOrigin_file_name(String origin_file_name) {
            this.origin_file_name = origin_file_name;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }
    }
}
