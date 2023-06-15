package com.rt.order.PxShipInfo.model;

public class ForwardParam {
    
    private Integer store_no;
    private Integer order_no;
    private String status;

    public Integer getStore_no() {
        return store_no;
    }
    public void setStore_no(Integer store_no) {
        this.store_no = store_no;
    }
    public Integer getOrder_no() {
        return order_no;
    }
    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "ForwardParam [store_no=" + store_no + ", order_no=" + order_no + ", status=" + status + "]";
    }
}
