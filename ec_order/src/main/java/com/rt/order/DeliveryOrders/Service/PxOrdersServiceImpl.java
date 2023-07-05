package com.rt.order.DeliveryOrders.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rt.order.DeliveryOrders.Dao.PxOrdersDao;
import com.rt.order.DeliveryOrders.Model.DeliveryCustInfo;
import com.rt.order.DeliveryOrders.Model.DeliveryOrderItems;
import com.rt.order.DeliveryOrders.Model.DeliveryOrders;
import com.rt.order.DeliveryOrders.Model.PxOrderInfo;
import com.rt.order.DeliveryOrders.Model.ResponOrder;
import com.rt.order.DeliveryOrders.Model.ResponOrderInfo;
import com.rt.order.utility.model.ReturnMessage;

@Service
public class PxOrdersServiceImpl implements PxOrdersService {

    @Autowired
    PxOrdersDao pxOrdersDao;

    @Value("${px.platform.no}")
    private Integer platformNo;

    Logger logger = LoggerFactory.getLogger("px");

    @Override
    public DeliveryOrders setDeliveryOrders(PxOrderInfo pxOrderInfo) {
        DeliveryOrders deliveryOrders = new DeliveryOrders();

        Integer storeNo = pxOrderInfo.getStore_no();

        // 取得訂單流水號
        Integer orderNo = pxOrdersDao.getOrderSeq(String.format("%02d", storeNo));

        // 訂單主檔
        deliveryOrders.setPlatformNo(platformNo);
        deliveryOrders.setOrderNo(orderNo);
        deliveryOrders.setIdNo(pxOrderInfo.getData().getOrders_data().getPx_order_no());
        deliveryOrders.setDisplayNo(pxOrderInfo.getData().getOrders_data().getPx_order_no().substring(11, 16));
        deliveryOrders.setStatus(2);// 訂單已建立
        deliveryOrders.setEater(pxOrderInfo.getData().getOrders_data().getOrder_addressee());
        deliveryOrders.setMemo(pxOrderInfo.getData().getOrders_data().getOrder_remarks());
        deliveryOrders.setTotal(pxOrderInfo.getData().getOrders_data().getPay_money_ssl());
        deliveryOrders.setCreateTime(pxOrderInfo.getData().getOrders_data().getOrder_date().replace("T", ""));
        deliveryOrders.setType(1); // 訂單類型 1.廠商送貨 2.RT送貨 3.內用 4.店取
        deliveryOrders.setStoreNo(storeNo);
        deliveryOrders.setDeliveryCarriage(pxOrderInfo.getLogistic_carriage());

        return deliveryOrders;
    }

    @Override
    public DeliveryCustInfo setDeliveryCustInfo(PxOrderInfo pxOrderInfo, Integer orderNo) {
        DeliveryCustInfo deliveryCustInfo = new DeliveryCustInfo();
        // 訂單客人資訊
        deliveryCustInfo.setPlatformNo(platformNo);
        deliveryCustInfo.setOrderNo(orderNo);
        deliveryCustInfo.setName(pxOrderInfo.getData().getOrders_data().getOrder_addressee());
        deliveryCustInfo.setPhoneNo(pxOrderInfo.getData().getOrders_data().getOrder_telcellphone());
        deliveryCustInfo.setMail(pxOrderInfo.getData().getOrders_data().getOrder_email());
        deliveryCustInfo.setCity(pxOrderInfo.getData().getOrders_data().getOrder_city());
        deliveryCustInfo.setZip(Integer.parseInt(pxOrderInfo.getData().getOrders_data().getOrder_zip()));
        deliveryCustInfo.setAddress(pxOrderInfo.getData().getOrders_data().getOrder_address());
        deliveryCustInfo.setIs_pay(1); // 是否付款 1:是
        deliveryCustInfo.setPay_money(pxOrderInfo.getData().getOrders_data().getPay_money_ssl());
        deliveryCustInfo.setInvoice_get_type(2); // 發票開立方式 2:廠商自行開立發票
        deliveryCustInfo.setInvoice_status(0); // 發票開立狀態 0:未開立

        return deliveryCustInfo;
    }

    @Override
    public List<DeliveryOrderItems> setDeliveryOrderItems(PxOrderInfo pxOrderInfo, Integer orderNo) {
        List<DeliveryOrderItems> deliveryOrderItemsList = new ArrayList<>();
        DeliveryOrderItems deliveryOrderItems;
        int itemSeq = 1;
        for (int i = 0; i < pxOrderInfo.getData().getOrder_items_data().size(); i++) {
            deliveryOrderItems = new DeliveryOrderItems();
            deliveryOrderItems.setPlatformNo(platformNo);
            deliveryOrderItems.setOrderNo(orderNo);
            deliveryOrderItems.setItemSeq(itemSeq);
            itemSeq++;
            deliveryOrderItems.setItemNo(pxOrderInfo.getData().getOrder_items_data().get(i).getItem_no());
            deliveryOrderItems.setName(pxOrderInfo.getData().getOrder_items_data().get(i).getItem_name());
            deliveryOrderItems.setStatus(0);
            deliveryOrderItems.setType(0);
            deliveryOrderItems.setQty(pxOrderInfo.getData().getOrder_items_data().get(i).getQty());
            deliveryOrderItems.setUnitPrice(pxOrderInfo.getData().getOrder_items_data().get(i).getSell_price());// 單價
            deliveryOrderItems.setTotalPrice(pxOrderInfo.getData().getOrder_items_data().get(i).getSell_price()
                    * pxOrderInfo.getData().getOrder_items_data().get(i).getQty());
            deliveryOrderItems.setOriginalQty(pxOrderInfo.getData().getOrder_items_data().get(i).getQty());
            deliveryOrderItems.setSalesAmount(pxOrderInfo.getData().getOrder_items_data().get(i).getSell_price());// 單價
            deliveryOrderItems.setTotalAmount(pxOrderInfo.getData().getOrder_items_data().get(i).getSell_price()
                    * pxOrderInfo.getData().getOrder_items_data().get(i).getQty());

            deliveryOrderItemsList.add(deliveryOrderItems);
        }
        return deliveryOrderItemsList;
    }

    @Override
    public ReturnMessage insertDeliveryOrders(String storeNo, DeliveryOrders deliveryOrders,
            List<DeliveryOrderItems> deliveryOrderItems, DeliveryCustInfo deliveryCustInfo) {        
        ResponOrderInfo responOrderInfo = new ResponOrderInfo(); 
        Integer get_store_no = Integer.parseInt(storeNo);
        if (pxOrdersDao.insertDeliveryOrders(String.format("%02d", get_store_no), deliveryOrders) == 1) {
            pxOrdersDao.insertDeliveryOrderItems(String.format("%02d", get_store_no), deliveryOrderItems);
            pxOrdersDao.insertDeliveryCustInfo(String.format("%02d", get_store_no), deliveryCustInfo);
            //訂單成立
            responOrderInfo.setAssign_store(Integer.parseInt(storeNo));
            responOrderInfo.setOrder_uid(deliveryOrders.getOrderNo());       
            return new ResponOrder(200, "success", responOrderInfo);
        }else {
            //取得訂單號碼
            Integer get_order_no = pxOrdersDao.getOrdNo(String.format("%02d", get_store_no), deliveryOrders.getIdNo());            
            responOrderInfo.setOrder_uid(get_order_no);
            responOrderInfo.setAssign_store(Integer.parseInt(storeNo));
                        
            return new ResponOrder(402, "訂單重複拋送", responOrderInfo);
        }
    }

    @Override
    public String ti_order_process(String storeNo, Integer orderNo) {

        /*
         * 1.chk is ti ?
         * 2.update order rts status
         * 3.call px 100
         * 4.update order rts status
         * 5.call px 110,120
         * 6.call packing
         * 7.call px 115
         */
        Integer get_store_no = Integer.parseInt(storeNo);

        // chk_order_is_ti
        Integer chk_order_is_ti = pxOrdersDao.chk_order_is_ti(String.format("%02d", get_store_no), orderNo);
        logger.info("1.chk_order_is_ti: " + chk_order_is_ti);
        if (chk_order_is_ti == 1) {
            logger.info("[檢查是TI商品] == store_no: " + String.format("%02d", get_store_no) + " ord: " + orderNo);
            sleep(3000);
            // update delivery status            
            Integer upd_delivery_orders = pxOrdersDao.upd_delivery_orders(storeNo, orderNo, 6);
            logger.info("2.upd_delivery_orders=6: " + upd_delivery_orders);
            if (upd_delivery_orders == 1) {
                logger.info("[更新訂單狀態為6成功] == store_no: " + storeNo + " ord: " + orderNo);
                sleep(5000);
                // call px shipinfo to 100
                Integer pxShipRes = pxOrdersDao.call_px_shipinfo(get_store_no, orderNo, "read_to_pick");
                logger.info("3.call_px_shipinfo=100: " + pxShipRes);
                if (pxShipRes == 200) {
                    logger.info("[更新PX貨態100成功] == store_no: " + storeNo + " ord: " + orderNo);
                    sleep(5000);
                    Integer upd_delivery_orders_5 = pxOrdersDao.upd_delivery_orders(storeNo, orderNo, 5);
                    logger.info("4.upd_delivery_orders_5=5: " + upd_delivery_orders_5);
                    if (upd_delivery_orders_5 == 1) {
                        logger.info("[更新訂單狀態為5成功] == store_no: " + storeNo + " ord: " + orderNo);
                        sleep(5000);
                        // call px shipinfo to 100
                        Integer pick_finish = pxOrdersDao.call_px_shipinfo(get_store_no, orderNo, "pick_finish");
                        logger.info("5.call_px_shipinfo=110: " + pick_finish);
                        if (pick_finish == 200) {
                            logger.info("[更新PX貨態110成功] == store_no: " + storeNo + " ord: " + orderNo);
                            sleep(5000);
                            // call rts to packing
                            Boolean result = pxOrdersDao.proc_packing(storeNo, orderNo);
                            logger.info("6.proc_packing: " + result);
                            if (result == true) {
                                logger.info("[結帳成功] == store_no: " + storeNo + " ord: " + orderNo);
                                sleep(5000);
                                // call px shipinfo to 100
                                Integer call_user = pxOrdersDao.call_px_shipinfo(get_store_no, orderNo, "call_user");
                                logger.info("7.call_px_shipinfo=115: " + call_user);
                                if (call_user == 200) {
                                    logger.info("[更新PX貨態115成功] == store_no: " + storeNo + " ord: " + orderNo);
                                } else
                                    logger.info("[更新PX貨態115失敗] == store_no: " + storeNo + " ord: " + orderNo);
                            } else
                                logger.info("[結帳失敗] == store_no: " + storeNo + " ord: " + orderNo);
                        } else
                            logger.info("[更新PX貨態110失敗] == store_no: " + storeNo + " ord: " + orderNo);

                    } else {
                        logger.info("[更新訂單狀態為5失敗] == store_no: " + storeNo + " ord: " + orderNo);
                    }

                } else {
                    logger.info("[更新PX貨態100失敗] == store_no: " + storeNo + " ord: " + orderNo);
                }
            } else {
                logger.info("[更新訂單狀態為6失敗] == store_no: " + storeNo + " ord: " + orderNo);
            }

        } else {
            logger.info("[檢查不是TI商品] == store_no: " + String.format("%02d", get_store_no) + " ord: " + orderNo);
        }

        return "1";
    }

    public void sleep(Integer millis){
        try {
            Thread.sleep(millis);
            logger.info("sleep: " + millis);
        } catch (InterruptedException e) {            
            e.printStackTrace();
            logger.info("sleep: " + e.getMessage());
        }
    }
}
