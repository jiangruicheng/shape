package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/11.
 */

public class SearchShopResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"store":[{"store_name":"小飞","store_id":"2","member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","goods_salenum":"4","count":12,"goods":[{"goods_image":"2_05513826366625209.jpg","goods_id":"17","goods_commonid":"8","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/2/2_05513826366625209_60.jpg"}]}]}
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
        private List<StoreBean> store;

        public List<StoreBean> getStore() {
            return store;
        }

        public void setStore(List<StoreBean> store) {
            this.store = store;
        }

        public static class StoreBean {
            /**
             * store_name : 小飞
             * store_id : 2
             * member_avatar : http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif
             * goods_salenum : 4
             * count : 12
             * goods : [{"goods_image":"2_05513826366625209.jpg","goods_id":"17","goods_commonid":"8","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/2/2_05513826366625209_60.jpg"}]
             */

            private String store_name;
            private String store_id;
            private String member_avatar;
            private String goods_salenum;
            private int count;
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
                 * goods_image : 2_05513826366625209.jpg
                 * goods_id : 17
                 * goods_commonid : 8
                 * img_url : http://zhongxiang.51edn.com/data/upload/shop/store/goods/2/2_05513826366625209_60.jpg
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
}
