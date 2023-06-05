package com.rt.order.PxOrderAPI.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.rt.order.PxOrderAPI.Dao.PxDao;
import com.rt.order.PxOrderAPI.Model.CancelReq;
import com.rt.order.PxOrderAPI.Model.CancelRes;
import com.rt.order.PxOrderAPI.Model.CancelResInfo;
import com.rt.order.PxOrderAPI.Model.OrderReturnDfData;
import com.rt.order.PxOrderAPI.Model.RetrunResData;
import com.rt.order.PxOrderAPI.Model.ReturnReq;
import com.rt.order.PxOrderAPI.Model.ReturnRes;
import com.rt.order.utility.model.ReturnMessage;

import java.util.List;

@PropertySource(value = "classpath:config/environment.properties")
@Service
public class PxServiceImpl implements PxService {
    @Autowired
    private PxDao pxDao;
    Logger clogger = LoggerFactory.getLogger("cancel.info");
    Logger rlogger = LoggerFactory.getLogger("return.info");
    @Override
    public ReturnMessage cancel_px_status(CancelReq cancelReq) {

        if (cancelReq.getOrder_uid() == null || String.valueOf(cancelReq.getOrder_uid()).length() > 6 || cancelReq.getStore_no() == null || String.format("%02d",cancelReq.getStore_no()).length() > 2 || cancelReq.getData() == null) {
            ReturnMessage returnMessage = new ReturnMessage(400, "欄位格式錯誤");
            clogger.info("=== getOrder_uid=null ===" + cancelReq + "," + returnMessage);
            return returnMessage;
        }
        Integer check_status = pxDao.check_status(cancelReq);
        clogger.info("確認狀態 check_status=" + check_status);
        if (check_status == null) {
            ReturnMessage returnMessage = new ReturnMessage(400, "取消訂單失敗");
            clogger.info("=== check_status:null ===" + cancelReq+","+returnMessage);
            return returnMessage;
        } else if (check_status == 4) {
            CancelResInfo cancelResInfo = new CancelResInfo();
            cancelResInfo.setOrder_uid(cancelReq.getOrder_uid());
            CancelRes cancelRes = new CancelRes(402, "訂單重複取消", cancelResInfo);
            clogger.info("=== check_status:4 ===" + cancelReq+","+cancelRes);
            return cancelRes;
        } else if (check_status == 7 || check_status == 8) {
            ReturnMessage returnMessage = new ReturnMessage(403, "已出貨不允許取消");
            clogger.info("=== check_status:7 or 8 ===" + cancelReq+","+returnMessage);
            return returnMessage;
        } else if (check_status == 0 || check_status == 2 || check_status == 6 /*|| check_status == 4*/) {

            Integer check_insert_cancel = pxDao.insert_cancel_refund_data(cancelReq);//新增訂單退款單
            Integer check_insert_return = pxDao.insert_candel_order_return(cancelReq);//新增退貨主檔
            clogger.info("check_insert_cancel="+check_insert_cancel);
            clogger.info("check_insert_return"+check_insert_return);
            if (check_insert_cancel == 1 && check_insert_return == 1) {
                Integer check_update = pxDao.update_px_status(cancelReq);
                if (check_update == 1) {
                    CancelResInfo cancelResInfo = new CancelResInfo();
                    cancelResInfo.setOrder_uid(cancelReq.getOrder_uid());
                    CancelRes cancelRes = new CancelRes(200, "Success", cancelResInfo);
                    clogger.info("=== check_update:1 ===" + cancelReq);
                    return cancelRes;
                }
            } else {
                ReturnMessage returnMessage = new ReturnMessage(400, "取消訂單失敗");
                clogger.info("=== check_insert_cancel != 1 && check_insert_return !=1 ===" + cancelReq+","+returnMessage);
                return returnMessage;
            }
        } else {
            ReturnMessage returnMessage = new ReturnMessage(400, "取消訂單失敗");
            clogger.info(cancelReq+","+returnMessage);
            return returnMessage;
        }

        ReturnMessage returnMessage = new ReturnMessage(400, "取消訂單失敗");
        clogger.info(cancelReq+","+returnMessage);
        return returnMessage;
    }

    @Override
    public ReturnMessage return_px_status(ReturnReq returnReq) {
        if (returnReq.getOrder_uid() == null || String.valueOf(returnReq.getOrder_uid()).length() > 6 || String.format("%02d",returnReq.getStore_no()).length() > 2 || returnReq.getOrder_status() == null || returnReq.getData() == null) {
            ReturnMessage returnMessage = new ReturnMessage(400, "欄位格式錯誤");//確認是否輸入order_id
            rlogger.info(returnReq+","+returnMessage);
            return returnMessage;
        }else{
            rlogger.info("return_px_status 確認是否重複退貨");//將request資料insert進訂單退款單 退貨單主檔 (status==45為部分退貨)>要多insert退貨明細
            Integer check_return_items = pxDao.check_return_items(returnReq);
            rlogger.info("check_return_items"+check_return_items);
            if(check_return_items != 0){
                List<OrderReturnDfData> list;
                list = pxDao.getReturnResData(returnReq);
                RetrunResData retrunResData = new RetrunResData();
                retrunResData.setOrder_return_df_data(list);
                retrunResData.setOrder_uid(returnReq.getOrder_uid());
                ReturnRes returnRes = new ReturnRes(402, "訂單重複退貨", retrunResData);
                rlogger.info(returnReq+","+returnRes);
                return returnRes;
            }else if (check_return_items == 0) {
                if (returnReq.getOrder_status() == 44) {
                    rlogger.info("status = 44 整單退貨 insert退貨主檔");
                    Integer insert_order_return = pxDao.insert_return_order_return(returnReq);
                    if (insert_order_return == 1) {
                        rlogger.info("insert退貨主檔完成>insert退貨明細");
                        Integer check_all_items = pxDao.insert_return_items_all(returnReq);
                        rlogger.info("insert退貨明細完成 check_all_items="+ check_all_items);
                        if (check_all_items == null) {
                            ReturnMessage returnMessage = new ReturnMessage(400, "欄位格式錯誤");
                            rlogger.info(returnReq+","+returnMessage);
                            return returnMessage;
                        } else {
                            List<OrderReturnDfData> list;
                            list = pxDao.getReturnResData(returnReq);
                            RetrunResData retrunResData = new RetrunResData();
                            retrunResData.setOrder_uid(returnReq.getOrder_uid());
                            retrunResData.setOrder_return_df_data(list);
                            rlogger.info("list"+list);
                            ReturnRes returnRes = new ReturnRes(200, "Success", retrunResData);
                            rlogger.info(returnReq.toString());
                            return returnRes;
                        }
                    } else {
                        ReturnMessage returnMessage = new ReturnMessage(400, "整單退貨失敗，請重新拋送");
                        rlogger.info(returnReq+","+returnMessage);
                        return returnMessage;
                    }
                } else if (returnReq.getOrder_status() == 45) {//insert 退貨明細
                    rlogger.info("status = 45 部分退貨>insert退貨主檔");
                    Integer insert_order_return = pxDao.insert_return_order_return(returnReq);
                    if (insert_order_return == 1) {
                        rlogger.info("insert退貨主檔完成>insert退貨明細");
                        Integer check_insert_return_items = pxDao.insert_return_items(returnReq);
                        rlogger.info("insert退貨明細完成 check_insert_return_items="+check_insert_return_items);
                        if (check_insert_return_items != null) {
                            List<OrderReturnDfData> list;
                            list = pxDao.getReturnResData(returnReq);
                            RetrunResData retrunResData = new RetrunResData();
                            retrunResData.setOrder_return_df_data(list);
                            retrunResData.setOrder_uid(returnReq.getOrder_uid());
                            ReturnRes returnRes = new ReturnRes(200, "Success", retrunResData);
                            rlogger.info(returnReq.toString());
                            return returnRes;
                        } else {
                            ReturnMessage returnMessage = new ReturnMessage(400, "部分退貨失敗，請重新拋送");
                            rlogger.info(returnReq+","+returnMessage);
                            return returnMessage;
                        }
                    } else {
                        ReturnMessage returnMessage = new ReturnMessage(400, "部分退貨失敗，請重新拋送");
                        rlogger.info(returnReq+","+returnMessage);
                        return returnMessage;
                    }
                } else {
                    ReturnMessage returnMessage = new ReturnMessage(400, "欄位格式錯誤");
                    rlogger.info(returnReq+","+returnMessage);
                    return returnMessage;
                }
            }else {
                ReturnMessage returnMessage = new ReturnMessage(400, "欄位格式錯誤");
                rlogger.info(returnReq+","+returnMessage);
                return returnMessage;
            }
        }

    }


}

