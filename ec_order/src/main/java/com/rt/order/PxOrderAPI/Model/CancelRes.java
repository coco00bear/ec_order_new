package com.rt.order.PxOrderAPI.Model;

import com.rt.order.utility.model.ReturnMessage;

public class CancelRes extends ReturnMessage{

    private CancelResInfo data;

    public CancelRes(Integer statusCode, String message, CancelResInfo cancelResInfo) {
        super(statusCode, message);
        this.data = cancelResInfo;
    }

    public CancelResInfo getData() {
        return data;
    }

    public void setData(CancelResInfo data) {
        this.data = data;
    }

    

}
