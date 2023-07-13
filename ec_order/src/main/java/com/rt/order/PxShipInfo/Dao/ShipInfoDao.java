package com.rt.order.PxShipInfo.Dao;

import java.util.List;

import com.rt.order.PxShipInfo.model.ForwardParam;
import com.rt.order.PxShipInfo.model.OrderDetail;
import com.rt.order.PxShipInfo.model.ReverseParam;
import com.rt.order.PxShipInfo.model.ShipInfoRes;
import com.rt.order.utility.model.ReturnMessage;


public interface ShipInfoDao {
    
    //確認訂單狀態與是否也此張單
    public Integer chk_order(Integer storeNo, Integer orderNo);

    //取得正向訂單資訊
    public List<OrderDetail> get_order_data(ForwardParam forwardParam);

    //確認退貨單狀態與是否有此張單 210退貨處理中,畫面按物流驗退
    public Integer chk_return_order(Integer storeNo, Integer returnNo);

    //確認退款單狀態與是否有此張單 220退貨完成,退款單畫面按退款完成
    public Integer chk_refund_order(Integer storeNo, Integer refundNo);

    //取得逆向訂單資訊
    public List<OrderDetail> get_return_data(ReverseParam reverseParam);

    //call .83 px_addship
    public ReturnMessage call_px_api(ShipInfoRes shipInfoRes);

    //ti get logistic_delivery_log.contact_date
    public String get_logistic_delivery_log(Integer storeNo, Integer orderNo);


}
