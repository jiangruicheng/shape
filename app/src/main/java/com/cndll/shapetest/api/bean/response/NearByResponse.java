package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/5.
 */

public class NearByResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"carousel":[{"img":"http://zhongxiang.51edn.com/data/upload/shop/goods_class/05531078148042064.jpg","url":"http:pic.yesky.com/tag/all/1/%E6%88%90%E4%BA%BA%E5%9B%BE%E7%89%87/"},{"img":"http://zhongxiang.51edn.com/data/upload/shop/goods_class/05531078148058532.jpg","url":"http:pic.yesky.com/tag/all/1/%E6%88%90%E4%BA%BA%E5%9B%BE%E7%89%87/"},{"img":"http://zhongxiang.51edn.com/data/upload/shop/goods_class/05531078148068976.jpg","url":"http:pic.yesky.com/tag/all/1/%E6%88%90%E4%BA%BA%E5%9B%BE%E7%89%87/"},{"img":"http://zhongxiang.51edn.com/data/upload/shop/goods_class/05531078148079249.jpg","url":"http:pic.yesky.com/tag/all/1/%E6%88%90%E4%BA%BA%E5%9B%BE%E7%89%87/"},{"img":"http://zhongxiang.51edn.com/data/upload/shop/goods_class/05531078148087750.jpg","url":"http:pic.yesky.com/tag/all/1/%E6%88%90%E4%BA%BA%E5%9B%BE%E7%89%87/"}],"store":[{"store_id":"1","store_label":"05531864680209995.jpg","store_name":"好商城V5","store_address":"宝安中心","store_slide":"05531008137011281.jpg,05531008159350517.jpg,05531008182373984.jpg,05531008205687228.jpg,05531008229746628.jpg","store_workingtime":"09:00-23:00","member_id":"1","store_avatar":null,"store_lng":"113.891709","store_lat":"22.555733","store_phone":"15812345678","mobile_body":"<div>店铺介绍。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。<\/div><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05527585873091159_1280.jpg\"><div>（1）每次插入文字不能超过500个字，标点、特殊字符按照一个字计算； （2）请手动输入文字，不要复制粘贴网页上的文字，防止出现乱码； （3）以下特殊字符\u201c\u201d、\u201c\u201d、\u201c\u201d、\u201c\u201d、\u201c\u201d会被替换为空。 建议：不要添加太多的文字，这样看起来更清晰。<\/div><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_1280.jpg\"><div>测试数据标签<\/div><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05525053245767266_1280.jpg\"><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524114805035914_1280.jpg\"><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719863643949_1280.jpg\"><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05525927428271725_1280.jpg\"><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05527585873091159_1280.jpg\"><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05531865893135280_1280.jpg\"><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524112317113129_1280.jpg\"><img src=\"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113261952806_1280.jpg\">","score":"0","img_url":["http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008137011281.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008159350517.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008182373984.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008205687228.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008229746628.jpg"],"member_avatar":"http://zhongxiang.51edn.com/data/upload/shop/store/05531864680209995.jpg","goods":[{"goods_name":"附近美食1","goods_price":"20.00","goods_id":"66","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg"},{"goods_name":"附近美食2","goods_price":"30.00","goods_id":"69","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg"},{"goods_name":"附近美食3","goods_price":"10.00","goods_id":"70","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05531865600492796_60.jpg"},{"goods_name":"附近美食4","goods_price":"110.00","goods_id":"71","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05531865893135280_60.jpg"},{"goods_name":"附近美食5","goods_price":"11110.00","goods_id":"72","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05525927428271725_60.jpg"},{"goods_name":"附近美食6","goods_price":"220.00","goods_id":"73","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05525053245767266_60.jpg"},{"goods_name":"附近美食7","goods_price":"230.00","goods_id":"74","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719352178284_60.jpg"},{"goods_name":"附近美食8","goods_price":"2.00","goods_id":"75","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719863643949_60.jpg"}]}]}
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
        private List<CarouselBean> carousel;
        private List<StoreBean> store;

        public List<CarouselBean> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<CarouselBean> carousel) {
            this.carousel = carousel;
        }

        public List<StoreBean> getStore() {
            return store;
        }

        public void setStore(List<StoreBean> store) {
            this.store = store;
        }

        public static class CarouselBean {
            /**
             * img : http://zhongxiang.51edn.com/data/upload/shop/goods_class/05531078148042064.jpg
             * url : http:pic.yesky.com/tag/all/1/%E6%88%90%E4%BA%BA%E5%9B%BE%E7%89%87/
             */

            private String img;
            private String url;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class StoreBean {
            /**
             * store_id : 1
             * store_label : 05531864680209995.jpg
             * store_name : 好商城V5
             * store_address : 宝安中心
             * store_slide : 05531008137011281.jpg,05531008159350517.jpg,05531008182373984.jpg,05531008205687228.jpg,05531008229746628.jpg
             * store_workingtime : 09:00-23:00
             * member_id : 1
             * store_avatar : null
             * store_lng : 113.891709
             * store_lat : 22.555733
             * store_phone : 15812345678
             * mobile_body : <div>店铺介绍。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。</div><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05527585873091159_1280.jpg"><div>（1）每次插入文字不能超过500个字，标点、特殊字符按照一个字计算； （2）请手动输入文字，不要复制粘贴网页上的文字，防止出现乱码； （3）以下特殊字符“”、“”、“”、“”、“”会被替换为空。 建议：不要添加太多的文字，这样看起来更清晰。</div><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_1280.jpg"><div>测试数据标签</div><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05525053245767266_1280.jpg"><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524114805035914_1280.jpg"><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719863643949_1280.jpg"><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05525927428271725_1280.jpg"><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05527585873091159_1280.jpg"><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05531865893135280_1280.jpg"><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524112317113129_1280.jpg"><img src="http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113261952806_1280.jpg">
             * score : 0
             * img_url : ["http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008137011281.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008159350517.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008182373984.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008205687228.jpg","http://zhongxiang.51edn.com/data/upload/shop/store/slide/05531008229746628.jpg"]
             * member_avatar : http://zhongxiang.51edn.com/data/upload/shop/store/05531864680209995.jpg
             * goods : [{"goods_name":"附近美食1","goods_price":"20.00","goods_id":"66","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg"},{"goods_name":"附近美食2","goods_price":"30.00","goods_id":"69","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg"},{"goods_name":"附近美食3","goods_price":"10.00","goods_id":"70","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05531865600492796_60.jpg"},{"goods_name":"附近美食4","goods_price":"110.00","goods_id":"71","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05531865893135280_60.jpg"},{"goods_name":"附近美食5","goods_price":"11110.00","goods_id":"72","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05525927428271725_60.jpg"},{"goods_name":"附近美食6","goods_price":"220.00","goods_id":"73","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05525053245767266_60.jpg"},{"goods_name":"附近美食7","goods_price":"230.00","goods_id":"74","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719352178284_60.jpg"},{"goods_name":"附近美食8","goods_price":"2.00","goods_id":"75","goods_image":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719863643949_60.jpg"}]
             */

            private String store_id;
            private String store_label;
            private String store_name;
            private String store_address;
            private String store_slide;
            private String store_workingtime;
            private String member_id;
            private Object store_avatar;
            private String store_lng;
            private String store_lat;
            private String store_phone;
            private String mobile_body;
            private String score;
            private String member_avatar;
            private List<String> img_url;
            private List<GoodsBean> goods;

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStore_label() {
                return store_label;
            }

            public void setStore_label(String store_label) {
                this.store_label = store_label;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getStore_address() {
                return store_address;
            }

            public void setStore_address(String store_address) {
                this.store_address = store_address;
            }

            public String getStore_slide() {
                return store_slide;
            }

            public void setStore_slide(String store_slide) {
                this.store_slide = store_slide;
            }

            public String getStore_workingtime() {
                return store_workingtime;
            }

            public void setStore_workingtime(String store_workingtime) {
                this.store_workingtime = store_workingtime;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public Object getStore_avatar() {
                return store_avatar;
            }

            public void setStore_avatar(Object store_avatar) {
                this.store_avatar = store_avatar;
            }

            public String getStore_lng() {
                return store_lng;
            }

            public void setStore_lng(String store_lng) {
                this.store_lng = store_lng;
            }

            public String getStore_lat() {
                return store_lat;
            }

            public void setStore_lat(String store_lat) {
                this.store_lat = store_lat;
            }

            public String getStore_phone() {
                return store_phone;
            }

            public void setStore_phone(String store_phone) {
                this.store_phone = store_phone;
            }

            public String getMobile_body() {
                return mobile_body;
            }

            public void setMobile_body(String mobile_body) {
                this.mobile_body = mobile_body;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getMember_avatar() {
                return member_avatar;
            }

            public void setMember_avatar(String member_avatar) {
                this.member_avatar = member_avatar;
            }

            public List<String> getImg_url() {
                return img_url;
            }

            public void setImg_url(List<String> img_url) {
                this.img_url = img_url;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * goods_name : 附近美食1
                 * goods_price : 20.00
                 * goods_id : 66
                 * goods_image : http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg
                 */

                private String goods_name;
                private String goods_price;
                private String goods_id;
                private String goods_image;

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

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
            }
        }
    }
}
