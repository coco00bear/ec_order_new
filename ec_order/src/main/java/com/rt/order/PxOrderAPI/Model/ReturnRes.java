package com.rt.order.PxOrderAPI.Model;

import com.rt.order.utility.model.ReturnMessage;

public class ReturnRes extends ReturnMessage{

    private RetrunResData data;//退貨明細相關資訊

    public ReturnRes(Integer statusCode, String message, RetrunResData data /*, Integer order_uid, Integer delivery_uid, Integer order_return_uid*/) {
        super(statusCode, message);    
        this.data = data;
    }

    public RetrunResData getData() {
        return data;
    }

    public void setData(RetrunResData data) {
        this.data = data;
    }   
}
