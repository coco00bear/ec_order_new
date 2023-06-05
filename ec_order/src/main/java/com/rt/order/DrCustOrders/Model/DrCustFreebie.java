package com.rt.order.DrCustOrders.Model;

public class DrCustFreebie {
    
    private Integer order_no;
    
    private Integer pk_type;

    private Integer wmkt_uid;
    
    private String memo;

    private Integer qty;

    public Integer getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }

    public Integer getPk_type() {
        return pk_type;
    }

    public void setPk_type(Integer pk_type) {
        this.pk_type = pk_type;
    }

    public Integer getWmkt_uid() {
        return wmkt_uid;
    }

    public void setWmkt_uid(Integer wmkt_uid) {
        this.wmkt_uid = wmkt_uid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
