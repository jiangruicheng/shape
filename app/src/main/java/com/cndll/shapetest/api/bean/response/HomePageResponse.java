package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/7/25.
 */

public class HomePageResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"tag":[{"gc_id":"1","gc_name":"首页"},{"gc_id":"2","gc_name":"农产"},{"gc_id":"3","gc_name":"美食"},{"gc_id":"4","gc_name":"水果"},{"gc_id":"5","gc_name":"家居"},{"gc_id":"6","gc_name":"电器"},{"gc_id":"7","gc_name":"美妆"}],"carousel":[{"img":"http://www.shop.com/data/upload/shop/goods_class/05503182886346127.jpg","url":"www.baidu.com"},{"img":"http://www.shop.com/data/upload/shop/goods_class/05503182886384005.jpg","url":"www.baidu.com"},{"img":"http://www.shop.com/data/upload/shop/goods_class/05503182886432292.jpg","url":"www.baidu.com"},{"img":"http://www.shop.com/data/upload/shop/goods_class/05503182886474177.jpg","url":"www.baidu.com"},{"img":"http://www.shop.com/data/upload/shop/goods_class/05503182886517096.jpg","url":"www.baidu.com"}],"today_sale":[{"goods_id":"2","start_time":"1497005460","end_time":"1498819800","time":"仅剩14天23小时","goods_name":"欧莱雅","goods_price":"100.00","xianshi_price":"50.00","score":"0","image_url":"http://www.shop.com/data/upload/shop/store/goods/1/1_05503190637395679_240.jpg","goods_url":"http://www.shop.com/mobile/index.php?act=goods&op=index&goods_id=2"},{"goods_id":"8","start_time":"1497005460","end_time":"1498819800","time":"仅剩14天23小时","goods_name":"美女图","goods_price":"100.00","xianshi_price":"10.00","score":"0","image_url":"http://www.shop.com/data/upload/shop/store/goods/1/1_05503189504148390_240.jpg","goods_url":"http://www.shop.com/mobile/index.php?act=goods&op=index&goods_id=8"}]}
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
        private List<TagBean> tag;
        private List<CarouselBean> carousel;
        private List<TodaySaleBean> today_sale;

        public List<TagBean> getTag() {
            return tag;
        }

        public void setTag(List<TagBean> tag) {
            this.tag = tag;
        }

        public List<CarouselBean> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<CarouselBean> carousel) {
            this.carousel = carousel;
        }

        public List<TodaySaleBean> getToday_sale() {
            return today_sale;
        }

        public void setToday_sale(List<TodaySaleBean> today_sale) {
            this.today_sale = today_sale;
        }

        public static class TagBean {
            /**
             * gc_id : 1
             * gc_name : 首页
             */

            private String gc_id;
            private String gc_name;

            public String getGc_id() {
                return gc_id;
            }

            public void setGc_id(String gc_id) {
                this.gc_id = gc_id;
            }

            public String getGc_name() {
                return gc_name;
            }

            public void setGc_name(String gc_name) {
                this.gc_name = gc_name;
            }
        }

        public static class CarouselBean {
            /**
             * img : http://www.shop.com/data/upload/shop/goods_class/05503182886346127.jpg
             * url : www.baidu.com
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

        public static class TodaySaleBean {
            /**
             * goods_id : 2
             * start_time : 1497005460
             * end_time : 1498819800
             * time : 仅剩14天23小时
             * goods_name : 欧莱雅
             * goods_price : 100.00
             * xianshi_price : 50.00
             * score : 0
             * image_url : http://www.shop.com/data/upload/shop/store/goods/1/1_05503190637395679_240.jpg
             * goods_url : http://www.shop.com/mobile/index.php?act=goods&op=index&goods_id=2
             */

            private String goods_id;
            private String start_time;
            private String end_time;
            private String time;
            private String goods_name;
            private String goods_price;
            private String xianshi_price;
            private String score;
            private String image_url;
            private String goods_url;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

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

            public String getXianshi_price() {
                return xianshi_price;
            }

            public void setXianshi_price(String xianshi_price) {
                this.xianshi_price = xianshi_price;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getGoods_url() {
                return goods_url;
            }

            public void setGoods_url(String goods_url) {
                this.goods_url = goods_url;
            }
        }
    }
}
