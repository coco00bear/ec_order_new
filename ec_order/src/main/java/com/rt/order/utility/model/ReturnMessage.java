package com.rt.order.utility.model;

import org.springframework.stereotype.Component;

@Component
public class ReturnMessage {
    private Integer statusCode;
    private String message;
    public ReturnMessage(){
    }
    public Integer getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "ReturnMessage [statusCode=" + statusCode + ", message=" + message + "]";
    }
    public ReturnMessage(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    

   
}
