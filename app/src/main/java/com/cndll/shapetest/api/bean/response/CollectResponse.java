package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/5 0005.
 * 收藏
 */

public class CollectResponse extends BaseResponse{

    /**
     * code : 200
     * datas : {"favorites_list":[{"goods_id":"5","goods_name":"彩妆笔 笔号 2H","goods_image":"1_05524113208321653.jpg","store_id":"1","goods_marketprice":"200.00","fav_id":"5","goods_image_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113208321653_240.jpg","goods_price":"100.00"}]}
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
        private List<FavoritesListBean> favorites_list;

        public List<FavoritesListBean> getFavorites_list() {
            return favorites_list;
        }

        public void setFavorites_list(List<FavoritesListBean> favorites_list) {
            this.favorites_list = favorites_list;
        }

        public static class FavoritesListBean {
            /**
             * goods_id : 5
             * goods_name : 彩妆笔 笔号 2H
             * goods_image : 1_05524113208321653.jpg
             * store_id : 1
             * goods_marketprice : 200.00
             * fav_id : 5
             * goods_image_url : http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113208321653_240.jpg
             * goods_price : 100.00
             */

            private String goods_id;
            private String goods_name;
            private String goods_image;
            private String store_id;
            private String goods_marketprice;
            private String fav_id;
            private String goods_image_url;
            private String goods_price;

            private String store_name;
            private String goods_count;
            private String store_sales;
            private String store_avatar_url;

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getGoods_count() {
                return goods_count;
            }

            public void setGoods_count(String goods_count) {
                this.goods_count = goods_count;
            }

            public String getStore_sales() {
                return store_sales;
            }

            public void setStore_sales(String store_sales) {
                this.store_sales = store_sales;
            }

            public String getStore_avatar_url() {
                return store_avatar_url;
            }

            public void setStore_avatar_url(String store_avatar_url) {
                this.store_avatar_url = store_avatar_url;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
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

            public String getGoods_marketprice() {
                return goods_marketprice;
            }

            public void setGoods_marketprice(String goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }

            public String getFav_id() {
                return fav_id;
            }

            public void setFav_id(String fav_id) {
                this.fav_id = fav_id;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }
        }
    }
}
