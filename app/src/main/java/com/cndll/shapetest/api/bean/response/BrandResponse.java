package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/3.
 */

public class BrandResponse extends BaseResponse {

    /**
     * code : 200
     * datas : [{"goods_name":"品牌折扣3","goods_jingle":"品牌折扣3","goods_price":"40.00","goods_marketprice":"50.00","score":"11","goods_image":"1_05526719863643949.jpg","store_id":"1","goods_id":"39","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719863643949_60.jpg","goods_url":"http://zhongxiang.51edn.com/mobile/index.php?act=goods&op=index&goods_id=39"},{"goods_name":"品牌折扣2","goods_jingle":"品牌折扣2","goods_price":"30.00","goods_marketprice":"40.00","score":"0","goods_image":"1_05526719352178284.jpg","store_id":"1","goods_id":"37","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719352178284_60.jpg","goods_url":"http://zhongxiang.51edn.com/mobile/index.php?act=goods&op=index&goods_id=37"},{"goods_name":"品牌折扣","goods_jingle":"品牌折扣","goods_price":"20.00","goods_marketprice":"30.00","score":"11","goods_image":"1_05525927428271725.jpg","store_id":"1","goods_id":"29","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05525927428271725_60.jpg","goods_url":"http://zhongxiang.51edn.com/mobile/index.php?act=goods&op=index&goods_id=29"}]
     */

    private int code;
    private ArrayList<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * goods_name : 品牌折扣3
         * goods_jingle : 品牌折扣3
         * goods_price : 40.00
         * goods_marketprice : 50.00
         * score : 11
         * goods_image : 1_05526719863643949.jpg
         * store_id : 1
         * goods_id : 39
         * img_url : http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719863643949_60.jpg
         * goods_url : http://zhongxiang.51edn.com/mobile/index.php?act=goods&op=index&goods_id=39
         */

        private String goods_name;
        private String goods_jingle;
        private String goods_price;
        private String goods_marketprice;
        private String score;
        private String goods_image;
        private String store_id;
        private String goods_id;
        private String img_url;
        private String goods_url;

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

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getGoods_url() {
            return goods_url;
        }

        public void setGoods_url(String goods_url) {
            this.goods_url = goods_url;
        }
    }
}
