package com.rt.order.PxShipInfo.model;

public class ReverseParam {

    private Integer store_no;
    private Integer return_order_no;
    private Integer refund_no;
    private String status;
    
    public Integer getStore_no() {
        return store_no;
    }
    public void setStore_no(Integer store_no) {
        this.store_no = store_no;
    }
    public Integer getReturn_order_no() {
        return return_order_no;
    }
    public void setReturn_order_no(Integer return_order_no) {
        this.return_order_no = return_order_no;
    }
    public Integer getRefund_no() {
        return refund_no;
    }
    public void setRefund_no(Integer refund_no) {
        this.refund_no = refund_no;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "ReverseParam [store_no=" + store_no + ", return_order_no=" + return_order_no + ", refund_no="
                + refund_no + ", status=" + status + "]";
    }
}
