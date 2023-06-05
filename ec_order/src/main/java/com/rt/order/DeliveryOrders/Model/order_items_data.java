package com.rt.order.DeliveryOrders.Model;

public class order_items_data {
    
    private Integer item_no;
    private String item_name;
    private Integer sell_price;
    private Integer qty;

    public Integer getItem_no() {
        return item_no;
    }
    public void setItem_no(Integer item_no) {
        this.item_no = item_no;
    }
    public String getItem_name() {
        return item_name;
    }
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    public Integer getSell_price() {
        return sell_price;
    }
    public void setSell_price(Integer sell_price) {
        this.sell_price = sell_price;
    }
    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
