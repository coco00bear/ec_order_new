package com.rt.order.PxShipInfo.model;

public class OrderDetail {
    
    private Integer order_no;
    private Integer item_no;
    private Integer type;
    private String reply_time;
    private String  logistic_name;
    private String tracking_number;
    
    public Integer getOrder_no() {
        return order_no;
    }
    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }
    public Integer getItem_no() {
        return item_no;
    }
    public void setItem_no(Integer item_no) {
        this.item_no = item_no;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
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
    @Override
    public String toString() {
        return "OrderDetail [order_no=" + order_no + ", item_no=" + item_no + ", type=" + type + ", reply_time="
                + reply_time + ", logistic_name=" + logistic_name + ", tracking_number=" + tracking_number + "]";
    }
}
