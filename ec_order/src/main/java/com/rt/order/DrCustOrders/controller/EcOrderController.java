package com.rt.order.DrCustOrders.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rt.order.DrCustOrders.Model.DrCustFreebie;
import com.rt.order.DrCustOrders.Model.DrCustOrderItems;
import com.rt.order.DrCustOrders.Model.DrCustOrders;
import com.rt.order.DrCustOrders.Model.DrCustTender;
import com.rt.order.DrCustOrders.Model.OrderInfo;
import com.rt.order.utility.model.ReturnMessage;
import com.rt.order.DrCustOrders.Service.EcOrderService;

@RestController
@RequestMapping("/ec_order/api/v1") 
public class EcOrderController {

    Logger loggerAll = LoggerFactory.getLogger("all");
    Logger logger = LoggerFactory.getLogger("all.order");

    @Autowired
    EcOrderService ecOrderService;
    
    @PostMapping("/order")
    public ResponseEntity<Object> getOrder(@RequestBody String orderString){
        //確認接收的資料
        OrderInfo orderInfo;
        String platform = "";
        try {           
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            orderInfo = objectMapper.readValue(orderString,OrderInfo.class);

            System.out.println("xxxx" + orderInfo.getItems().size());

            if(orderInfo.getOrders().getPk_type()==18){
                platform = "PX";
            }else platform = "EC";
            loggerAll.debug("=====================["+platform+"]============================");
            loggerAll.debug(orderString);

        }catch (Exception e){
            logger.info("getOrder mapper failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReturnMessage(400,"訂單格式有誤"));
        }

        //整理成dr_cust_orders & dr_cust_order_items & dr_cust_tender & dr_cust_freebie的資料型態
        DrCustOrders drCustOrders = ecOrderService.setDrCustOrders(orderInfo);
        List<DrCustOrderItems> drCustOrderItemsList = ecOrderService.setDrCustOrderItems(orderInfo, orderInfo.getOrders().getStore_no());
        DrCustTender drCustTender = ecOrderService.setDrCustTender(orderInfo);
        List<DrCustFreebie> drCustFreebie = ecOrderService.setDrCustFreebie(orderInfo);

        String storeNo = String.format("%02d",orderInfo.getOrders().getStore_no());

        Integer get_return_code = ecOrderService.insertDrCustOrders(storeNo, drCustOrders, drCustOrderItemsList,drCustTender,drCustFreebie);

        if (get_return_code==101){
            return ResponseEntity.status(HttpStatus.OK).body(new ReturnMessage(200,"訂單已存在,更新訂單完成"));
        }
        
        if(get_return_code==102){
            return ResponseEntity.status(HttpStatus.OK).body(new ReturnMessage(200,"歷史訂單有資料,排除異常"));
        }

        if(orderInfo.getItems().size()==1){
            Thread thread = new Thread("TI-CHECK") {
                public void run() {
                    try {
                        Thread.sleep(10000);
                        String response = ecOrderService.ti_order_process(storeNo, orderInfo.getOrders().getOrder_uid());
                        logger.info("run by: " + getName() + " response: " + response);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        logger.info("run by: " + getName() + " fail: " + e.getMessage());
                    }               
                }
            };
            thread.start();
            System.out.println(thread.getName());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(new ReturnMessage(200,"新增訂單完成"));
    }

    @PostMapping("/test")
    public ResponseEntity<Object> test(@RequestBody String orderString){
        //確認接收的資料
        OrderInfo orderInfo;
        try {
            loggerAll.debug("test =="+ orderString);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            orderInfo = objectMapper.readValue(orderString,OrderInfo.class);

        }catch (Exception e){
            logger.info("getOrder mapper failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReturnMessage(400,"訂單格式有誤"));
        }
        loggerAll.debug("test");
        return ResponseEntity.status(HttpStatus.OK).body(new ReturnMessage(200,"ok"));
    }    

    @PostMapping("/testec")
    public ResponseEntity<Object> testec(String storeNo, Integer orderNo){
        
        ecOrderService.ti_order_process(storeNo, orderNo);    
        return ResponseEntity.status(HttpStatus.OK).body(new ReturnMessage(200,"ok"));
    }
}
