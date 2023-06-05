package com.rt.order.DeliveryOrders.Model;

public class DeliveryOrders {

    private Integer platformNo ;

    private Integer orderNo;

    private String idNo;

    private String displayNo;

    private Integer storeNo;

    private Integer extNo;

    private Integer status;

    private String eater;

    private String memo;

    private Integer total;

    private String createTime;
    
    private Integer deliveryCarriage;

    public Integer getDeliveryCarriage() {
        return deliveryCarriage;
    }

    public void setDeliveryCarriage(Integer deliveryCarriage) {
        this.deliveryCarriage = deliveryCarriage;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private Integer type;

    public Integer getPlatformNo() {
        return platformNo;
    }

    public void setPlatformNo(Integer platformNo) {
        this.platformNo = platformNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(String displayNo) {
        this.displayNo = displayNo;
    }

    public Integer getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    public Integer getExtNo() {
        return extNo;
    }

    public void setExtNo(Integer extNo) {
        this.extNo = extNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEater() {
        return eater;
    }

    public void setEater(String eater) {
        this.eater = eater;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DeliveryOrders [platformNo=" + platformNo + ", orderNo=" + orderNo + ", idNo=" + idNo + ", displayNo="
                + displayNo + ", storeNo=" + storeNo + ", extNo=" + extNo + ", status=" + status + ", eater=" + eater
                + ", memo=" + memo + ", total=" + total + ", createTime=" + createTime + ", deliveryCarriage="
                + deliveryCarriage + ", type=" + type + "]";
    }

    

}
