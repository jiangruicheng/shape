package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class ScoreAllResponse extends BaseResponse {

    /**
     * code : 200
     * error_massage : 未登录
     * datas : {"score":[{"type":"1","score":"1111.00","time":"1111","operation_type":"0"}]}
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

    public static class DatasBean {
        private List<ScoreBean> score;

        public List<ScoreBean> getScore() {
            return score;
        }

        public void setScore(List<ScoreBean> score) {
            this.score = score;
        }

        public static class ScoreBean {
            /**
             * type : 1
             * score : 1111.00
             * time : 1111
             * operation_type : 0
             */

            private String type;
            private String score;
            private String time;
            private String operation_type;
            private String store_name;
            private String goods_name;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getOperation_type() {
                return operation_type;
            }

            public void setOperation_type(String operation_type) {
                this.operation_type = operation_type;
            }
        }
    }
}
