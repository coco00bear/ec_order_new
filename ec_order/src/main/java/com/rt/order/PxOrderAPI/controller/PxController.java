package com.rt.order.PxOrderAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rt.order.PxOrderAPI.Model.CancelReq;
import com.rt.order.PxOrderAPI.Model.ReturnReq;
import com.rt.order.PxOrderAPI.Service.PxServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/ec_order/api/v1")
public class PxController {

    @Autowired
    private PxServiceImpl pxService;
    Logger logger = LoggerFactory.getLogger("controller");

    @GetMapping("/test")//測試連線
    public ResponseEntity<Object> test(@PathParam("test") String test) {
        System.out.println("????");
        System.out.println(test);
        System.out.println("????");
        return ResponseEntity.status(HttpStatus.OK).body(test);
    }

    @PostMapping("/cancel_order")//取消訂單
    public ResponseEntity<?> cancel_px_status(@RequestBody CancelReq cancelReq) {
        // 確認接收的資料
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("xxx cancel_order " + mapper.writeValueAsString(cancelReq));
            return ResponseEntity.status(HttpStatus.OK).body(pxService.cancel_px_status(cancelReq));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400,Bad Request");
        }
       // return ResponseEntity.status(HttpStatus.OK).body(pxService.cancel_px_status(cancelReq));
    }

    @PostMapping("/return_order")//退貨
    public ResponseEntity<?> return_px_status(@RequestBody ReturnReq returnReq) {
        // 確認接收的資料
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("xxx return_order " + mapper.writeValueAsString(returnReq));
            return ResponseEntity.status(HttpStatus.OK).body(pxService.return_px_status(returnReq));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400,Bad Request");
        }
    }

}
