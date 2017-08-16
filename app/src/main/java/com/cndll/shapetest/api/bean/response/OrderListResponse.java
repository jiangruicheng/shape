package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class OrderListResponse extends BaseResponse implements Serializable{

    /**
     * code : 200
     * datas : [{"order_id":"45","order_sn":"9000000000004801","order_amount":"20.00","is_excitation_state":"0","evaluation_state":"0","evaluation_again_state":"0","order_state":"10","refund_state":"0","goods_list":[{"goods_num":"1","goods_price":"20.00","goods_name":"测试购买数据","goods_spec":"300ml","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/2/2_05536142552749073_60.jpg"}]},{"order_id":"44","order_sn":"9000000000004701","order_amount":"20.00","is_excitation_state":"0","evaluation_state":"0","evaluation_again_state":"0","order_state":"10","refund_state":"0","goods_list":[{"goods_num":"1","goods_price":"20.00","goods_name":"测试购买数据","goods_spec":"300ml","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/2/2_05536142552749073_60.jpg"}]}]
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

    public static class DatasBean implements Serializable{
        /**
         * order_id : 45
         * order_sn : 9000000000004801
         * order_amount : 20.00
         * is_excitation_state : 0
         * evaluation_state : 0
         * evaluation_again_state : 0
         * order_state : 10
         * refund_state : 0
         * goods_list : [{"goods_num":"1","goods_price":"20.00","goods_name":"测试购买数据","goods_spec":"300ml","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/2/2_05536142552749073_60.jpg"}]
         */

        private String order_id;
        private String order_sn;
        private String order_amount;
        private String is_excitation_state;
        private String evaluation_state;
        private String evaluation_again_state;
        private String order_state;
        private String refund_state;
        private List<GoodsListBean> goods_list;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getIs_excitation_state() {
            return is_excitation_state;
        }

        public void setIs_excitation_state(String is_excitation_state) {
            this.is_excitation_state = is_excitation_state;
        }

        public String getEvaluation_state() {
            return evaluation_state;
        }

        public void setEvaluation_state(String evaluation_state) {
            this.evaluation_state = evaluation_state;
        }

        public String getEvaluation_again_state() {
            return evaluation_again_state;
        }

        public void setEvaluation_again_state(String evaluation_again_state) {
            this.evaluation_again_state = evaluation_again_state;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(String refund_state) {
            this.refund_state = refund_state;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean implements Serializable{
            /**
             * goods_num : 1
             * goods_price : 20.00
             * goods_name : 测试购买数据
             * goods_spec : 300ml
             * img_url : http://zhongxiang.51edn.com/data/upload/shop/store/goods/2/2_05536142552749073_60.jpg
             */

            private String goods_num;
            private String goods_price;
            private String goods_name;
            private String goods_spec;
            private String img_url;
            private String rec_id;

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_spec() {
                return goods_spec;
            }

            public void setGoods_spec(String goods_spec) {
                this.goods_spec = goods_spec;
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
