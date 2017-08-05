package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/4.
 */

public class MemberResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"member_name":"15001372759","member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","is_member":"0","goods":[{"goods_name":"会员商品5","score":"22","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg","goods_price":"330.00","goods_marketprice":"440.00","goods_id":"136","store_id":"1"},{"goods_name":"会员商品4","score":"22","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05531865600492796_60.jpg","goods_price":"220.00","goods_marketprice":"330.00","goods_id":"134","store_id":"1"}]}
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
         * member_name : 15001372759
         * member_avatar : http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif
         * is_member : 0
         * goods : [{"goods_name":"会员商品5","score":"22","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg","goods_price":"330.00","goods_marketprice":"440.00","goods_id":"136","store_id":"1"},{"goods_name":"会员商品4","score":"22","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05531865600492796_60.jpg","goods_price":"220.00","goods_marketprice":"330.00","goods_id":"134","store_id":"1"}]
         */

        private String member_name;
        private String member_avatar;
        private String is_member;
        private List<GoodsBean> goods;

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getIs_member() {
            return is_member;
        }

        public void setIs_member(String is_member) {
            this.is_member = is_member;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_name : 会员商品5
             * score : 22
             * goods_image : http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg
             * goods_price : 330.00
             * goods_marketprice : 440.00
             * goods_id : 136
             * store_id : 1
             */

            private String goods_name;
            private String score;
            private String goods_image;
            private String goods_price;
            private String goods_marketprice;
            private String goods_id;
            private String store_id;

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
        }
    }
}
