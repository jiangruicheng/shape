package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;
import com.cndll.shapetest.bean.anno.Variable;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/21.
 */

public class ClassShop extends BaseResponse {

    /**
     * code : 200
     * datas : [{"store_name":"好商城V5","store_id":"1","member_avatar":"http://www.zhongxiang.com/data/upload/shop/avatar/avatar_1.jpg","goods_salenum":"63","count":21,"goods":[{"goods_image":"1_05524945474322931.jpg","goods_id":"2","goods_commonid":"1","img_url":"http://www.zhongxiang.com/data/upload/shop/store/goods/1/1_05524945474322931_60.jpg"}]}]
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
         * store_name : 好商城V5
         * store_id : 1
         * member_avatar : http://www.zhongxiang.com/data/upload/shop/avatar/avatar_1.jpg
         * goods_salenum : 63
         * count : 21
         * goods : [{"goods_image":"1_05524945474322931.jpg","goods_id":"2","goods_commonid":"1","img_url":"http://www.zhongxiang.com/data/upload/shop/store/goods/1/1_05524945474322931_60.jpg"}]
         */
        @Variable(variable = "shopName")
        public String store_name;
        private String store_id;
        @Variable(variable = "logoUrl")
        public String member_avatar;
        @Variable(variable = "salenum")
        public String goods_salenum;
        @Variable(variable = "count")
        public int count;
        private List<GoodsBean> goods;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getGoods_salenum() {
            return goods_salenum;
        }

        public void setGoods_salenum(String goods_salenum) {
            this.goods_salenum = goods_salenum;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_image : 1_05524945474322931.jpg
             * goods_id : 2
             * goods_commonid : 1
             * img_url : http://www.zhongxiang.com/data/upload/shop/store/goods/1/1_05524945474322931_60.jpg
             */

            private String goods_image;
            private String goods_id;
            private String goods_commonid;
            private String img_url;

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_commonid() {
                return goods_commonid;
            }

            public void setGoods_commonid(String goods_commonid) {
                this.goods_commonid = goods_commonid;
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
