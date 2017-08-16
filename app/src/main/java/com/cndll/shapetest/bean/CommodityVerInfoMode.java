package com.cndll.shapetest.bean;

/**
 * Created by jiangruicheng on 2017/8/4.
 */

public class CommodityVerInfoMode extends BaseBinding {
    public String name;
    public String nowPrice;
    public String oldPrice;
    public String score;
    public String storeID;
    public String goodsID;
    public String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(String orderScore) {
        this.orderScore = orderScore;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public boolean isShowButton() {
        return showButton;
    }

    public void setShowButton(boolean showButton) {
        this.showButton = showButton;
    }

    public boolean isButtonEnable() {
        return isButtonEnable;
    }

    public void setButtonEnable(boolean buttonEnable) {
        isButtonEnable = buttonEnable;
    }

    public boolean isButtonEnable;
    public boolean showButton;
    public String orderScore;
    public String goodsUrl;

}
