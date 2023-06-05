package com.rt.order.DrCustOrders.Model;

public class Orders {
    /*
    "orders": {
        "store_no": 10,
        "order_uid": 646042,
        "mem_uid": 778899,
        "mem_name": "王映琪",
        "phone": "0915788383",
        "phone1": "0",
        "mem_address": "新竹市東區金城一路50－6號",
        "order_gender": "M",
        "count": 13,
        "memo": "消費者備註:電梯(無)公司簽收.請幫我送到永慶不動產 謝謝",
        "pick": "20230130200000",
        "order_date": "20230130201555",
        "update": "20230201162915",
        "order_status": 1,
        "num": 70,
        "delivery_type": 1,
        "c": "90601882",
        "pay_way": 10,
        "credit_no": 0,
        "delivery_way": 1,
        "First6Num": "448709",
        "Last4Num": "0001",
        "invoice_electronic_device_code": "",
        "invoice_donatee": "0",
        "invoice_certificate": "",
        "mart_store_no": 39,
        "mart_client_no": 151920,
        "pk_type": 3
    }
 */

    private Integer store_no;
    private Integer order_uid;
    private Integer mem_uid;
    private String mem_name;
    private String phone;
    private String phone1;
    private String mem_address;
    private String order_gender;
    private Integer count;
    private String memo;
    private String pick;
    private String order_date;
    private String update;
    private Integer order_status;
    private Integer num;
    private Integer delivery_type;
    private String uniform;
    private Integer pay_way;
    private Integer credit_no;
    private Integer delivery_way;
    private String first6Num;
    private String last4Num;
    private String invoice_electronic_device_code;
    private String invoice_donatee;
    private String invoice_certificate;
    private Integer mart_store_no;
    private Integer mart_client_no;
    private Integer pk_type;
    private String px_order_id;
    
    public String getPx_order_id() {
        return px_order_id;
    }
    public void setPx_order_id(String px_order_id) {
        this.px_order_id = px_order_id;
    }
    public Integer getStore_no() {
        return store_no;
    }
    public void setStore_no(Integer store_no) {
        this.store_no = store_no;
    }
    public Integer getOrder_uid() {
        return order_uid;
    }
    public void setOrder_uid(Integer order_uid) {
        this.order_uid = order_uid;
    }
    public Integer getMem_uid() {
        return mem_uid;
    }
    public void setMem_uid(Integer mem_uid) {
        this.mem_uid = mem_uid;
    }
    public String getMem_name() {
        return mem_name;
    }
    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone1() {
        return phone1;
    }
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }
    public String getMem_address() {
        return mem_address;
    }
    public void setMem_address(String mem_address) {
        this.mem_address = mem_address;
    }
    public String getOrder_gender() {
        return order_gender;
    }
    public void setOrder_gender(String order_gender) {
        this.order_gender = order_gender;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getPick() {
        return pick;
    }
    public void setPick(String pick) {
        this.pick = pick;
    }
    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    public String getUpdate() {
        return update;
    }
    public void setUpdate(String update) {
        this.update = update;
    }
    public Integer getOrder_status() {
        return order_status;
    }
    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Integer getDelivery_type() {
        return delivery_type;
    }
    public void setDelivery_type(Integer delivery_type) {
        this.delivery_type = delivery_type;
    }
    public String getUniform() {
        return uniform;
    }
    public void setUniform(String uniform) {
        this.uniform = uniform;
    }
    public Integer getPay_way() {
        return pay_way;
    }
    public void setPay_way(Integer pay_way) {
        this.pay_way = pay_way;
    }
    public Integer getCredit_no() {
        return credit_no;
    }
    public void setCredit_no(Integer credit_no) {
        this.credit_no = credit_no;
    }
    public Integer getDelivery_way() {
        return delivery_way;
    }
    public void setDelivery_way(Integer delivery_way) {
        this.delivery_way = delivery_way;
    }
    public String getFirst6Num() {
        return first6Num;
    }
    public void setFirst6Num(String first6Num) {
        this.first6Num = first6Num;
    }
    public String getLast4Num() {
        return last4Num;
    }
    public void setLast4Num(String last4Num) {
        this.last4Num = last4Num;
    }
    public String getInvoice_electronic_device_code() {
        return invoice_electronic_device_code;
    }
    public void setInvoice_electronic_device_code(String invoice_electronic_device_code) {
        this.invoice_electronic_device_code = invoice_electronic_device_code;
    }
    public String getInvoice_donatee() {
        return invoice_donatee;
    }
    public void setInvoice_donatee(String invoice_donatee) {
        this.invoice_donatee = invoice_donatee;
    }
    public String getInvoice_certificate() {
        return invoice_certificate;
    }
    public void setInvoice_certificate(String invoice_certificate) {
        this.invoice_certificate = invoice_certificate;
    }
    public Integer getMart_store_no() {
        return mart_store_no;
    }
    public void setMart_store_no(Integer mart_store_no) {
        this.mart_store_no = mart_store_no;
    }
    public Integer getMart_client_no() {
        return mart_client_no;
    }
    public void setMart_client_no(Integer mart_client_no) {
        this.mart_client_no = mart_client_no;
    }
    public Integer getPk_type() {
        return pk_type;
    }
    public void setPk_type(Integer pk_type) {
        this.pk_type = pk_type;
    }



}
