package com.rt.order.DeliveryOrders.Model;

public class orders_data {

    private String px_order_no;
    private String order_addressee;
    private String order_email;
    private String order_telcellphone;
    private String order_city;
    private String order_zip;
    private String order_address;
    private Integer pay_money_ssl;
    private String order_date;
    private String order_remarks;
    private String recycle;
    private Integer collection_amount;
    
    public String getPx_order_no() {
        return px_order_no;
    }
    public void setPx_order_no(String px_order_no) {
        this.px_order_no = px_order_no;
    }
    public String getOrder_addressee() {
        return order_addressee;
    }
    public void setOrder_addressee(String order_addressee) {
        this.order_addressee = order_addressee;
    }
    public String getOrder_email() {
        return order_email;
    }
    public void setOrder_email(String order_email) {
        this.order_email = order_email;
    }
    public String getOrder_telcellphone() {
        return order_telcellphone;
    }
    public void setOrder_telcellphone(String order_telcellphone) {
        this.order_telcellphone = order_telcellphone;
    }
    public String getOrder_city() {
        return order_city;
    }
    public void setOrder_city(String order_city) {
        this.order_city = order_city;
    }
    public String getOrder_zip() {
        return order_zip;
    }
    public void setOrder_zip(String order_zip) {
        this.order_zip = order_zip;
    }
    public String getOrder_address() {
        return order_address;
    }
    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }
    public Integer getPay_money_ssl() {
        return pay_money_ssl;
    }
    public void setPay_money_ssl(Integer pay_money_ssl) {
        this.pay_money_ssl = pay_money_ssl;
    }
    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    public String getOrder_remarks() {
        return order_remarks;
    }
    public void setOrder_remarks(String order_remarks) {
        this.order_remarks = order_remarks;
    }
    public String getRecycle() {
        return recycle;
    }
    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }
    public Integer getCollection_amount() {
        return collection_amount;
    }
    public void setCollection_amount(Integer collection_amount) {
        this.collection_amount = collection_amount;
    }
    @Override
    public String toString() {
        return "orders_data [px_order_no=" + px_order_no + ", order_addressee=" + order_addressee + ", order_email="
                + order_email + ", order_telcellphone=" + order_telcellphone + ", order_city=" + order_city
                + ", order_zip=" + order_zip + ", order_address=" + order_address + ", pay_money_ssl=" + pay_money_ssl
                + ", order_date=" + order_date + ", order_remarks=" + order_remarks + ", recycle=" + recycle
                + ", collection_amount=" + collection_amount + "]";
    }
    
}
