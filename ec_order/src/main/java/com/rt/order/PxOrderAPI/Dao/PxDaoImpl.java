package com.rt.order.PxOrderAPI.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.rt.order.PxOrderAPI.Model.CancelReq;
import com.rt.order.PxOrderAPI.Model.OrderReturnDfData;
import com.rt.order.PxOrderAPI.Model.ReturnReq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PropertySource(value = "classpath:config/environment.properties")
@PropertySource(value = "classpath:config/host.properties")
@Repository
public class PxDaoImpl implements PxDao {

    @Autowired
    @Qualifier("storeJDBC")
    private Map<String, NamedParameterJdbcTemplate> storeNamedParameterJdbcTemplate;

    @Autowired
    RestTemplate restTemplate;

    Logger clogger = LoggerFactory.getLogger("cancel.detail");
    Logger rlogger = LoggerFactory.getLogger("return.detail");
    @Override
    public Integer check_status(CancelReq cancelReq) {
        clogger.info("確認貨態 check_status");
        String sql = "SELECT status FROM DELIVERY_ORDERS WHERE order_no =:order_no ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_no", cancelReq.getOrder_uid());
        map.put("status", cancelReq.getStatus());
        Integer count = storeNamedParameterJdbcTemplate.get(String.format("%2d", cancelReq.getStore_no())).queryForObject(sql, map, Integer.class);
        clogger.debug(map.toString());
        clogger.debug("xxx check_status" + " == orderNo ==" + cancelReq.getOrder_uid() + " == count == " + count);
        try {
            return count;
        } catch (EmptyResultDataAccessException e) {
            count = null;
        }
        return count;
    }


    @Override
    public Integer update_px_status(CancelReq cancelReq) {
        clogger.debug("更新貨態為取消訂單 update_px_status");//更新取消訂單貨態
        Integer storeNo = cancelReq.getStore_no();
        String sql = " update DELIVERY_ORDERS set status = case status when 0 then 4 when 2 then 4 when 6 then 4 ELSE status end " +
                " where order_no in (" + cancelReq.getOrder_uid() + ") ";
        Map<String, Object> map = new HashMap<String, Object>();
        Integer count = storeNamedParameterJdbcTemplate.get(String.format("%2d", storeNo)).update(sql, map);

        try {
            //Integer count = namedParameterJdbcTemplate.update(sql, map);
            clogger.debug("xxx update_px_status" + " == orderNo ==" + cancelReq.getOrder_uid() + " == count == " + count);
            return count;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Integer insert_cancel_refund_data(CancelReq cancelReq) {
        clogger.debug("取消訂單 insert_cancel_refund_data");//取消訂單 退款單 DELIVERY_ORDER_REFUND_DATA
        Integer storeNo = cancelReq.getStore_no();

        String sql = "INSERT INTO DELIVERY_ORDER_REFUND_DATA (platform_no, order_no, refund_no, refund_price, refund_surcharge, status, cause, type, refund_date, create_date ) "
                + "VALUES (7,  :order_uid, PX_REFUND_SEQ.nextval, :order_ref_money, :order_ref_surcharge, 2, 1, 3, TO_DATE(:order_refund_date, 'YYYY-MM-DD HH24:MI:SS'),SYSDATE)";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_uid", cancelReq.getOrder_uid());  // 設置 'order_uid' 參數的值
        if (cancelReq.getData() != null) {
            clogger.debug("check getData != null");
            map.put("order_ref_money", cancelReq.getData().getOrder_refund_data().getOrder_ref_money());
            map.put("order_ref_surcharge", cancelReq.getData().getOrder_refund_data().getOrder_ref_surcharge());
            map.put("order_refund_date", cancelReq.getData().getOrder_refund_data().getOrder_ref_date());
        }
        Integer count = storeNamedParameterJdbcTemplate.get(String.format("%2d", storeNo)).update(sql, map);
        try {
            //Integer count = namedParameterJdbcTemplate.update(sql, map);
            clogger.debug("xxx insert_cancel_data" + " == orderNo ==" + cancelReq.getOrder_uid() + " == count == " + count);
            return count;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Integer insert_candel_order_return(CancelReq cancelReq) {
        clogger.debug("取消訂單 insert_candel_order_return");//取消訂單 退貨主檔 DELIVERY_ORDER_RETURN
        Integer storeNo = cancelReq.getStore_no();
        String sql = "INSERT INTO DELIVERY_ORDER_RETURN (platform_no, order_no, RETURN_ORDER_NO, return_date, return_memo, RETURN_PRICE, LOGISTICS_TYPE, STATUS,status_date) "
                + "VALUES (7, :order_uid, PX_RETURN_SEQ.nextval, TO_DATE(:order_ref_date, 'YYYY-MM-DD HH24:MI:SS'), :cancel_memo,:RETURN_PRICE, 0, 40, SYSDATE)";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_uid", cancelReq.getOrder_uid());  // 設置 'order_uid' 參數的值
        map.put("order_ref_date", cancelReq.getData().getOrder_refund_data().getOrder_ref_date());
        map.put("cancel_memo", "攔單");
        map.put("RETURN_PRICE",cancelReq.getData().getOrder_refund_data().getOrder_ref_money());
//        map.put("contact_name", cancelReq.getData().getOrder_cancel_data().getContact_name());
//        map.put("contact_tel", cancelReq.getData().getOrder_cancel_data().getContact_tel());

        Integer count = storeNamedParameterJdbcTemplate.get(String.format("%2d", storeNo)).update(sql, map);

        try {
            //Integer count = namedParameterJdbcTemplate.update(sql, map);
            clogger.debug("xxx insert_candel_order_return" + " == orderNo ==" + cancelReq.getOrder_uid() + " == count == " + count);
            return count;
        } catch (Exception e) {
            return null;
        }

    }


    public Integer insert_return_order_return(ReturnReq returnReq) {
        rlogger.debug("退貨 insert_return_order_return");//退貨 insert退貨主檔 DELIVERY_ORDER_RETURN
        Integer store_no = returnReq.getStore_no();
        String sql = "INSERT INTO DELIVERY_ORDER_RETURN (platform_no, order_no, RETURN_ORDER_NO, return_date, return_memo, RETURN_PRICE, LOGISTICS_TYPE, STATUS,status_date) "
                + "VALUES (7, :order_uid, PX_RETURN_SEQ.nextval, TO_DATE(:order_ref_date, 'YYYY-MM-DD HH24:MI:SS'), :cancel_memo , :return_price, 0, 40, SYSDATE)";
//        String sql = "INSERT INTO DELIVERY_ORDER_RETURN (platform_no, order_no, RETURN_ORDER_NO, return_date, return_memo, RETURN_PRICE, LOGISTICS_TYPE, STATUS, status_date) "
//                + "VALUES (7, ?, DELIVERY_ORDER_RETURN_SEQ.nextval, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'), ?, ?, 0, 40, SYSDATE)";


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_uid", returnReq.getOrder_uid());  // 設置 'order_uid' 參數的值
        //map.put("RETURN_ORDER_NO", Math.random() * 10000);
        map.put("order_ref_date", returnReq.getData().getOrder_return_data().getReturn_request_date());
        map.put("cancel_memo", "攔單");
        map.put("return_price", returnReq.getData().getOrder_return_data().getOrder_ref_money());
//            map.put("RETURN_PRICE", returnReq.getData().getOrder_return_data().getContact_name());
//            map.put("contact_tel", returnReq.getData().getOrder_return_data().getContact_tel());
        Integer count = storeNamedParameterJdbcTemplate.get(String.format("%2d", store_no)).update(sql, map);

        rlogger.debug(String.valueOf(count));
        try {
            //Integer count = namedParameterJdbcTemplate.update(sql, map);
            rlogger.debug("xxx insert_return_order_return" + " == orderNo ==" + returnReq.getOrder_uid() + " == count == " + count);
            return count;
        } catch (Exception e) {
            return null;
        }

    }

    public Integer insert_return_items_all(ReturnReq returnReq) {
        rlogger.debug("整單退貨 insert_return_items_all");//退貨明細整筆退的話 出貨=退貨 DELIVERY_ORDER_ITEMS.TOTAL_PRICE=delivery_order_return_items.total_prica
        Integer store_no = returnReq.getStore_no();
        String sql = "INSERT INTO DELIVERY_ORDER_RETURN_ITEMS (platform_no, order_no, RETURN_ORDER_NO, item_no, delivery_qty, return_qty, total_price)" +
                "                SELECT platform_no, order_no, (SELECT RETURN_ORDER_NO FROM DELIVERY_ORDER_RETURN WHERE order_no in (" + returnReq.getOrder_uid() + ")), item_no, qty, qty, total_price" +
                "                FROM DELIVERY_ORDER_ITEMS" +
                "                WHERE DELIVERY_ORDER_ITEMS.order_no in (" + returnReq.getOrder_uid() + ")";

        Map<String, Object> map = new HashMap<String, Object>();
        Integer count = storeNamedParameterJdbcTemplate.get(String.format("%2d", store_no)).update(sql, map);
        rlogger.debug("xxx insert_return_items_all" + " == orderNo ==" + returnReq.getOrder_uid() + " == count == " + count);
        //map.put("order_uid", returnReq.getOrder_uid());
        //map.put("RETURN_ORDER_NO", Math.random() * 10000);
        //storeNamedParameterJdbcTemplate.get(String.format("%2d", store_no)).update(sql, map);
        if (count > 0) {
            rlogger.debug(String.valueOf(count));
            return 1;
        } else {
            return null;
        }
    }

    public Integer insert_return_items(ReturnReq returnReq) {
        rlogger.debug("部分退貨 insert_return_items");//退貨明細 DELIVERY_ORDER_RETURN_ITEMS 當部分退貨時須insert(status=45)
        /*  PLATFORM_NO	NUMBER(2) px:7
            ORDER_NO	NUMBER(6) 訂單號碼
            SEQ_NO	NUMBER(6) 退貨單號
            ITEM_NO	NUMBER(6) 貨號
            DELIVERY_QTY	NUMBER(6) 出貨數量
            RETURN_QTY	NUMBER(6) 退貨數量
            TOTAL_PRICE	實際售價
         */
        Integer store_no = returnReq.getStore_no();
        String sql = "INSERT INTO DELIVERY_ORDER_RETURN_ITEMS (platform_no, order_no, RETURN_ORDER_NO, item_no, delivery_qty, return_qty, total_price)" +
                "VALUES (7, :order_uid, PX_RETURN_SEQ.nextval,:item_no, :delivery_qty , :return_qty, :total_price)";
        List<OrderReturnDfData> dataList = returnReq.getData().getOrder_return_df_data();
        for (OrderReturnDfData data : dataList) {
            Map<String, Object> map = new HashMap<>();
            map.put("order_uid", returnReq.getOrder_uid());  // 設置 'order_uid' 參數的值
            //map.put("RETURN_ORDER_NO", Math.random() * 10000);
            map.put("item_no", data.getProd_uid());
            map.put("delivery_qty", data.getDelivery_qty());
            map.put("return_qty", data.getReturn_qty());
            map.put("total_price", data.getOrder_df_price());
            Integer count = storeNamedParameterJdbcTemplate.get(String.format("%2d", store_no)).update(sql, map);
            rlogger.debug(map.toString());
            rlogger.debug("xxx insert_return_items" + " == orderNo ==" + returnReq.getOrder_uid() + " == count == " + count);
        }

        try {

        } catch (Exception e) {
            return null;
        }
        return 1;
    }

    public Integer check_return_items(ReturnReq returnReq) {
        rlogger.debug("確認是否重複退貨 check_return_items");
        Integer store_no = returnReq.getStore_no();
        String sql = "SELECT COUNT(*) FROM DELIVERY_ORDER_RETURN_ITEMS WHERE order_no in (" + returnReq.getOrder_uid() + ")";
        Map<String, Object> map = new HashMap<String, Object>();
        Integer count = storeNamedParameterJdbcTemplate.get(String.format("%2d", store_no)).queryForObject(sql, map, Integer.class);
        if (count > 0) {
            rlogger.debug(String.valueOf(count));
            return 1;
        } else {
            return 0;
        }

    }

    public List<OrderReturnDfData> getReturnResData(ReturnReq returnReq) {//取得OrderReturnDfData 退貨明細有多個
        rlogger.debug("getReturnResData");
        Integer store_no = returnReq.getStore_no();
        List<OrderReturnDfData> list;
        String sql = "SELECT RETURN_ORDER_NO, item_no,DELIVERY_QTY,return_qty,total_price FROM DELIVERY_ORDER_RETURN_ITEMS WHERE order_no =:order_uid ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_uid", returnReq.getOrder_uid());
        list = storeNamedParameterJdbcTemplate.get(String.format("%2d", store_no)).query(sql, map, new OrderReturnDfDataRowMapper());
        rlogger.debug(list.toString());
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

}
