package com.rt.order.DrCustOrders.Model;

public class EcResponMessage {
    // {
    //     "status": 1,
    //     "message": "",
    //     "debugmessage": ""

    /* "statusCode": "400",
  "message": "更新22貨態失敗,更新物流資訊有誤" */
    // }

    private Integer status;
    private String message;
    private String debugmessage;

    private String statusCode;
    
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDebugmessage() {
        return debugmessage;
    }
    public void setDebugmessage(String debugmessage) {
        this.debugmessage = debugmessage;
    }
}
