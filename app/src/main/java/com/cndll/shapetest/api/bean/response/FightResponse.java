package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/7/27.
 */

public class FightResponse extends BaseResponse {

    /**
     * code : 200
     * datas : [{"fight_groups_id":"13","fight_groups_people":"2","fight_groups_price":"20.00","fight_groups_aloneprice":"25.00","goods_commonid":"44","store_id":"1","goods_id":"81","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719863643949_60.jpg","score":"22","goods_name":"平团商品3","goods_jingle":"平团商品3"},{"fight_groups_id":"14","fight_groups_people":"3","fight_groups_price":"10.00","fight_groups_aloneprice":"20.00","goods_commonid":"43","store_id":"1","goods_id":"79","img_url":"http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526720925778907_60.jpg","score":"33","goods_name":"平团商品2","goods_jingle":"平团商品2"}]
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
         * fight_groups_id : 13
         * fight_groups_people : 2
         * fight_groups_price : 20.00
         * fight_groups_aloneprice : 25.00
         * goods_commonid : 44
         * store_id : 1
         * goods_id : 81
         * img_url : http://zhongxiang.51edn.com/data/upload/shop/store/goods/1/1_05526719863643949_60.jpg
         * score : 22
         * goods_name : 平团商品3
         * goods_jingle : 平团商品3
         */

        private String fight_groups_id;
        private String fight_groups_people;
        private String fight_groups_price;
        private String fight_groups_aloneprice;
        private String goods_commonid;
        private String store_id;
        private String goods_id;
        private String img_url;
        private String score;
        private String goods_name;
        private String goods_jingle;

        public String getFight_groups_id() {
            return fight_groups_id;
        }

        public void setFight_groups_id(String fight_groups_id) {
            this.fight_groups_id = fight_groups_id;
        }

        public String getFight_groups_people() {
            return fight_groups_people;
        }

        public void setFight_groups_people(String fight_groups_people) {
            this.fight_groups_people = fight_groups_people;
        }

        public String getFight_groups_price() {
            return fight_groups_price;
        }

        public void setFight_groups_price(String fight_groups_price) {
            this.fight_groups_price = fight_groups_price;
        }

        public String getFight_groups_aloneprice() {
            return fight_groups_aloneprice;
        }

        public void setFight_groups_aloneprice(String fight_groups_aloneprice) {
            this.fight_groups_aloneprice = fight_groups_aloneprice;
        }

        public String getGoods_commonid() {
            return goods_commonid;
        }

        public void setGoods_commonid(String goods_commonid) {
            this.goods_commonid = goods_commonid;
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

        public String getGoods_jingle() {
            return goods_jingle;
        }

        public void setGoods_jingle(String goods_jingle) {
            this.goods_jingle = goods_jingle;
        }
    }
}
