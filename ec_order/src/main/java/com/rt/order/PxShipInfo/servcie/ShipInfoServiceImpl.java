package com.rt.order.PxShipInfo.servcie;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rt.order.PxShipInfo.Dao.ShipInfoDao;
import com.rt.order.PxShipInfo.model.ForwardParam;
import com.rt.order.PxShipInfo.model.OrderDetail;
import com.rt.order.PxShipInfo.model.ReverseParam;
import com.rt.order.PxShipInfo.model.ShipInfoRes;
import com.rt.order.PxShipInfo.model.ShipInfoResInfo;
import com.rt.order.utility.model.ReturnMessage;

@PropertySource(value = "classpath:config/environment.properties")
@Service
public class ShipInfoServiceImpl implements ShipInfoService {

    @Autowired
    ShipInfoDao shipInfoDao;

    // 正向
    @Value("${px.status.ready.to.pick}")
    private Integer ready_to_pick;
    @Value("${px.status.pick.finish}")
    private Integer pick_finish;
    @Value("${px.status.pick.sold.out}")
    private Integer pick_sold_out;
    @Value("${px.status.delivered}")
    private Integer delivered;
    @Value("${px.status.arrived}")
    private Integer arrived;

    // 退貨
    @Value("${px.status.return.process}")
    private Integer return_process;
    @Value("${px.status.return.finish}")
    private Integer return_finish;
    @Value("${px.status.return.fail}")
    private Integer return_fail;

    Logger shipInfoLog = LoggerFactory.getLogger("pxship.detail");

    @Override
    public ReturnMessage get_shipinfo_forward(ForwardParam forwardParam) {

        /*
         * 貨態正向
         * status=6 px=100 撿貨中 -> ready_to_pick
         * status=5 px=110/120 撿貨完成/斷貨 -> pick_finish , delivery_order_items.type=1 &
         * delivery_order_items.type=2
         * status=1 px=130 已出貨 -> delivered , delivery_order_items.type=1
         * status=7 px=140 已送達 -> arrived, delivery_order_items.type=1
         */

        Integer get_status = shipInfoDao.chk_order(forwardParam.getStore_no(), forwardParam.getOrder_no());
        shipInfoLog.info("[get_shipinfo_forward - chk_order]: " + get_status);

        List<OrderDetail> list = shipInfoDao.get_order_data(forwardParam);
        shipInfoLog.info("[get_shipinfo_forward - get_order_data - list]: " + list.toString());
        ShipInfoRes shipInfoRes = new ShipInfoRes();

        // 查無此訂單
        if (get_status == -1 || (list == null || list.isEmpty())) {
            shipInfoLog.info("[get_shipinfo_forward]: " + "查無此訂單");
            return new ReturnMessage(1001, "查無此訂單");
        } else {
            List<ShipInfoResInfo> shipInfoResInfos = new ArrayList<ShipInfoResInfo>();
            for (int i = 0; i < list.size(); i++) {
                ShipInfoResInfo info = new ShipInfoResInfo();
                info.setOrder_uid(list.get(i).getOrder_no());
                info.setProd_uid(list.get(i).getItem_no());
                info.setReply_time(list.get(i).getReply_time());

                switch (forwardParam.getStatus()) {
                    case "ready_to_pick":
                        if (get_status == 6) {
                            info.setStatus(ready_to_pick);
                            info.setLogistic_name("");
                            info.setTracking_number("");                            
                        } else {
                            shipInfoLog.info("[get_shipinfo_forward]: " + "ready_to_pick: "+ "更新px貨態為100狀態不為6");
                            return new ReturnMessage(1001, "更新px貨態為100狀態不為6");
                        }
                        break;
                    case "pick_finish":
                        if (get_status == 5) {
                            info.setLogistic_name("");
                            info.setTracking_number("");
                            if (list.get(i).getType() == 1) {
                                info.setStatus(pick_finish);
                            } else
                                info.setStatus(pick_sold_out);
                        } else {
                            shipInfoLog.info("[get_shipinfo_forward]: " + "pick_finish: "+ "更新px貨態為110/120狀態不為5");
                            return new ReturnMessage(1001, "更新px貨態為110/120狀態不為5");
                        }
                        break;
                    case "delivered":
                        if (get_status == 7) {
                            info.setStatus(delivered);
                            info.setLogistic_name(list.get(i).getLogistic_name());
                            info.setTracking_number(list.get(i).getTracking_number());
                        } else {
                            shipInfoLog.info("[get_shipinfo_forward]: " + "delivered: "+ "更新px貨態為130狀態不為1");
                            return new ReturnMessage(1001, "更新px貨態為130狀態不為7");
                        }
                        break;
                    case "arrived":
                        if (get_status == 7) {
                            info.setStatus(arrived);
                            info.setLogistic_name(list.get(i).getLogistic_name());
                            info.setTracking_number(list.get(i).getTracking_number());
                        } else {
                            shipInfoLog.info("[get_shipinfo_forward]: " + "arrived: "+ "更新px貨態為140狀態不為7");
                            return new ReturnMessage(1001, "更新px貨態為140狀態不為7");
                        }
                        break;
                    default:
                        shipInfoLog.info("[get_shipinfo_forward]: " + "更新px貨態狀態名稱錯誤");
                        return new ReturnMessage(1001, "更新px貨態狀態名稱錯誤");
                }
                shipInfoResInfos.add(info);
            }
            shipInfoRes.setShip_infos(shipInfoResInfos);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            shipInfoLog.info("[get_shipinfo_forward - json]" + objectMapper.writeValueAsString(shipInfoRes));
            //call .83 api
            ReturnMessage returnMessage = shipInfoDao.call_px_api(shipInfoRes);
            shipInfoLog.info("[get_shipinfo_forward - returnMessage]" + returnMessage.getMessage());

            return returnMessage;
        } catch (JsonProcessingException e) {            
            e.printStackTrace();
            shipInfoLog.info("[get_shipinfo_forward - fail]" + e.getMessage());
            ReturnMessage returnMessage = new ReturnMessage();
            returnMessage.setStatusCode(9999);
            returnMessage.setMessage("未定義異常");
            shipInfoLog.info("[get_shipinfo_forward - fail]: "+ e.getMessage());
            return returnMessage;
        }
    }

    @Override
    public ReturnMessage get_shipinfo_return(ReverseParam reverseParam) {
        /*
         * 貨態逆向
         * 210 退貨處理中 -> return_process
         * 220 退貨完成 -> return_finish
         * 221 退貨失敗 -> return_fail
         */
        List<OrderDetail> list = shipInfoDao.get_return_data(reverseParam);
        ShipInfoRes shipInfoRes = new ShipInfoRes();
        if (reverseParam.getStatus().equals("return_process")) {
            Integer get_return_status = shipInfoDao.chk_return_order(reverseParam.getStore_no(),
                    reverseParam.getReturn_order_no());
            if (get_return_status == -1 || (list == null || list.isEmpty())) {
                shipInfoLog.info("[get_shipinfo_return]: " + "查無此退貨單");
                return new ReturnMessage(1001, "查無此退貨單");
            } else {
                if (get_return_status == 40) {
                    List<ShipInfoResInfo> shipInfoResInfos = new ArrayList<ShipInfoResInfo>();
                    for (int i = 0; i < list.size(); i++) {
                        ShipInfoResInfo info = new ShipInfoResInfo();
                        info.setOrder_uid(list.get(i).getOrder_no());
                        info.setProd_uid(list.get(i).getItem_no());
                        info.setReply_time(list.get(i).getReply_time());
                        info.setStatus(return_process);
                        info.setLogistic_name(list.get(i).getLogistic_name());
                        info.setTracking_number(list.get(i).getTracking_number());

                        shipInfoResInfos.add(info);
                    }
                   shipInfoRes.setShip_infos(shipInfoResInfos);
                   shipInfoLog.info("[get_shipinfo_return - json]: " + shipInfoRes.toString());
                }else return new ReturnMessage(1001, "更新px貨態130狀態錯誤");
            }
        }else if(reverseParam.getStatus().equals("return_finish")){
            Integer get_refund_status = shipInfoDao.chk_refund_order(reverseParam.getStore_no(), reverseParam.getRefund_no());
            if(get_refund_status==-1 || (list == null || list.isEmpty())){
                shipInfoLog.info("[get_shipinfo_return]: " + "查無此訂退款單");
                return new ReturnMessage(1001, "查無此訂退款單");
            }else{
                if (get_refund_status == 2) {
                    List<ShipInfoResInfo> shipInfoResInfos = new ArrayList<ShipInfoResInfo>();
                    for (int i = 0; i < list.size(); i++) {
                        ShipInfoResInfo info = new ShipInfoResInfo();
                        info.setOrder_uid(list.get(i).getOrder_no());
                        info.setProd_uid(list.get(i).getItem_no());
                        info.setReply_time(list.get(i).getReply_time());
                        info.setStatus(return_finish);
                        info.setLogistic_name(list.get(i).getLogistic_name());
                        info.setTracking_number(list.get(i).getTracking_number());

                        shipInfoResInfos.add(info);
                    }
                   shipInfoRes.setShip_infos(shipInfoResInfos);
                   shipInfoLog.info("[get_shipinfo_return - json]: " + shipInfoRes.toString());
                }else return new ReturnMessage(1001, "更新px貨態140狀態錯誤");
            }
        }else{
            shipInfoLog.info("[get_shipinfo_return]: " + "更新px貨態狀態名稱錯誤");
            return new ReturnMessage(1001, "更新px貨態狀態名稱錯誤");
        }
        

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            shipInfoLog.info("[get_shipinfo_return - json]" + objectMapper.writeValueAsString(shipInfoRes));
            //call .83 api
            ReturnMessage returnMessage = shipInfoDao.call_px_api(shipInfoRes);
            shipInfoLog.info("[get_shipinfo_return - returnMessage]" + returnMessage.getMessage());

            return returnMessage;
        } catch (JsonProcessingException e) {            
            e.printStackTrace();
            shipInfoLog.info("[get_shipinfo_return - fail]" + e.getMessage());
            ReturnMessage returnMessage = new ReturnMessage();
            returnMessage.setStatusCode(9999);
            returnMessage.setMessage("未定義異常");
            shipInfoLog.info("[get_shipinfo_return - fail]: "+ e.getMessage());
            return returnMessage;
        }
    }
}
