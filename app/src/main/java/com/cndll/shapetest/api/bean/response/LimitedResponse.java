package com.cndll.shapetest.api.bean.response;

import com.cndll.shapetest.api.bean.BaseResponse;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/7/27.
 */

public class LimitedResponse extends BaseResponse {

    /**
     * code : 200
     * datas : [{"groupbuy_id":"15","groupbuy_name":"2017-06-15 15点","start_time":"1497510000","end_time":"1497520800","goods_id":"11","goods_commonid":"5","goods_name":"乳霜面霜","store_id":"1","store_name":"好商城V5","goods_price":"100.00","groupbuy_price":"15.00","groupbuy_rebate":"1.50","virtual_quantity":"0","upper_limit":"1","buyer_count":"0","buy_quantity":"0","groupbuy_intro":"","state":"20","recommended":"0","views":"0","class_id":"0","s_class_id":"0","groupbuy_image":"http://www.shop.com/data/upload/shop/groupbuy/1/1_05508529848378890_mid.jpg","groupbuy_image1":"","remark":"2017-06-15 15点","is_vr":"0","vr_city_id":null,"vr_area_id":null,"vr_mall_id":null,"vr_class_id":null,"vr_s_class_id":null,"score":"0","remind_state":0,"goods_url":"http://www.shop.com/mobile/index.php?act=goods&op=index&goods_id=11","goods_total":2212,"sale_proportion":0}]
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
         * groupbuy_id : 15
         * groupbuy_name : 2017-06-15 15点
         * start_time : 1497510000
         * end_time : 1497520800
         * goods_id : 11
         * goods_commonid : 5
         * goods_name : 乳霜面霜
         * store_id : 1
         * store_name : 好商城V5
         * goods_price : 100.00
         * groupbuy_price : 15.00
         * groupbuy_rebate : 1.50
         * virtual_quantity : 0
         * upper_limit : 1
         * buyer_count : 0
         * buy_quantity : 0
         * groupbuy_intro :
         * state : 20
         * recommended : 0
         * views : 0
         * class_id : 0
         * s_class_id : 0
         * groupbuy_image : http://www.shop.com/data/upload/shop/groupbuy/1/1_05508529848378890_mid.jpg
         * groupbuy_image1 :
         * remark : 2017-06-15 15点
         * is_vr : 0
         * vr_city_id : null
         * vr_area_id : null
         * vr_mall_id : null
         * vr_class_id : null
         * vr_s_class_id : null
         * score : 0
         * remind_state : 0
         * goods_url : http://www.shop.com/mobile/index.php?act=goods&op=index&goods_id=11
         * goods_total : 2212
         * sale_proportion : 0
         */

        private String groupbuy_id;
        private String groupbuy_name;
        private String start_time;
        private String end_time;
        private String goods_id;
        private String goods_commonid;
        private String goods_name;
        private String store_id;
        private String store_name;
        private String goods_price;
        private String groupbuy_price;
        private String groupbuy_rebate;
        private String virtual_quantity;
        private String upper_limit;
        private String buyer_count;
        private String buy_quantity;
        private String groupbuy_intro;
        private String state;
        private String recommended;
        private String views;
        private String class_id;
        private String s_class_id;
        private String groupbuy_image;
        private String groupbuy_image1;
        private String remark;
        private String is_vr;
        private Object vr_city_id;
        private Object vr_area_id;
        private Object vr_mall_id;
        private Object vr_class_id;
        private Object vr_s_class_id;
        private String score;
        private int remind_state;
        private String goods_url;
        private int goods_total;
        private int sale_proportion;

        public String getGroupbuy_id() {
            return groupbuy_id;
        }

        public void setGroupbuy_id(String groupbuy_id) {
            this.groupbuy_id = groupbuy_id;
        }

        public String getGroupbuy_name() {
            return groupbuy_name;
        }

        public void setGroupbuy_name(String groupbuy_name) {
            this.groupbuy_name = groupbuy_name;
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

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
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

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGroupbuy_price() {
            return groupbuy_price;
        }

        public void setGroupbuy_price(String groupbuy_price) {
            this.groupbuy_price = groupbuy_price;
        }

        public String getGroupbuy_rebate() {
            return groupbuy_rebate;
        }

        public void setGroupbuy_rebate(String groupbuy_rebate) {
            this.groupbuy_rebate = groupbuy_rebate;
        }

        public String getVirtual_quantity() {
            return virtual_quantity;
        }

        public void setVirtual_quantity(String virtual_quantity) {
            this.virtual_quantity = virtual_quantity;
        }

        public String getUpper_limit() {
            return upper_limit;
        }

        public void setUpper_limit(String upper_limit) {
            this.upper_limit = upper_limit;
        }

        public String getBuyer_count() {
            return buyer_count;
        }

        public void setBuyer_count(String buyer_count) {
            this.buyer_count = buyer_count;
        }

        public String getBuy_quantity() {
            return buy_quantity;
        }

        public void setBuy_quantity(String buy_quantity) {
            this.buy_quantity = buy_quantity;
        }

        public String getGroupbuy_intro() {
            return groupbuy_intro;
        }

        public void setGroupbuy_intro(String groupbuy_intro) {
            this.groupbuy_intro = groupbuy_intro;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getRecommended() {
            return recommended;
        }

        public void setRecommended(String recommended) {
            this.recommended = recommended;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getS_class_id() {
            return s_class_id;
        }

        public void setS_class_id(String s_class_id) {
            this.s_class_id = s_class_id;
        }

        public String getGroupbuy_image() {
            return groupbuy_image;
        }

        public void setGroupbuy_image(String groupbuy_image) {
            this.groupbuy_image = groupbuy_image;
        }

        public String getGroupbuy_image1() {
            return groupbuy_image1;
        }

        public void setGroupbuy_image1(String groupbuy_image1) {
            this.groupbuy_image1 = groupbuy_image1;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getIs_vr() {
            return is_vr;
        }

        public void setIs_vr(String is_vr) {
            this.is_vr = is_vr;
        }

        public Object getVr_city_id() {
            return vr_city_id;
        }

        public void setVr_city_id(Object vr_city_id) {
            this.vr_city_id = vr_city_id;
        }

        public Object getVr_area_id() {
            return vr_area_id;
        }

        public void setVr_area_id(Object vr_area_id) {
            this.vr_area_id = vr_area_id;
        }

        public Object getVr_mall_id() {
            return vr_mall_id;
        }

        public void setVr_mall_id(Object vr_mall_id) {
            this.vr_mall_id = vr_mall_id;
        }

        public Object getVr_class_id() {
            return vr_class_id;
        }

        public void setVr_class_id(Object vr_class_id) {
            this.vr_class_id = vr_class_id;
        }

        public Object getVr_s_class_id() {
            return vr_s_class_id;
        }

        public void setVr_s_class_id(Object vr_s_class_id) {
            this.vr_s_class_id = vr_s_class_id;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getRemind_state() {
            return remind_state;
        }

        public void setRemind_state(int remind_state) {
            this.remind_state = remind_state;
        }

        public String getGoods_url() {
            return goods_url;
        }

        public void setGoods_url(String goods_url) {
            this.goods_url = goods_url;
        }

        public int getGoods_total() {
            return goods_total;
        }

        public void setGoods_total(int goods_total) {
            this.goods_total = goods_total;
        }

        public int getSale_proportion() {
            return sale_proportion;
        }

        public void setSale_proportion(int sale_proportion) {
            this.sale_proportion = sale_proportion;
        }
    }
}
