package com.rt.order.PxOrderAPI.Model;


public class CancelData{

    private OrderCancelData order_cancel_data;
    private OrderRefundData order_refund_data;
    
    public OrderCancelData getOrder_cancel_data() {
        return order_cancel_data;
    }
    public void setOrder_cancel_data(OrderCancelData order_cancel_data) {
        this.order_cancel_data = order_cancel_data;
    }
    public OrderRefundData getOrder_refund_data() {
        return order_refund_data;
    }
    public void setOrder_refund_data(OrderRefundData order_refund_data) {
        this.order_refund_data = order_refund_data;
    }

}
