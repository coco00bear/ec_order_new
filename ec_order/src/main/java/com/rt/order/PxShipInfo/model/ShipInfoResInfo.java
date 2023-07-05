package com.rt.order.PxShipInfo.model;

public class ShipInfoResInfo {
    // "order_uid": 500580102,
    // "prod_uid": 41464,
    // "status": 130,
    // "reply_time": "2022-09-02T15:30:00",
    // "logistic_name": "宅配通",
    // "tracking_number": "999999999999"

    private Integer order_uid;
    private Integer prod_uid;
    private Integer status;
    private String reply_time;
    private String logistic_name;
    private String tracking_number;
    private Integer store_no;
    public Integer getOrder_uid() {
        return order_uid;
    }
    public void setOrder_uid(Integer order_uid) {
        this.order_uid = order_uid;
    }
    public Integer getProd_uid() {
        return prod_uid;
    }
    public void setProd_uid(Integer prod_uid) {
        this.prod_uid = prod_uid;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getReply_time() {
        return reply_time;
    }
    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }
    public String getLogistic_name() {
        return logistic_name;
    }
    public void setLogistic_name(String logistic_name) {
        this.logistic_name = logistic_name;
    }
    public String getTracking_number() {
        return tracking_number;
    }
    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }
    public Integer getStore_no() {
        return store_no;
    }
    public void setStore_no(Integer store_no) {
        this.store_no = store_no;
    }
    @Override
    public String toString() {
        return "ShipInfoResInfo [order_uid=" + order_uid + ", prod_uid=" + prod_uid + ", status=" + status
                + ", reply_time=" + reply_time + ", logistic_name=" + logistic_name + ", tracking_number="
                + tracking_number + ", store_no=" + store_no + "]";
    }

    
}
