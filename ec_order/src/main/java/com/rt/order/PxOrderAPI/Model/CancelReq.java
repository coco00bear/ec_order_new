package com.rt.order.PxOrderAPI.Model;

public class CancelReq {
    private Integer order_uid;//RT訂單編號
    private Integer store_no;//店號
    private Integer status;//狀態
    private CancelData data;//訂單資訊
    public Integer getOrder_uid() {
        return order_uid;
    }
    public void setOrder_uid(Integer order_uid) {
        this.order_uid = order_uid;
    }
    public Integer getStore_no() {
        return store_no;
    }
    public void setStore_no(Integer store_no) {
        this.store_no = store_no;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public CancelData getData() {
        return data;
    }
    public void setData(CancelData data) {
        this.data = data;
    }
    
    
}
