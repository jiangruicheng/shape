package com.cndll.shapetest.bean;

/**
 * Created by jiangruicheng on 2017/8/4.
 */

public class CommodityVerInfoMode extends BaseBinding {
    private String name;
    private String nowPrice;
    private String oldPreci;
    private String score;
    private String storeID;
    private String goodsID;
    private String imgUrl;

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

    public String getOldPreci() {
        return oldPreci;
    }

    public void setOldPreci(String oldPreci) {
        this.oldPreci = oldPreci;
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
    private String orderScore;
    private String goodsUrl;

}
