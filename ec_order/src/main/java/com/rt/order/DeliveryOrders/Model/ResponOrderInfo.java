package com.rt.order.DeliveryOrders.Model;

public class ResponOrderInfo {
    // {
    //     "statusCode": "200",
    //     "message": "success",
    //     "data": {
    //       "assign_store": 10,
    //       "order_uid": 500580204
    //     }
    //   }

    private Integer assign_store;
    private Integer order_uid;

    public Integer getAssign_store() {
        return assign_store;
    }
    public void setAssign_store(Integer assign_store) {
        this.assign_store = assign_store;
    }
    public Integer getOrder_uid() {
        return order_uid;
    }
    public void setOrder_uid(Integer order_uid) {
        this.order_uid = order_uid;
    }
}
