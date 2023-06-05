package com.rt.order.DrCustOrders.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rt.order.DrCustOrders.Model.DrCustFreebie;
import com.rt.order.DrCustOrders.Model.DrCustOrderItems;
import com.rt.order.DrCustOrders.Model.DrCustOrders;
import com.rt.order.DrCustOrders.Model.DrCustTender;
import com.rt.order.DrCustOrders.Model.OrderInfo;

@Service
public interface EcOrderService {

    // 整理成dr_cust_orders的資料型態
    public DrCustOrders setDrCustOrders(OrderInfo orderInfo);

    // 整理成dr_cust_order_items的資料型態
    public List<DrCustOrderItems> setDrCustOrderItems(OrderInfo orderInfo, Integer orderNo);

    // 整理成dr_cust_tender的資料型態
    public DrCustTender setDrCustTender(OrderInfo orderInfo);

    // 整理成dr_cust_freebie的資料型態
    public List<DrCustFreebie> setDrCustFreebie(OrderInfo orderInfo);

    // 將資料存入DB 同時確認是否有重複
    public Integer insertDrCustOrders(String storeNo,
            DrCustOrders dCustOrders, List<DrCustOrderItems> drCustOrderItemsList,
            DrCustTender drCustTender,List<DrCustFreebie> drCustFreebie);

    // 將訂單更新狀態ec
    public String ti_order_process(String storeNo, Integer orderNo);
}
