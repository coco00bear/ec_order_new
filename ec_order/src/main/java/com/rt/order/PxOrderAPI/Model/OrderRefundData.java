package com.rt.order.PxOrderAPI.Model;

//訂單退款單
public class OrderRefundData{

    private Integer order_ref_money;//應退金額 DELIVERY_ORDER_RETURN.RETURN_PRICE
    private Integer order_ref_surcharge;//扣除手續費
    private String order_ref_date;//顧客發起取消時間 DELIVERY_ORDER_RETURN.RETURN_DATE
    private String order_ref_remarks;//備註

    public Integer getOrder_ref_money() {
        return order_ref_money;
    }
    public void setOrder_ref_money(Integer order_ref_money) {
        this.order_ref_money = order_ref_money;
    }
    public Integer getOrder_ref_surcharge() {
        return order_ref_surcharge;
    }
    public void setOrder_ref_surcharge(Integer order_ref_surcharge) {
        this.order_ref_surcharge = order_ref_surcharge;
    }
    public String getOrder_ref_date() {
        return order_ref_date;
    }
    public void setOrder_ref_date(String order_ref_date) {
        this.order_ref_date = order_ref_date;
    }
    public String getOrder_ref_remarks() {
        return order_ref_remarks;
    }
    public void setOrder_ref_remarks(String order_ref_remarks) {
        this.order_ref_remarks = order_ref_remarks;
    }
}
