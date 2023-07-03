package com.rt.order.DrCustOrders.Dao;

import java.util.List;

import com.rt.order.DrCustOrders.Model.DrCustFreebie;
import com.rt.order.DrCustOrders.Model.DrCustOrderItems;
import com.rt.order.DrCustOrders.Model.DrCustOrders;
import com.rt.order.DrCustOrders.Model.DrCustTender;

public interface EcOrderDao {
    //chk_cust_orders()
    public Integer chk_cust_orders(String storeNo, Integer orderNo);

    //存入訂單主檔
    public Integer insertDrCustOrders(String storeNo, DrCustOrders drCustOrders);

    //存入訂單明細
    //public Integer insertDrCustOrderItems(String storeNo, List<DrCustOrderItems> drCustOrderItemsList);

    public Integer insertDrCustOrderItems(String storeNo, DrCustOrderItems drCustOrderItemsList);

    //存入訂單抵用卷
    public Integer insertDrCustTender(String storeNo, DrCustTender drCustTender);

    //存入訂單贈品
    public Integer insertDrCustFreebie(String storeNo, List<DrCustFreebie> drCustFreebie);

    //更新訂單資訊
    public Integer updateDrCustOrders(String storeNo, DrCustOrders drCustOrders);

    //排除異常訂單
    public Integer deleteDrCustOrdersHistory(String storeNo, DrCustOrders drCustOrders);

    //DAILY_LOG
    public Integer insertDailyLog(String storeNo, Integer orderNo);

    //檢查訂單明細是否有資料
    public Integer chk_cust_items(String storeNo, Integer orderNo, Integer line_no);

    //call ec update order status
    public Integer api_SetOrderStatus19(Integer OrderUid);

    //call ec update order finish
    public Integer api_box_v2(String storeNo, Integer OrderUid);

    //orders packing
    public Integer print_invoice(String storeNo, Integer orderNo);

    //check orders is ti
    public Integer chk_order_is_ti(String storeNo, Integer orderNo);

    //update dr_cust_orders status & dr_cust_order_items status,type
    public Integer upd_dr_cust_orders(String storeNo, Integer orderNo);

    //call ec api update px cargo
    public Integer update_cargo_status(Integer storeNo, Integer orderNo);

    //call ec api to 21
    public Integer SetOrderStatus21(Integer OrderUid);

}
