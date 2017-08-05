package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/4.
 */

public class ScoreResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"member_score":"20","goods":[{"goods_name":"积分小商品","score":"100","goods_image":"1_05526719352178284.jpg","price":"20.00","goods_id":"112","store_id":"1","shop_score":"100","scoreprice":"0.00","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719352178284_60.jpg","goods_price":"消费积分 100 + ¥0.00","goods_marketprice":"20.00"},{"goods_name":"积分商品","score":"100","goods_image":"1_05536899838386847.jpg","price":"120.00","goods_id":"12","store_id":"1","shop_score":"200","scoreprice":"30.00","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05536899838386847_60.jpg","goods_price":"消费积分 200 + ¥30.00","goods_marketprice":"120.00"}]}
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
         * member_score : 20
         * goods : [{"goods_name":"积分小商品","score":"100","goods_image":"1_05526719352178284.jpg","price":"20.00","goods_id":"112","store_id":"1","shop_score":"100","scoreprice":"0.00","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719352178284_60.jpg","goods_price":"消费积分 100 + ¥0.00","goods_marketprice":"20.00"},{"goods_name":"积分商品","score":"100","goods_image":"1_05536899838386847.jpg","price":"120.00","goods_id":"12","store_id":"1","shop_score":"200","scoreprice":"30.00","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05536899838386847_60.jpg","goods_price":"消费积分 200 + ¥30.00","goods_marketprice":"120.00"}]
         */

        private String member_score;
        private List<GoodsBean> goods;

        public String getMember_score() {
            return member_score;
        }

        public void setMember_score(String member_score) {
            this.member_score = member_score;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_name : 积分小商品
             * score : 100
             * goods_image : 1_05526719352178284.jpg
             * price : 20.00
             * goods_id : 112
             * store_id : 1
             * shop_score : 100
             * scoreprice : 0.00
             * img_url : http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719352178284_60.jpg
             * goods_price : 消费积分 100 + ¥0.00
             * goods_marketprice : 20.00
             */

            private String goods_name;
            private String score;
            private String goods_image;
            private String price;
            private String goods_id;
            private String store_id;
            private String shop_score;
            private String scoreprice;
            private String img_url;
            private String goods_price;
            private String goods_marketprice;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getShop_score() {
                return shop_score;
            }

            public void setShop_score(String shop_score) {
                this.shop_score = shop_score;
            }

            public String getScoreprice() {
                return scoreprice;
            }

            public void setScoreprice(String scoreprice) {
                this.scoreprice = scoreprice;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_marketprice() {
                return goods_marketprice;
            }

            public void setGoods_marketprice(String goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }
        }
    }
}
