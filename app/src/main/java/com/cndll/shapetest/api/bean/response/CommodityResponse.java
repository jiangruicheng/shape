package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/7/27.
 */

public class CommodityResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"detail_imgs":[],"goods_name":"乳霜面霜","goods_freight":"0.00","goods_commonid":"3","is_store_favorate":0,"goods_custom":[],"goods_type":"赵奕欢","goods_body":"","goods_salenum":5,"goods":[{"goods_type_name":"美女","goods_id":"7","goods_info":{"so_price":"100.00","now_price":"200.00","goods_storage":"97","end_time":"","is_goods_favorate":0,"score":"0","scoreprice":"","goods_score":""}},{"goods_type_name":"妹子","goods_id":"8","goods_info":{"so_price":"100.00","now_price":"200.00","goods_storage":"98","end_time":"","is_goods_favorate":0,"score":"0","scoreprice":"","goods_score":""}}],"groups_order_num":0,"carousel":["http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113961130819_60.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113938243904_60.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113913284285_60.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113891840356_60.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113983626143_60.jpg"],"store_id":"1","store_name":"好商城V5","store_label":"http://zhongxiang.51edn.com/data/upload/shop/store/05531864680209995.jpg","store_collect":"0","store_count":40,"evaluate_all":5,"evaluate_good":5,"good_percent":100,"commtents":[{"geval_image":["http://zhongxiang.51edn.com/data/upload/shop/member/2/2_05542082923462465_240.jpg"],"geval_addtime":"2017-07-24 | 美女","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"11111","geval_frommembername":"xiaofei","geval_id":"10","geval_scores":"5"},{"geval_image":["http://zhongxiang.51edn.com/data/upload/shop/common/default_goods_image_240.gif","http://zhongxiang.51edn.com/data/upload/shop/common/default_goods_image_240.gif"],"geval_addtime":"2017-06-13","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"再来","geval_frommembername":"xiaofei","geval_id":"7","geval_scores":"5"},{"geval_image":["http://zhongxiang.51edn.com/data/upload/shop/common/default_goods_image_240.gif"],"geval_addtime":"2017-06-13","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"使用","geval_frommembername":"xiaofei","geval_id":"6","geval_scores":"5"},{"geval_image":"","geval_addtime":"2017-06-13","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"很好","geval_frommembername":"xiaofei","geval_id":"5","geval_scores":"5"},{"geval_image":"","geval_addtime":"2017-06-13","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"完美","geval_frommembername":"xiaofei","geval_id":"4","geval_scores":"5"}],"areaid_1":"0","areaid_2":"0","provice":"","city":""}
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
         * detail_imgs : []
         * goods_name : 乳霜面霜
         * goods_freight : 0.00
         * goods_commonid : 3
         * is_store_favorate : 0
         * goods_custom : []
         * goods_type : 赵奕欢
         * goods_body :
         * goods_salenum : 5
         * goods : [{"goods_type_name":"美女","goods_id":"7","goods_info":{"so_price":"100.00","now_price":"200.00","goods_storage":"97","end_time":"","is_goods_favorate":0,"score":"0","scoreprice":"","goods_score":""}},{"goods_type_name":"妹子","goods_id":"8","goods_info":{"so_price":"100.00","now_price":"200.00","goods_storage":"98","end_time":"","is_goods_favorate":0,"score":"0","scoreprice":"","goods_score":""}}]
         * groups_order_num : 0
         * carousel : ["http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113961130819_60.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113938243904_60.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113913284285_60.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113891840356_60.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113983626143_60.jpg"]
         * store_id : 1
         * store_name : 好商城V5
         * store_label : http://zhongxiang.51edn.com/data/upload/shop/store/05531864680209995.jpg
         * store_collect : 0
         * store_count : 40
         * evaluate_all : 5
         * evaluate_good : 5
         * good_percent : 100
         * commtents : [{"geval_image":["http://zhongxiang.51edn.com/data/upload/shop/member/2/2_05542082923462465_240.jpg"],"geval_addtime":"2017-07-24 | 美女","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"11111","geval_frommembername":"xiaofei","geval_id":"10","geval_scores":"5"},{"geval_image":["http://zhongxiang.51edn.com/data/upload/shop/common/default_goods_image_240.gif","http://zhongxiang.51edn.com/data/upload/shop/common/default_goods_image_240.gif"],"geval_addtime":"2017-06-13","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"再来","geval_frommembername":"xiaofei","geval_id":"7","geval_scores":"5"},{"geval_image":["http://zhongxiang.51edn.com/data/upload/shop/common/default_goods_image_240.gif"],"geval_addtime":"2017-06-13","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"使用","geval_frommembername":"xiaofei","geval_id":"6","geval_scores":"5"},{"geval_image":"","geval_addtime":"2017-06-13","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"很好","geval_frommembername":"xiaofei","geval_id":"5","geval_scores":"5"},{"geval_image":"","geval_addtime":"2017-06-13","geavl_memberimg":"http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif","geval_content":"完美","geval_frommembername":"xiaofei","geval_id":"4","geval_scores":"5"}]
         * areaid_1 : 0
         * areaid_2 : 0
         * provice :
         * city :
         */

        private String goods_name;
        private String goods_freight;
        private String goods_commonid;
        private int is_store_favorate;
        private String goods_type;
        private String goods_body;
        private int goods_salenum;
        private int groups_order_num;
        private String store_id;
        private String store_name;
        private String store_label;
        private String store_collect;
        private int store_count;
        private int evaluate_all;
        private int evaluate_good;
        private int good_percent;
        private String areaid_1;
        private String areaid_2;
        private String provice;
        private String city;
        private List<?> detail_imgs;
        private List<?> goods_custom;
        private List<GoodsBean> goods;
        private List<String> carousel;
        private List<CommtentsBean> commtents;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_freight() {
            return goods_freight;
        }

        public void setGoods_freight(String goods_freight) {
            this.goods_freight = goods_freight;
        }

        public String getGoods_commonid() {
            return goods_commonid;
        }

        public void setGoods_commonid(String goods_commonid) {
            this.goods_commonid = goods_commonid;
        }

        public int getIs_store_favorate() {
            return is_store_favorate;
        }

        public void setIs_store_favorate(int is_store_favorate) {
            this.is_store_favorate = is_store_favorate;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getGoods_body() {
            return goods_body;
        }

        public void setGoods_body(String goods_body) {
            this.goods_body = goods_body;
        }

        public int getGoods_salenum() {
            return goods_salenum;
        }

        public void setGoods_salenum(int goods_salenum) {
            this.goods_salenum = goods_salenum;
        }

        public int getGroups_order_num() {
            return groups_order_num;
        }

        public void setGroups_order_num(int groups_order_num) {
            this.groups_order_num = groups_order_num;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_label() {
            return store_label;
        }

        public void setStore_label(String store_label) {
            this.store_label = store_label;
        }

        public String getStore_collect() {
            return store_collect;
        }

        public void setStore_collect(String store_collect) {
            this.store_collect = store_collect;
        }

        public int getStore_count() {
            return store_count;
        }

        public void setStore_count(int store_count) {
            this.store_count = store_count;
        }

        public int getEvaluate_all() {
            return evaluate_all;
        }

        public void setEvaluate_all(int evaluate_all) {
            this.evaluate_all = evaluate_all;
        }

        public int getEvaluate_good() {
            return evaluate_good;
        }

        public void setEvaluate_good(int evaluate_good) {
            this.evaluate_good = evaluate_good;
        }

        public int getGood_percent() {
            return good_percent;
        }

        public void setGood_percent(int good_percent) {
            this.good_percent = good_percent;
        }

        public String getAreaid_1() {
            return areaid_1;
        }

        public void setAreaid_1(String areaid_1) {
            this.areaid_1 = areaid_1;
        }

        public String getAreaid_2() {
            return areaid_2;
        }

        public void setAreaid_2(String areaid_2) {
            this.areaid_2 = areaid_2;
        }

        public String getProvice() {
            return provice;
        }

        public void setProvice(String provice) {
            this.provice = provice;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<?> getDetail_imgs() {
            return detail_imgs;
        }

        public void setDetail_imgs(List<?> detail_imgs) {
            this.detail_imgs = detail_imgs;
        }

        public List<?> getGoods_custom() {
            return goods_custom;
        }

        public void setGoods_custom(List<?> goods_custom) {
            this.goods_custom = goods_custom;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<String> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<String> carousel) {
            this.carousel = carousel;
        }

        public List<CommtentsBean> getCommtents() {
            return commtents;
        }

        public void setCommtents(List<CommtentsBean> commtents) {
            this.commtents = commtents;
        }

        public static class GoodsBean {
            /**
             * goods_type_name : 美女
             * goods_id : 7
             * goods_info : {"so_price":"100.00","now_price":"200.00","goods_storage":"97","end_time":"","is_goods_favorate":0,"score":"0","scoreprice":"","goods_score":""}
             */

            private String goods_type_name;
            private String goods_id;
            private GoodsInfoBean goods_info;

            public String getGoods_type_name() {
                return goods_type_name;
            }

            public void setGoods_type_name(String goods_type_name) {
                this.goods_type_name = goods_type_name;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public GoodsInfoBean getGoods_info() {
                return goods_info;
            }

            public void setGoods_info(GoodsInfoBean goods_info) {
                this.goods_info = goods_info;
            }

            public static class GoodsInfoBean {
                /**
                 * so_price : 100.00
                 * now_price : 200.00
                 * goods_storage : 97
                 * end_time :
                 * is_goods_favorate : 0
                 * score : 0
                 * scoreprice :
                 * goods_score :
                 */

                private String so_price;
                private String now_price;
                private String goods_storage;
                private String end_time;
                private int is_goods_favorate;
                private String score;
                private String scoreprice;
                private String goods_score;

                public String getSo_price() {
                    return so_price;
                }

                public void setSo_price(String so_price) {
                    this.so_price = so_price;
                }

                public String getNow_price() {
                    return now_price;
                }

                public void setNow_price(String now_price) {
                    this.now_price = now_price;
                }

                public String getGoods_storage() {
                    return goods_storage;
                }

                public void setGoods_storage(String goods_storage) {
                    this.goods_storage = goods_storage;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public int getIs_goods_favorate() {
                    return is_goods_favorate;
                }

                public void setIs_goods_favorate(int is_goods_favorate) {
                    this.is_goods_favorate = is_goods_favorate;
                }

                public String getScore() {
                    return score;
                }

                public void setScore(String score) {
                    this.score = score;
                }

                public String getScoreprice() {
                    return scoreprice;
                }

                public void setScoreprice(String scoreprice) {
                    this.scoreprice = scoreprice;
                }

                public String getGoods_score() {
                    return goods_score;
                }

                public void setGoods_score(String goods_score) {
                    this.goods_score = goods_score;
                }
            }
        }

        public static class CommtentsBean {
            /**
             * geval_image : ["http://zhongxiang.51edn.com/data/upload/shop/member/2/2_05542082923462465_240.jpg"]
             * geval_addtime : 2017-07-24 | 美女
             * geavl_memberimg : http://zhongxiang.51edn.com/data/upload/shop/common/default_user_portrait.gif
             * geval_content : 11111
             * geval_frommembername : xiaofei
             * geval_id : 10
             * geval_scores : 5
             */

            private String geval_addtime;
            private String geavl_memberimg;
            private String geval_content;
            private String geval_frommembername;
            private String geval_id;
            private String geval_scores;
            private List<String> geval_image;

            public String getGeval_addtime() {
                return geval_addtime;
            }

            public void setGeval_addtime(String geval_addtime) {
                this.geval_addtime = geval_addtime;
            }

            public String getGeavl_memberimg() {
                return geavl_memberimg;
            }

            public void setGeavl_memberimg(String geavl_memberimg) {
                this.geavl_memberimg = geavl_memberimg;
            }

            public String getGeval_content() {
                return geval_content;
            }

            public void setGeval_content(String geval_content) {
                this.geval_content = geval_content;
            }

            public String getGeval_frommembername() {
                return geval_frommembername;
            }

            public void setGeval_frommembername(String geval_frommembername) {
                this.geval_frommembername = geval_frommembername;
            }

            public String getGeval_id() {
                return geval_id;
            }

            public void setGeval_id(String geval_id) {
                this.geval_id = geval_id;
            }

            public String getGeval_scores() {
                return geval_scores;
            }

            public void setGeval_scores(String geval_scores) {
                this.geval_scores = geval_scores;
            }

            public List<String> getGeval_image() {
                return geval_image;
            }

            public void setGeval_image(List<String> geval_image) {
                this.geval_image = geval_image;
            }
        }
    }
}
