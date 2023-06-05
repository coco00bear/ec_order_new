package com.rt.order.DeliveryOrders.Dao;

import java.util.List;

import com.rt.order.DeliveryOrders.Model.DeliveryCustInfo;
import com.rt.order.DeliveryOrders.Model.DeliveryOrderItems;
import com.rt.order.DeliveryOrders.Model.DeliveryOrders;

public interface PxOrdersDao {

    //取得PX專用的orderSeq
    public Integer getOrderSeq(String storeNo);
    
    public Integer insertDeliveryOrders(String storeNo, DeliveryOrders deliveryOrders);

    public Integer insertDeliveryOrderItems(String storeNo, List<DeliveryOrderItems> deliveryOrderItemsList);

    public Integer insertDeliveryCustInfo(String storeNo, DeliveryCustInfo deliveryCustInfo);

    //查詢訂單號碼
    public Integer getOrdNo(String storeNo, String px_order_no);
    
    //check orders is ti
    public Integer chk_order_is_ti(String storeNo, Integer orderNo);

    public Integer upd_delivery_orders(String storeNo, Integer orderNo);

    //call px 100,110,120,130
    public Integer call_px_shipinfo(String storeNo, Integer orderNo, Integer status);

    //packing
    public boolean proc_packing(String storeNo, Integer orderNo);
}
