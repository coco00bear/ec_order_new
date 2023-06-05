package com.rt.order.PxOrderAPI.Model;

//訂單取消
public class OrderCancelData{

    private String cancel_reason;//退貨原因
    private String cancel_memo;//退貨說明 DELIVERY_ORDER_RETURN.RETURN_MEMO
    private String contact_name;//聯絡人姓名 DELIVERY_ORDER_RETURN.CONTACT_NAME
    private String contact_tel;//聯絡人電話 DELIVERY_ORDER_RETURN.CONTACT_TEL

    public String getCancel_reason() {
        return cancel_reason;
    }
    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }
    public String getCancel_memo() {
        return cancel_memo;
    }
    public void setCancel_memo(String cancel_memo) {
        this.cancel_memo = cancel_memo;
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
