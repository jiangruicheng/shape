package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class LogisticsResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"img_url":"http://zhongxiang.51edn.com/data/upload/shop/common/default_goods_image_60.gif","express_name":"顺丰快递","shipping_code":"957309267134","error":"","deliver_info":[{"time":"2017-04-12 10:31:00","context":"已签收,感谢使用顺丰,期待再次为您服务"},{"time":"2017-04-12 09:59:00","context":"快件交给叶延辉，正在派送途中（联系电话：18736318166）"},{"time":"2017-04-12 09:49:00","context":"快件到达 【洛阳库区乡合作点】"},{"time":"2017-04-12 08:56:00","context":"快件在【洛阳嵩县水源街营业点】已装车，准备发往 【洛阳库区乡合作点】"},{"time":"2017-04-12 08:42:00","context":"快件到达 【洛阳嵩县水源街营业点】"},{"time":"2017-04-11 15:36:00","context":"快件在【洛阳机场集散中心】已装车，准备发往 【洛阳嵩县水源街营业点】"},{"time":"2017-04-11 15:26:00","context":"快件到达 【洛阳机场集散中心】"},{"time":"2017-04-11 11:16:00","context":"快件在【郑州总集散中心】已装车，准备发往 【洛阳机场集散中心】"},{"time":"2017-04-11 10:47:00","context":"快件到达 【郑州总集散中心】"},{"time":"2017-04-11 02:36:00","context":"快件在【武汉总集散中心】已装车，准备发往 【郑州总集散中心】"},{"time":"2017-04-11 01:54:00","context":"快件到达 【武汉总集散中心】"},{"time":"2017-04-10 21:32:00","context":"快件在【荆门掇刀集散中心】已装车，准备发往 【武汉总集散中心】"},{"time":"2017-04-10 20:48:00","context":"快件到达 【荆门掇刀集散中心】"},{"time":"2017-04-10 18:35:00","context":"快件在【荆门京山县新阳大道营业点】已装车，准备发往 【荆门掇刀集散中心】"},{"time":"2017-04-10 18:21:00","context":"顺丰速运 已收取快件"}]}
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
         * img_url : http://zhongxiang.51edn.com/data/upload/shop/common/default_goods_image_60.gif
         * express_name : 顺丰快递
         * shipping_code : 957309267134
         * error :
         * deliver_info : [{"time":"2017-04-12 10:31:00","context":"已签收,感谢使用顺丰,期待再次为您服务"},{"time":"2017-04-12 09:59:00","context":"快件交给叶延辉，正在派送途中（联系电话：18736318166）"},{"time":"2017-04-12 09:49:00","context":"快件到达 【洛阳库区乡合作点】"},{"time":"2017-04-12 08:56:00","context":"快件在【洛阳嵩县水源街营业点】已装车，准备发往 【洛阳库区乡合作点】"},{"time":"2017-04-12 08:42:00","context":"快件到达 【洛阳嵩县水源街营业点】"},{"time":"2017-04-11 15:36:00","context":"快件在【洛阳机场集散中心】已装车，准备发往 【洛阳嵩县水源街营业点】"},{"time":"2017-04-11 15:26:00","context":"快件到达 【洛阳机场集散中心】"},{"time":"2017-04-11 11:16:00","context":"快件在【郑州总集散中心】已装车，准备发往 【洛阳机场集散中心】"},{"time":"2017-04-11 10:47:00","context":"快件到达 【郑州总集散中心】"},{"time":"2017-04-11 02:36:00","context":"快件在【武汉总集散中心】已装车，准备发往 【郑州总集散中心】"},{"time":"2017-04-11 01:54:00","context":"快件到达 【武汉总集散中心】"},{"time":"2017-04-10 21:32:00","context":"快件在【荆门掇刀集散中心】已装车，准备发往 【武汉总集散中心】"},{"time":"2017-04-10 20:48:00","context":"快件到达 【荆门掇刀集散中心】"},{"time":"2017-04-10 18:35:00","context":"快件在【荆门京山县新阳大道营业点】已装车，准备发往 【荆门掇刀集散中心】"},{"time":"2017-04-10 18:21:00","context":"顺丰速运 已收取快件"}]
         */

        private String img_url;
        private String express_name;
        private String shipping_code;
        private String error;
        private List<DeliverInfoBean> deliver_info;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getExpress_name() {
            return express_name;
        }

        public void setExpress_name(String express_name) {
            this.express_name = express_name;
        }

        public String getShipping_code() {
            return shipping_code;
        }

        public void setShipping_code(String shipping_code) {
            this.shipping_code = shipping_code;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public List<DeliverInfoBean> getDeliver_info() {
            return deliver_info;
        }

        public void setDeliver_info(List<DeliverInfoBean> deliver_info) {
            this.deliver_info = deliver_info;
        }

        public static class DeliverInfoBean {
            /**
             * time : 2017-04-12 10:31:00
             * context : 已签收,感谢使用顺丰,期待再次为您服务
             */

            private String time;
            private String context;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
    }
}
