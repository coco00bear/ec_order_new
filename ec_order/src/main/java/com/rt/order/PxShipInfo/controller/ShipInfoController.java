package com.rt.order.PxShipInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rt.order.PxShipInfo.model.ForwardParam;
import com.rt.order.PxShipInfo.model.ReverseParam;
import com.rt.order.PxShipInfo.servcie.ShipInfoService;
import com.rt.order.utility.model.ReturnMessage;

@RestController
@RequestMapping("/ec_order/api/v1")
public class ShipInfoController {
    
    @Autowired
    ShipInfoService shipInfoService;

    /*
     * 貨態正向
     * 100 撿貨中 -> read_to_pick
     * 110/120 撿貨完成/斷貨 -> pick_finish
     * 130 已出貨 -> delivered
     * 140 已送達 -> arrived
     */
    @PostMapping("/ship_info_forward")
    public ResponseEntity<Object> get_shipInfo_forward(@RequestBody String forwardString){

        ForwardParam forwardParam;

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            forwardParam = objectMapper.readValue(forwardString,ForwardParam.class);
            System.out.println("forwardString: " + objectMapper.writeValueAsString(forwardParam));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReturnMessage(400,"格式有誤"));
        }

        ReturnMessage returnMessage = shipInfoService.get_shipinfo_forward(forwardParam);
        return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
    }

    /*
     * 貨態逆向
     * 210 退貨處理中 -> return_process
     * 220 退貨完成 -> return_finish
     * 221 退貨失敗 -> return_fail
     */
    @PostMapping("/ship_info_reverse")
    public ResponseEntity<Object> get_shipInfo_return(@RequestBody String returnString){

        ReverseParam reverseParam;

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            reverseParam = objectMapper.readValue(returnString,ReverseParam.class);
            System.out.println("returnString: " + objectMapper.writeValueAsString(reverseParam));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReturnMessage(400,"格式有誤"));
        }
        ReturnMessage returnMessage = shipInfoService.get_shipinfo_return(reverseParam);
        return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
    }

}
