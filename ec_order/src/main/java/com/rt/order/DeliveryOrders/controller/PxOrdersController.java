package com.rt.order.DeliveryOrders.controller;

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
import com.rt.order.DeliveryOrders.Model.DeliveryCustInfo;
import com.rt.order.DeliveryOrders.Model.DeliveryOrderItems;
import com.rt.order.DeliveryOrders.Model.DeliveryOrders;
import com.rt.order.DeliveryOrders.Model.PxOrderInfo;
import com.rt.order.DeliveryOrders.Service.PxOrdersService;
import com.rt.order.utility.model.ReturnMessage;

@RestController
@RequestMapping("/ec_order/api/v1")
public class PxOrdersController {
    
    Logger logger = LoggerFactory.getLogger("px");

    @Autowired
    PxOrdersService pxOrdersService;

    @PostMapping("/established_order")
    public ResponseEntity<Object> getPxOrder(@RequestBody String pxOrderString) {
        
        PxOrderInfo pxOrderInfo;
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            pxOrderInfo = objectMapper.readValue(pxOrderString,PxOrderInfo.class);
            
            logger.info("[PxOrdersController] - pxOrderInfo: "+ objectMapper.writeValueAsString(pxOrderInfo));

        }catch (Exception e){
            logger.info("getOrder mapper failed"+ e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReturnMessage(400,"訂單格式有誤"));
        }

        if(pxOrderInfo.getStore_no()==null){
            return ResponseEntity.status(HttpStatus.OK).body(new ReturnMessage(400,"未輸入店號"));
        }
        
        //整理成delivery_orders & delivery_order_items
        DeliveryOrders deliveryOrders = pxOrdersService.setDeliveryOrders(pxOrderInfo);
    
        DeliveryCustInfo deliveryCustInfo = pxOrdersService.setDeliveryCustInfo(pxOrderInfo, deliveryOrders.getOrderNo());

        List<DeliveryOrderItems> deliveryOrderItems = pxOrdersService.setDeliveryOrderItems(pxOrderInfo, deliveryOrders.getOrderNo());

        logger.info("DeliveryOrders: "+ deliveryOrders.toString());
        logger.info("deliveryCustInfo: "+ deliveryCustInfo.toString());
        logger.info("deliveryOrderItems: "+ deliveryOrderItems.toString());
        
        //若訂單明細為一個品項,進行確認是否是大家電
        if(pxOrderInfo.getData().getOrder_items_data().size()==1){
            Thread thread = new Thread("TI-CHECK") {
                public void run() {
                    try {
                        Thread.sleep(5000);
                        String response = pxOrdersService.ti_order_process(deliveryOrders.getStoreNo()+"", deliveryOrders.getOrderNo());
                        logger.info("run by: " + getName() + " response: " + response);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        logger.info("run by: " + getName() + " fail: " + e.getMessage());
                    }               
                }
            };
            thread.start();
            System.out.println(thread.getName());
        }
        return ResponseEntity.status(HttpStatus.OK).body(pxOrdersService.insertDeliveryOrders(pxOrderInfo.getStore_no()+"", deliveryOrders, deliveryOrderItems, deliveryCustInfo));
    }

    @PostMapping("/testpx")
    public ResponseEntity<Object> testec(String storeNo, Integer orderNo){
        
        pxOrdersService.ti_order_process(storeNo, orderNo);    
        return ResponseEntity.status(HttpStatus.OK).body(new ReturnMessage(200,"ok"));
    }
}
