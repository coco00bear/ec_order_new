package com.rt.order.PxShipInfo.servcie;

import com.rt.order.PxShipInfo.model.ForwardParam;
import com.rt.order.PxShipInfo.model.ReverseParam;
import com.rt.order.utility.model.ReturnMessage;

public interface ShipInfoService {
    
    //正向
    public ReturnMessage get_shipinfo_forward(ForwardParam forwardParam);

    //逆
    public ReturnMessage get_shipinfo_return(ReverseParam reverseParam);

}
