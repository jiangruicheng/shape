package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/8/11.
 */

public class SearcheCommodityResponse extends BaseResponse {

    /**
     * code : 200
     * datas : {"goods":[{"score":"0","goods_name":"乳霜面霜","goods_marketprice":"200.00","goods_price":"100.00","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113778318337_60.jpg","goods_id":"7"}]}
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
        private List<GoodsBean> goods;

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * score : 0
             * goods_name : 乳霜面霜
             * goods_marketprice : 200.00
             * goods_price : 100.00
             * img_url : http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05524113778318337_60.jpg
             * goods_id : 7
             */

            private String score;
            private String goods_name;
            private String goods_marketprice;
            private String goods_price;
            private String img_url;
            private String goods_id;

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_marketprice() {
                return goods_marketprice;
            }

            public void setGoods_marketprice(String goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }
        }
    }
}
