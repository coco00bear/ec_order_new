package com.rt.order.DrCustOrders.Model;

import java.util.List;

public class OrderInfo {

    private Orders orders;

    private List<OrderItems> items;

    private Tender tender;

    private List<Freebie> freebie;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<OrderItems> getItems() {
        return items;
    }

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public List<Freebie> getFreebie() {
        return freebie;
    }

    public void setFreebie(List<Freebie> freebie) {
        this.freebie = freebie;
    }

    
}
