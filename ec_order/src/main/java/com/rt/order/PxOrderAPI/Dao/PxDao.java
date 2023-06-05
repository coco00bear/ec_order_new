package com.rt.order.PxOrderAPI.Dao;

import com.rt.order.PxOrderAPI.Model.CancelReq;
import com.rt.order.PxOrderAPI.Model.OrderReturnDfData;
import com.rt.order.PxOrderAPI.Model.ReturnReq;

import java.util.List;

public interface PxDao {
    public Integer check_status(CancelReq cancelReq);

    public Integer update_px_status(CancelReq cancelReq);

    public Integer insert_cancel_refund_data(CancelReq cancelReq);

    public Integer insert_candel_order_return(CancelReq cancelReq);

    public Integer insert_return_order_return(ReturnReq returnReq);

    public Integer insert_return_items_all(ReturnReq returnReq);

    public Integer insert_return_items(ReturnReq returnReq);

    public List<OrderReturnDfData> getReturnResData(ReturnReq returnReq);

    public Integer check_return_items(ReturnReq returnReq);
}
