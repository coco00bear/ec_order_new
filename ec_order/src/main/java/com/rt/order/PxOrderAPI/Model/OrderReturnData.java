package com.rt.order.PxOrderAPI.Model;

public class OrderReturnData {

    private String return_request_date;//顧客發起退或時間
    private String return_memo;//退貨說明
    private Integer order_ref_money;//退貨金額
    private String contact_name;//聯絡人姓名
    private String contact_tel;//聯絡人電話

    public String getReturn_request_date() {
        return return_request_date;
    }
    public void setReturn_request_date(String return_request_date) {
        this.return_request_date = return_request_date;
    }
    public String getReturn_memo() {
        return return_memo;
    }
    public void setReturn_memo(String return_memo) {
        this.return_memo = return_memo;
    }
    public Integer getOrder_ref_money() {
        return order_ref_money;
    }
    public void setOrder_ref_money(Integer order_ref_money) {
        this.order_ref_money = order_ref_money;
    }
    public String getContact_name() {
        return contact_name;
    }
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
    public String getContact_tel() {
        return contact_tel;
    }
    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }
}
