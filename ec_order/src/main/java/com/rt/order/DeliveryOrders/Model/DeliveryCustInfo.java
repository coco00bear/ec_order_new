package com.rt.order.DeliveryOrders.Model;

public class DeliveryCustInfo {

     private Integer platformNo ;
     private Integer orderNo;
     private String name; //px
     private String phoneNo; //px
     private String mail; //px
     private String city; //px
     private Integer zip; //px
     private String address; //px
     private Integer is_pay; 
     private Integer pay_money; //px
     private Integer invoice_get_type;
     private Integer invoice_status;
     
    public Integer getPlatformNo() {
        return platformNo;
    }
    public void setPlatformNo(Integer platformNo) {
        this.platformNo = platformNo;
    }
    public Integer getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Integer getZip() {
        return zip;
    }
    public void setZip(Integer zip) {
        this.zip = zip;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getIs_pay() {
        return is_pay;
    }
    public void setIs_pay(Integer is_pay) {
        this.is_pay = is_pay;
    }
    public Integer getPay_money() {
        return pay_money;
    }
    public void setPay_money(Integer pay_money) {
        this.pay_money = pay_money;
    }
    public Integer getInvoice_get_type() {
        return invoice_get_type;
    }
    public void setInvoice_get_type(Integer invoice_get_type) {
        this.invoice_get_type = invoice_get_type;
    }
    public Integer getInvoice_status() {
        return invoice_status;
    }
    public void setInvoice_status(Integer invoice_status) {
        this.invoice_status = invoice_status;
    }
    @Override
    public String toString() {
        return "DeliveryCustInfo [platformNo=" + platformNo + ", orderNo=" + orderNo + ", name=" + name + ", phoneNo="
                + phoneNo + ", mail=" + mail + ", city=" + city + ", zip=" + zip + ", address=" + address + ", is_pay="
                + is_pay + ", pay_money=" + pay_money + ", invoice_get_type=" + invoice_get_type + ", invoice_status="
                + invoice_status + "]";
    }
    
}
