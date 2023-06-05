package com.rt.order.DeliveryOrders.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rt.order.DeliveryOrders.Model.DeliveryCustInfo;
import com.rt.order.DeliveryOrders.Model.DeliveryOrderItems;
import com.rt.order.DeliveryOrders.Model.DeliveryOrders;
import com.rt.order.DeliveryOrders.Model.PxOrderInfo;
import com.rt.order.utility.model.ReturnMessage;

@Service
public interface PxOrdersService {

    // 整理成delivery_orders的資料型態
    public DeliveryOrders setDeliveryOrders(PxOrderInfo pxOrderInfo);

    // 整理成delivery_order_cust_info的資料型態
    public DeliveryCustInfo setDeliveryCustInfo(PxOrderInfo pxOrderInfo, Integer orderNo);
    
    // 整理成delivery_order_items的資料型態
    public List<DeliveryOrderItems> setDeliveryOrderItems(PxOrderInfo pxOrderInfo, Integer orderNo);

    // 將資料存入DB 同時確認是否有重複
    public ReturnMessage insertDeliveryOrders(String storeNo,
    DeliveryOrders deliveryOrders, List<DeliveryOrderItems> deliveryOrderItems, DeliveryCustInfo deliveryCustInfo);

    // TI流程
    public String ti_order_process(String storeNo, Integer orderNo);

}
