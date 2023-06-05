package com.rt.order.DeliveryOrders.Model;

public class DeliveryOrderItems {
    
    private Integer platformNo ;

    private Integer orderNo;

    private Integer itemSeq;

    private Integer itemNo;

    private String name;

    private Integer status;

    private Integer type;

    private Integer qty;

    private Integer unitPrice;

    private Integer totalPrice;

    private Integer salesAmount;

    private Integer originalQty;

    private Integer totalAmount;

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

    public Integer getItemSeq() {
        return itemSeq;
    }

    public void setItemSeq(Integer itemSeq) {
        this.itemSeq = itemSeq;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getOriginalQty() {
        return originalQty;
    }

    public void setOriginalQty(Integer originalQty) {
        this.originalQty = originalQty;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "DeliveryOrderItems [platformNo=" + platformNo + ", orderNo=" + orderNo + ", itemSeq=" + itemSeq
                + ", itemNo=" + itemNo + ", name=" + name + ", status=" + status + ", type=" + type + ", qty=" + qty
                + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + ", salesAmount=" + salesAmount
                + ", originalQty=" + originalQty + ", totalAmount=" + totalAmount + "]";
    }
    
}
