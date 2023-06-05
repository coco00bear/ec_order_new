package com.rt.order.PxOrderAPI.Service;

import com.rt.order.PxOrderAPI.Model.CancelReq;
import com.rt.order.PxOrderAPI.Model.ReturnReq;
import com.rt.order.utility.model.ReturnMessage;

public interface PxService {

    public ReturnMessage cancel_px_status(CancelReq cancelReq);
    public ReturnMessage return_px_status(ReturnReq returnReq);

}
