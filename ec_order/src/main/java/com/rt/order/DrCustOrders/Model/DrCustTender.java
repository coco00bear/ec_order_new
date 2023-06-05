package com.rt.order.DrCustOrders.Model;

public class DrCustTender {
    
    private Integer order_no;
    
    private Integer pk_type;

    private Integer tender_no;
    
    private Integer amount;

    public Integer getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }

    public Integer getPk_type() {
        return pk_type;
    }

    public void setPk_type(Integer pk_type) {
        this.pk_type = pk_type;
    }

    public Integer getTender_no() {
        return tender_no;
    }

    public void setTender_no(Integer tender_no) {
        this.tender_no = tender_no;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
