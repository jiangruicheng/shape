package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;
import com.cndll.shapetest.bean.anno.Variable;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/21.
 */

public class ClassCommodity extends BaseResponse {

    /**
     * code : 200
     * datas : [{"goods_id":"136","goods_image":"1_05526720925778907.jpg","store_id":"1","goods_name":"会员商品5","goods_jingle":"会员商品5","score":"22","goods_price":"330.00","goods_marketprice":"440.00","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg"},{"goods_id":"134","goods_image":"1_05531865600492796.jpg","store_id":"1","goods_name":"会员商品4","goods_jingle":"会员商品4","score":"22","goods_price":"220.00","goods_marketprice":"330.00","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05531865600492796_60.jpg"}]
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
         * goods_id : 136
         * goods_image : 1_05526720925778907.jpg
         * store_id : 1
         * goods_name : 会员商品5
         * goods_jingle : 会员商品5
         * score : 22
         * goods_price : 330.00
         * goods_marketprice : 440.00
         * img_url : http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg
         */

        private String goods_id;

        public String goods_image;
        @Variable(variable = "storeId")

        public String store_id;
        @Variable(variable = "name")
        public String goods_name;
        private String goods_jingle;
        @Variable(variable = "score")

        public String score;
        @Variable(variable = "nowPrice")

        public String goods_price;
        @Variable(variable = "oldPrice")
        private String goods_marketprice;
        @Variable(variable = "imgUrl")

        public String img_url;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_jingle() {
            return goods_jingle;
        }

        public void setGoods_jingle(String goods_jingle) {
            this.goods_jingle = goods_jingle;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
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

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
