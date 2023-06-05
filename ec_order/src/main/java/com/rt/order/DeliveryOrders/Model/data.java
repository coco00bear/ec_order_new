package com.rt.order.DeliveryOrders.Model;

import java.util.List;

public class data {
    
    private orders_data orders_data;
    private List<order_items_data> order_items_data;
    
    public orders_data getOrders_data() {
        return orders_data;
    }
    public void setOrders_data(orders_data orders_data) {
        this.orders_data = orders_data;
    }    
    public List<order_items_data> getOrder_items_data() {
        return order_items_data;
    }
    public void setOrder_items_data(List<order_items_data> order_items_data) {
        this.order_items_data = order_items_data;
    }
}
