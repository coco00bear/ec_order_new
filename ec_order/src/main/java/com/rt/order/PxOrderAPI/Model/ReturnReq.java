package com.rt.order.PxOrderAPI.Model;

public class ReturnReq {

    private Integer order_uid;//訂單編號
    private Integer store_no;
    private Integer order_status;//訂單狀態
    private ReturnReqData data;//訂單資訊
    
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
    public Integer getOrder_status() {
        return order_status;
    }
    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }
    public ReturnReqData getData() {
        return data;
    }
    public void setData(ReturnReqData data) {
        this.data = data;
    }
}
