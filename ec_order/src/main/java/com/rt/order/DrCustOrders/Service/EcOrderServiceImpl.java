package com.rt.order.DrCustOrders.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.order.DrCustOrders.Dao.EcOrderDao;
import com.rt.order.DrCustOrders.Model.DrCustFreebie;
import com.rt.order.DrCustOrders.Model.DrCustOrderItems;
import com.rt.order.DrCustOrders.Model.DrCustOrders;
import com.rt.order.DrCustOrders.Model.DrCustTender;
import com.rt.order.DrCustOrders.Model.OrderInfo;

@Service
public class EcOrderServiceImpl implements EcOrderService {

    Logger loggerAll = LoggerFactory.getLogger("all");
    Logger logger = LoggerFactory.getLogger("all.order");

    @Autowired
    EcOrderDao ecOrderDao;

    @Override
    public DrCustOrders setDrCustOrders(OrderInfo orderInfo) {
        DrCustOrders drCustOrders = new DrCustOrders();

        Integer storeNo = orderInfo.getOrders().getStore_no();
        Integer orderNo = orderInfo.getOrders().getOrder_uid();

        drCustOrders.setStore_no(storeNo);
        drCustOrders.setOrder_no(orderNo);
        drCustOrders.setRoad_no(99);
        drCustOrders.setClient_no(orderInfo.getOrders().getMem_uid());
        drCustOrders.setUniform(orderInfo.getOrders().getUniform());
        drCustOrders.setOrd_date(orderInfo.getOrders().getOrder_date());
        drCustOrders.setNbr_items(orderInfo.getOrders().getCount());
        drCustOrders.setPrint(0);
        drCustOrders.setStatus(orderInfo.getOrders().getOrder_status());
        drCustOrders.setName(orderInfo.getOrders().getMem_name());
        drCustOrders.setPhone_no(orderInfo.getOrders().getPhone());
        drCustOrders.setExt_no(orderInfo.getOrders().getPhone1());
        drCustOrders.setAddress(orderInfo.getOrders().getMem_address());
        drCustOrders.setSex(orderInfo.getOrders().getOrder_gender());
        drCustOrders.setMemo(orderInfo.getOrders().getMemo());
        drCustOrders.setCredit(orderInfo.getOrders().getNum()); // 會員總訂單數
        drCustOrders.setCarry_time(orderInfo.getOrders().getPick());
        drCustOrders.setCreated_date(orderInfo.getOrders().getOrder_date());
        drCustOrders.setChanged_date(orderInfo.getOrders().getUpdate());
        drCustOrders.setDelivery(orderInfo.getOrders().getDelivery_type());
        drCustOrders.setTender_no(orderInfo.getOrders().getPay_way());
        drCustOrders.setCreadit_no(orderInfo.getOrders().getCredit_no()); // 信用卡卡號
        drCustOrders.setPayment_methods(orderInfo.getOrders().getDelivery_way());
        drCustOrders.setMart_store_no(orderInfo.getOrders().getMart_store_no());
        drCustOrders.setMart_client_no(orderInfo.getOrders().getMart_client_no());
        if (orderInfo.getOrders().getFirst6Num().trim().equals("")) {
            drCustOrders.setCredit_card_no("");
        } else {
            drCustOrders.setCredit_card_no(
                    orderInfo.getOrders().getFirst6Num() + "00****" + orderInfo.getOrders().getLast4Num());
        }
        drCustOrders.setInv_code(orderInfo.getOrders().getInvoice_electronic_device_code());
        drCustOrders.setInv_love(orderInfo.getOrders().getInvoice_donatee());
        drCustOrders.setInv_cert(orderInfo.getOrders().getInvoice_certificate());
        drCustOrders.setPk_type(orderInfo.getOrders().getPk_type());
        drCustOrders.setPx_order_no(orderInfo.getOrders().getPx_order_id());

        //20230713 ti project add two column
        drCustOrders.setRecycle(orderInfo.getOrders().getRecycle());
        drCustOrders.setCollection_amount(orderInfo.getOrders().getCollection_amount());

        return drCustOrders;
    }

    @Override
    public List<DrCustOrderItems> setDrCustOrderItems(OrderInfo orderInfo, Integer storeNo) {
        List<DrCustOrderItems> drCustOrderItemsList = new ArrayList<>();
        DrCustOrderItems drCustOrderItems;
        for (int i = 0; i < orderInfo.getItems().size(); i++) {

            drCustOrderItems = new DrCustOrderItems();
            drCustOrderItems.setRoad_no(99);
            drCustOrderItems.setOrder_no(orderInfo.getItems().get(i).getOrder_uid());
            drCustOrderItems.setLine_no(orderInfo.getItems().get(i).getLine_no());
            drCustOrderItems.setItem_no(orderInfo.getItems().get(i).getItem_no());
            drCustOrderItems.setPromotion_no(orderInfo.getItems().get(i).getPromotion_no());
            drCustOrderItems.setProm_level(orderInfo.getItems().get(i).getProm_level());
            drCustOrderItems.setQty(orderInfo.getItems().get(i).getQty());
            drCustOrderItems.setOriginal_qty(orderInfo.getItems().get(i).getQty());
            drCustOrderItems.setAmount(orderInfo.getItems().get(i).getAmount());
            drCustOrderItems.setDesc_amnt(0);
            drCustOrderItems.setStatus(0);
            drCustOrderItems.setAction_type(orderInfo.getItems().get(i).getAction_type());
            drCustOrderItems.setAction_no(orderInfo.getItems().get(i).getAction_no());
            drCustOrderItems.setModule_code(orderInfo.getItems().get(i).getModule_code());
            drCustOrderItems.setModule_rule_type(orderInfo.getItems().get(i).getModule_rule_type());
            drCustOrderItems.setModule_rule_value(orderInfo.getItems().get(i).getModule_rule_value());
            drCustOrderItems.setDisc_action_type(orderInfo.getItems().get(i).getDisc_action_type());
            drCustOrderItems.setDisc_action_no(orderInfo.getItems().get(i).getDisc_action_no());
            drCustOrderItems.setDisc_rule_type(orderInfo.getItems().get(i).getDisc_rule_type());
            drCustOrderItems.setDisc_rule_value(orderInfo.getItems().get(i).getDisc_rule_value());
            drCustOrderItems.setConst_cnt(orderInfo.getItems().get(i).getDisc_cnt());
            drCustOrderItems.setConst_amount(orderInfo.getItems().get(i).getDisc_amnt());
            drCustOrderItems.setType(0);
            drCustOrderItems.setBonus_point(orderInfo.getItems().get(i).getBonus_point());
            drCustOrderItems.setAvg_price(orderInfo.getItems().get(i).getAvg_price());
            drCustOrderItems.setItem_memo(orderInfo.getItems().get(i).getProd_memo());

            drCustOrderItems.setItem_uid(orderInfo.getItems().get(i).getOrder_df_uid());

            drCustOrderItemsList.add(drCustOrderItems);
        }
        return drCustOrderItemsList;
    }

    @Override
    public DrCustTender setDrCustTender(OrderInfo orderInfo) {

        DrCustTender drCustTender = new DrCustTender();
        drCustTender.setOrder_no(orderInfo.getTender().getOrder_no());
        drCustTender.setPk_type(orderInfo.getTender().getPk_type());
        drCustTender.setAmount(orderInfo.getTender().getAmount());
        drCustTender.setTender_no(orderInfo.getTender().getTender_no());

        return drCustTender;
    }

    @Override
    public List<DrCustFreebie> setDrCustFreebie(OrderInfo orderInfo) {
        List<DrCustFreebie> drCustFreebieList = new ArrayList<>();
        DrCustFreebie drCustFreebie;

        for (int i = 0; i < orderInfo.getFreebie().size(); i++) {
            drCustFreebie = new DrCustFreebie();
            drCustFreebie.setOrder_no(orderInfo.getFreebie().get(i).getOrder_no());
            drCustFreebie.setPk_type(orderInfo.getFreebie().get(i).getPk_type());
            drCustFreebie.setWmkt_uid(orderInfo.getFreebie().get(i).getWmkt_uid());
            drCustFreebie.setMemo(orderInfo.getFreebie().get(i).getMemo());
            drCustFreebie.setQty(orderInfo.getFreebie().get(i).getQty());

            drCustFreebieList.add(drCustFreebie);
        }
        return drCustFreebieList;
    }

    @Override
    public Integer insertDrCustOrders(String storeNo, DrCustOrders drCustOrders,
            List<DrCustOrderItems> drCustOrderItemsList, DrCustTender drCustTender, List<DrCustFreebie> drCustFreebie) {

        // 檢查dr_cust_orders是否有訂單資料
        Integer status = ecOrderDao.chk_cust_orders(storeNo, drCustOrders.getOrder_no());

        if (status == -1) { // dr_cust_orders無訂單資料
            logger.info(
                    "[檢查dr_cust_orders是否有訂單資料] == [無] store_no: " + storeNo + " ord_no: " + drCustOrders.getOrder_no());

            // 塞訂單主檔資料至dr_cust_orders
            Integer get_response = ecOrderDao.insertDrCustOrders(storeNo, drCustOrders);

            if (get_response == 0) {
                // 歷史訂單有資料
                logger.info("[歷史訂單有資料] == store_no: " + storeNo + " ord_no: " + drCustOrders.getOrder_no());

                if (drCustOrders.getStatus() != 6) {
                    logger.info("[歷史訂單有資料,排除異常] == store_no: " + storeNo + " ord_no: " + drCustOrders.getOrder_no());
                    ecOrderDao.deleteDrCustOrdersHistory(storeNo, drCustOrders);
                }
                logger.info("====================================================" + "\n");
                return 102; // 歷史訂單有資料,排除異常

            } else {
                // 歷史訂單無資料
                logger.info("[歷史訂單無資料-新增訂單] == store_no: " + storeNo + " ord_no: " + drCustOrders.getOrder_no()
                        + " pk_type: " + drCustOrders.getPk_type());
                // 塞訂單商品明細資料至dr_cust_order_items
                for (int i = 0; i < drCustOrderItemsList.size(); i++) {
                    ecOrderDao.insertDrCustOrderItems(storeNo, drCustOrderItemsList.get(i));
                }

                // 塞訂單抵用卷資訊至dr_cust_tender
                if (drCustTender.getOrder_no() != null) {
                    ecOrderDao.insertDrCustTender(storeNo, drCustTender);
                }

                // 塞訂單贈品資訊至dr_cust_freebie
                if (drCustFreebie.get(0).getOrder_no() != null) {
                    ecOrderDao.insertDrCustFreebie(storeNo, drCustFreebie);
                }
                logger.info("====================================================" + "\n");

                logger.info("=================塞訂單完成===========================" + "\n");
                return 200; // 塞訂單完成
            }

        } else { // dr_cust_orders有訂單資料
            logger.info(
                    "[檢查dr_cust_orders是否有訂單資料] == [有] store_no: " + storeNo + " ord_no: " + drCustOrders.getOrder_no());

            /*
             * status 系統內的訂單狀態
             * drCustOrders.getStatus() json格式傳來的訂單狀態
             * 1.rts status -> 0 or 6
             * 2.rts status -> 2 and json -> 6
             * 3.rts status -> (5 or 9) and json -> (6 or 7) never
             */

            // 塞訂單商品明細資料至dr_cust_order_items
            for (int i = 0; i < drCustOrderItemsList.size(); i++) {
                Integer get_count = ecOrderDao.chk_cust_items(storeNo, drCustOrderItemsList.get(i).getOrder_no(),
                        drCustOrderItemsList.get(i).getLine_no());
                if (get_count == 1) {
                    logger.info("[檢查dr_cust_order_items已存在] == store_no: " + storeNo + " ord_no: "
                            + drCustOrderItemsList.get(i).getOrder_no() +
                            " itemNo: " + drCustOrderItemsList.get(i).getItem_no() + " lineNo: "
                            + drCustOrderItemsList.get(i).getLine_no());
                } else {
                    logger.info("[檢查dr_cust_order_items不存在,新增] == store_no: " + storeNo + " ord_no: "
                            + drCustOrderItemsList.get(i).getOrder_no() +
                            " itemNo: " + drCustOrderItemsList.get(i).getItem_no() + " lineNo: "
                            + drCustOrderItemsList.get(i).getLine_no());
                    ecOrderDao.insertDrCustOrderItems(storeNo, drCustOrderItemsList.get(i));
                }
            }

            if (status == 0 || status == 6 || (status == 2 && drCustOrders.getStatus() == 6) ||
                    ((status == 5 || status == 9)
                            && (drCustOrders.getStatus() == 6 || drCustOrders.getStatus() == 7))) {
                // update dr_cust_orders info
                logger.info("[更新dr_cust_orders資料] == store_no: " + storeNo + " ord_no: " + drCustOrders.getOrder_no());
                ecOrderDao.updateDrCustOrders(storeNo, drCustOrders);

                // rts status != json status
                if (status != drCustOrders.getStatus()) {
                    ecOrderDao.insertDailyLog(storeNo, drCustOrders.getOrder_no());
                }
                logger.info("====================================================" + "\n");
                return 101; // 更新訂單完成
            }
            logger.info("====================================================" + "\n");
            return 101; // 更新訂單完成
        }
    }

    @Override
    public String ti_order_process(String storeNo, Integer orderNo) {
        // chk_order_is_ti
        Integer chk_order_is_ti = ecOrderDao.chk_order_is_ti(storeNo, orderNo);
        logger.info("1.chk_order_is_ti: " + chk_order_is_ti);
        if (chk_order_is_ti == 1) {
            logger.info("[檢查是TI商品] == store_no: " + storeNo + " ord: " + orderNo);
            sleep(3000);
            // update rts dr_cust_orders status and dr_cust_order_items status & type
            Integer upd_order = ecOrderDao.upd_dr_cust_orders(storeNo, orderNo);
            logger.info("2.upd_order: " + upd_order);
            if (upd_order == 1) {
                sleep(3000);
                logger.info("[更新rts訂單成功] == store_no: " + storeNo + " ord: " + orderNo);
                // update ec set status = 19
                Integer setStatus19 = ecOrderDao.api_SetOrderStatus19(orderNo);
                logger.info("3.setStatus19: " + setStatus19);
                if (setStatus19 == 0) {
                    logger.info("[更新ec訂單成功狀19] == store_no: " + storeNo + " ord: " + orderNo);
                    sleep(8000);
                    // update ec set status = 21
                    Integer api_box_v2 = ecOrderDao.api_box_v2(storeNo,orderNo);
                    logger.info("4.api_box_v2: " + api_box_v2);
                    if (api_box_v2 == 0) {
                        logger.info("[更新ec訂單成功狀21] == store_no: " + storeNo + " ord: " + orderNo);
                        sleep(5000);
                        // 結帳
                        Integer printer_invoice = ecOrderDao.print_invoice(storeNo, orderNo);
                        logger.info("5.printer_invoice: " + printer_invoice);
                        switch (printer_invoice) {
                            case 0:
                                logger.info("[TI訂單結帳成功] == store_no: " + storeNo + " ord: " + orderNo);
                                sleep(5000);
                                // 更新訂單狀態為21
                                Integer SetOrderStatus21 = ecOrderDao.SetOrderStatus21(orderNo);
                                if (SetOrderStatus21 == 0) {
                                    logger.info("[更新ec訂單狀態為21成功] == store_no: " + storeNo + " ord: " + orderNo);
                                    return "success";
                                    // sleep(5000);
                                    // // 更新px貨態到專人電聯
                                    // Integer statusCode = ecOrderDao.update_cargo_status(Integer.parseInt(storeNo),orderNo);
                                    // if (statusCode == 200) {
                                    //     return "success";
                                    // } else {
                                    //     return "update_cargo_fail";
                                    // }
                                } else {
                                    logger.info("[更新ec訂單狀態為21失敗] == store_no: " + storeNo + " ord: " + orderNo);
                                    return "fail";
                                }
                            case 4:
                                logger.info("[TI訂單-狀態不為撿貨完成,無法結帳] == store_no: " + storeNo + " ord: " + orderNo);
                                return "4";
                            case 8:
                                logger.info("[TI訂單-已結帳完成] == store_no: " + storeNo + " ord: " + orderNo);
                                return "8";
                            default:
                                logger.info("[TI訂單-結帳異常] == store_no: " + storeNo + " ord: " + orderNo);
                                return "999";
                        }
                    } else {
                        logger.info("[更新ec訂單失敗狀21] == store_no: " + storeNo + " ord: " + orderNo);
                        return "update_ec_error_21";
                    }
                } else {
                    logger.info("[更新ec訂單失敗狀19] == store_no: " + storeNo + " ord: " + orderNo);
                    return "update_ec_error_19";
                }
            } else {
                logger.info("[更新rts訂單失敗] == store_no: " + storeNo + " ord: " + orderNo);
                return "update_rts_error";
            }
        } else {
            logger.info("[檢查不是TI商品] == store_no: " + storeNo + " ord: " + orderNo);
            return "not_ti";
        }       
    }

    public void sleep(Integer millis){
        try {
            Thread.sleep(millis);
            logger.info("sleep: " + millis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("sleep: " + e.getMessage());
        }
    }
}
