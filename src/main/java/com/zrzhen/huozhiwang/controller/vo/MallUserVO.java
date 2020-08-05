package com.zrzhen.huozhiwang.controller.vo;

/**
 * @author: 慧燕
 * @date: 2020/7/30 16:08
 * @copyright yanlongyun2020
 */
public class MallUserVO {
    /*保存用户基本信息*/
    private Long userId;
    private String nickName;
    private String loginName;
    private String introduceSign;
    private String address;
    private  int isDeleted;
    private  int lockedFlag;
    private int ShopCartItemCount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIntroduceSign() {
        return introduceSign;
    }

    public void setIntroduceSign(String introduceSign) {
        this.introduceSign = introduceSign;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getlockedFlag() {
        return lockedFlag;
    }

    public void setlockedFlag(int llockedFlag) {
        this.lockedFlag = llockedFlag;
    }

    public int getShopCartItemCount() {
        return ShopCartItemCount;
    }

    public void setShopCartItemCount(int shopCartItemCount) {
        ShopCartItemCount = shopCartItemCount;
    }
}

