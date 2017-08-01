package com.cndll.shapetest.api.bean.response;

import java.util.List;


public class Test {


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


        private int fight_groups_id;
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
        private List<OrderFightGroupsInfoBean> order_fight_groups_info;
        private List<?> detail_imgs;
        private List<?> goods_custom;
        private List<GoodsBean> goods;
        private List<String> carousel;
        private List<?> commtents;

        public int getFight_groups_id() {
            return fight_groups_id;
        }

        public void setFight_groups_id(int fight_groups_id) {
            this.fight_groups_id = fight_groups_id;
        }

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

        public List<OrderFightGroupsInfoBean> getOrder_fight_groups_info() {
            return order_fight_groups_info;
        }

        public void setOrder_fight_groups_info(List<OrderFightGroupsInfoBean> order_fight_groups_info) {
            this.order_fight_groups_info = order_fight_groups_info;
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

        public List<?> getCommtents() {
            return commtents;
        }

        public void setCommtents(List<?> commtents) {
            this.commtents = commtents;
        }

        public static class OrderFightGroupsInfoBean {


            private String order_id;
            private String buyer_id;
            private String add_time;
            private String end_time;
            private String people_num;
            private String now_people_num;
            private String member_name;
            private String member_avatar;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(String buyer_id) {
                this.buyer_id = buyer_id;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getPeople_num() {
                return people_num;
            }

            public void setPeople_num(String people_num) {
                this.people_num = people_num;
            }

            public String getNow_people_num() {
                return now_people_num;
            }

            public void setNow_people_num(String now_people_num) {
                this.now_people_num = now_people_num;
            }

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
        }

        public static class GoodsBean {


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

                private String so_price;
                private String once_price;
                private String goods_storage;
                private String groups_price;
                private int is_goods_favorate;
                private String score;
                private String groups_people_num;

                public String getSo_price() {
                    return so_price;
                }

                public void setSo_price(String so_price) {
                    this.so_price = so_price;
                }

                public String getOnce_price() {
                    return once_price;
                }

                public void setOnce_price(String once_price) {
                    this.once_price = once_price;
                }

                public String getGoods_storage() {
                    return goods_storage;
                }

                public void setGoods_storage(String goods_storage) {
                    this.goods_storage = goods_storage;
                }

                public String getGroups_price() {
                    return groups_price;
                }

                public void setGroups_price(String groups_price) {
                    this.groups_price = groups_price;
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

                public String getGroups_people_num() {
                    return groups_people_num;
                }

                public void setGroups_people_num(String groups_people_num) {
                    this.groups_people_num = groups_people_num;
                }
            }
        }
    }
}
