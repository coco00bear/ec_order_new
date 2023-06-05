package com.rt.order.DeliveryOrders.Model;

import com.rt.order.utility.model.ReturnMessage;

public class ResponOrder extends ReturnMessage{

    // {
    //     "statusCode": "200",
    //     "message": "success",
    //     "data": {
    //       "assign_store": 10,
    //       "order_uid": 500580204
    //     }
    //   }

    private ResponOrderInfo data;

    public ResponOrderInfo getData() {
        return data;
    }

    public void setData(ResponOrderInfo data) {
        this.data = data;
    }

    public ResponOrder(Integer statusCode, String message, ResponOrderInfo data) {
        super(statusCode, message);
        this.data = data;
    }
}
