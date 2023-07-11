package com.rt.order.DeliveryOrders.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rt.order.DeliveryOrders.Model.DeliveryCustInfo;
import com.rt.order.DeliveryOrders.Model.DeliveryOrderItems;
import com.rt.order.DeliveryOrders.Model.DeliveryOrders;
import com.rt.order.configuration.connection.StoreIpConfiguraion;
import com.rt.order.utility.model.ReturnMessage;

@PropertySource(value = "classpath:config/host.properties")
@Repository
public class PxOrdersDaoImpl implements PxOrdersDao {

    @Autowired
    @Qualifier("storeJDBC")
    private Map<String, NamedParameterJdbcTemplate> storeNamedParameterJdbcTemplate;

    @Value("${rt-api.ec_order.domain.path}")
    private String ShipinfoUrl;

    Logger logger = LoggerFactory.getLogger("px");

    @Override
    public Integer getOrderSeq(String storeNo) {
        String sql = "select px_order_seq.nextval from dual ";
        Map<String, Object> map = new HashMap<String, Object>();
        Integer seq = storeNamedParameterJdbcTemplate.get(storeNo).queryForObject(sql, map, Integer.class);

        logger.info("[px-getOrderSeq] now: "+ seq);

        return seq;
    }


    @Override
    public Integer insertDeliveryOrders(String storeNo, DeliveryOrders deliveryOrders) {
        String sql = "insert into delivery_orders (" +
                "platform_no, " +
                "order_no, " +
                "id_no, " +
                "display_no, " +
                "store_no, " +
                "ext_no, " +
                "status, " +
                "type, " +
                "eater, " +
                "total, " +
                "create_time, " +
                "delivery_carriage ) " +
                "select " +
                ":platformNo, " +
                ":orderNo, " +
                ":idNo, " +
                ":displayNo, " +
                ":storeNo, " +
                ":extNo, " +
                ":status, " +
                ":type, " +
                ":eater, " +
                ":total, " +
                "to_date(:createTime,'yyyy-mm-dd hh24:mi:ss'), " +
                ":delivery_carriage " +
                "  from dual " +
                " where not exists ( " +
                "  select 'x' from delivery_orders " +
                "   where platform_no = :platformNo " +
                "     and id_no = :idNo ) ";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platformNo", deliveryOrders.getPlatformNo());
        map.put("orderNo", deliveryOrders.getOrderNo());
        map.put("idNo", deliveryOrders.getIdNo());
        map.put("displayNo", deliveryOrders.getDisplayNo());
        map.put("storeNo", deliveryOrders.getStoreNo());
        map.put("extNo", deliveryOrders.getExtNo());
        map.put("status", deliveryOrders.getStatus());
        map.put("type", deliveryOrders.getType());
        map.put("eater", deliveryOrders.getEater());
        map.put("total", deliveryOrders.getTotal());
        map.put("createTime", deliveryOrders.getCreateTime());
        map.put("delivery_carriage", deliveryOrders.getDeliveryCarriage());

        return storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);
    }

    @Override
    public Integer insertDeliveryOrderItems(String storeNo, List<DeliveryOrderItems> deliveryOrderItemsList) {
        String sql = "insert into delivery_order_items (" +
                "platform_no, " +
                "order_no, " +
                "item_seq, " +
                "item_no, " +
                "name, " +
                "status, " +
                "type, " +
                "unit_price, " +
                "sales_amount, " +
                "total_price, " +
                "qty, " +
                "original_qty, " +
                "total_amount " +
                ") values(" +
                ":platformNo, " +
                ":orderNo, " +
                ":itemSeq, " +
                ":itemNo, " +
                "substrb(:name,0,33), " +
                ":status, " +
                ":type, " +
                ":unitPrice, " +
                ":salesAmount, " +
                ":totalPrice, " +
                ":qty, " +
                ":originalQty, " +
                ":totalAmount ) ";
        List<Map<String,Object>> maplist = new ArrayList<Map<String,Object>>(deliveryOrderItemsList.size());
        Map<String, Object> map ;
        for(int i = 0; i < deliveryOrderItemsList.size(); i++) {
            map = new HashMap<String, Object>();
            map.put("platformNo", deliveryOrderItemsList.get(i).getPlatformNo());
            map.put("orderNo", deliveryOrderItemsList.get(i).getOrderNo());
            map.put("itemSeq", deliveryOrderItemsList.get(i).getItemSeq());
            map.put("itemNo", deliveryOrderItemsList.get(i).getItemNo());
            map.put("name", deliveryOrderItemsList.get(i).getName());            
            map.put("status", deliveryOrderItemsList.get(i).getStatus());
            map.put("type", deliveryOrderItemsList.get(i).getType());
            map.put("unitPrice", deliveryOrderItemsList.get(i).getUnitPrice());
            map.put("salesAmount", deliveryOrderItemsList.get(i).getSalesAmount());
            map.put("totalPrice", deliveryOrderItemsList.get(i).getTotalPrice());
            map.put("qty", deliveryOrderItemsList.get(i).getQty());
            map.put("originalQty", deliveryOrderItemsList.get(i).getOriginalQty());
            map.put("totalAmount", deliveryOrderItemsList.get(i).getTotalAmount());

            maplist.add(map);
        }

        try{
            storeNamedParameterJdbcTemplate.get(storeNo).batchUpdate(sql, maplist.toArray(new Map[maplist.size()]));
            return 1;
        }catch(Exception e){
            logger.info("[新增訂單明細]: "+"ordNo: "+ deliveryOrderItemsList.get(0).getOrderNo()+ " 失敗: " + e.toString());
            return 0;
        }
    }

    @Override
    public Integer insertDeliveryCustInfo(String storeNo, DeliveryCustInfo deliveryCustInfo) {
        String sql = "insert into delivery_order_cust_info (" +
                "platform_no, " +
                "order_no, " +
                "name, " +
                "phone_no, " +
                "mail, " +
                "city, " +
                "zip, " +
                "address, " +
                "is_pay, " +
                "pay_money, " +
                "invoice_get_type, " +
                "invoice_status ) " +
                "select " +
                ":platform_no, " +
                ":order_no, " +
                ":name, " +
                ":phone_no, " +
                ":mail, " +
                ":city, " +
                ":zip, " +
                ":address, " +
                ":is_pay, " +
                ":pay_money, " +
                ":invoice_get_type, " +
                ":invoice_status " +
                "  from dual " +
                " where not exists ( " +
                "  select 'x' from delivery_order_cust_info " +
                "   where platform_no = :platform_no " +
                "     and order_no = :order_no ) ";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("platform_no", deliveryCustInfo.getPlatformNo());
        map.put("order_no", deliveryCustInfo.getOrderNo());
        map.put("name", deliveryCustInfo.getName());
        map.put("phone_no", deliveryCustInfo.getPhoneNo());
        map.put("mail", deliveryCustInfo.getMail());
        map.put("city", deliveryCustInfo.getCity());
        map.put("zip", deliveryCustInfo.getZip());
        map.put("address", deliveryCustInfo.getAddress());        
        map.put("is_pay", deliveryCustInfo.getIs_pay());
        map.put("pay_money", deliveryCustInfo.getPay_money());
        map.put("invoice_get_type", deliveryCustInfo.getInvoice_get_type());
        map.put("invoice_status", deliveryCustInfo.getInvoice_status());        

        return storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);
    }


    @Override
    public Integer chk_order_is_ti(String storeNo, Integer orderNo) {
        String sql = " select count(*) " +
                "  from delivery_orders a, delivery_order_items b, items c " +
                " where a.order_no = b.order_no " +
                "   and a.platform_no = b.platform_no " +
                "   and b.item_no = c.item_no " +
                "   and c.sup_no in ( select value " +
                "                       from rts_sys_param " +
                "                      where name = 'TIAPP_DC_SUP' )  " +
                "   and c.section_no in ( select value " +
                "                           from rts_sys_param " +
                "                          where name = 'TIAPP_ITM_SECTION' ) " +
                "   and a.order_no = :orderNo ";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);

        try {
            Integer count = storeNamedParameterJdbcTemplate.get(storeNo).queryForObject(sql, map, Integer.class);
            return count;
        } catch (Exception e) {            
            return 0;
        }
    }


    @Override
    public Integer upd_delivery_orders(String storeNo, Integer orderNo, Integer status) {
        String sql = " update delivery_orders set status = :status " +
                " where store_no = :store_no " +
                "   and order_no = :ord_no  ";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("store_no", storeNo);
        map.put("ord_no", orderNo);

        Integer count = storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);

        logger.info("[upd_delivery_orders] " + "main:" + count);

        if (count == 1) {
            String sql1 = " update delivery_order_items set status = 1, type = 1 " +
                    " where order_no = :ord_no ";

            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("ord_no", orderNo);
    
            return storeNamedParameterJdbcTemplate.get(storeNo).update(sql1, map1);
        }else {
            logger.info("[upd_delivery_orders] " + "fail:" + " ord: " + orderNo);
            return 0;
        }
    }


    @Override
    public Integer call_px_shipinfo(Integer storeNo, Integer orderNo, String status) {

        String url = ShipinfoUrl+"/ship_info_forward";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("store_no", storeNo);
        map.put("order_no", orderNo);
        map.put("status", status);

        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("call_px_shipinfo-json: " + mapper.writeValueAsString(map));
            RestTemplate restTemplate = new RestTemplate();
            ReturnMessage returnMessage= restTemplate.postForObject(url, mapper.writeValueAsString(map),ReturnMessage.class);
            
            return returnMessage.getStatusCode();
        }catch (JsonProcessingException e) {                        
            logger.info("call_px_shipinfo-error: " + e.getMessage().toString());
            return 0;
        }    
    }


    @Override
    public boolean proc_packing(String storeNo, Integer orderNo) {
        String rtsApiUrl = "http://"+ StoreIpConfiguraion.getStoreIp(storeNo) +":8080/axis/rt_db_ec.jws?method=px_packing&order_no="+orderNo+"";
        logger.info("[proc_packing] == " + "rtsApiUrl == " + rtsApiUrl);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(rtsApiUrl, String.class);

        logger.info("[proc_packing] == " + "result == " + result.toString());
        
        Integer response = 999;

        if(result.contains("success")){
            response = 0;
            return true;
        }else if(result.contains("is_packing")){
            response = 1001;
        }else{
            response = 999;
        }
        logger.info("[proc_packing] == ["+storeNo+"-"+orderNo+"]" + " response == " + response);        

        return false;
    }


    @Override
    public Integer getOrdNo(String storeNo, String px_order_no) {
        
        String sql = " select order_no from delivery_orders "
                + " where store_no = :storeNo "
                + "   and id_no = :px_order_no ";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeNo", storeNo);
        map.put("px_order_no", px_order_no);

        Integer get_order_no = storeNamedParameterJdbcTemplate.get(storeNo).queryForObject(sql, map, Integer.class);
        logger.info("[getOrdNo] == ["+storeNo+"-"+px_order_no+"]" + " order_no == " + get_order_no);        

        return get_order_no;
    }


    @Override
    public Integer insert_cust_detail(Integer storeNo, Integer orderNo) {
        String sql = " insert into delivery_order_cust_detail  " +
                " (platform_no, order_no, logistic_case_no, logistic_type_dc, logistic_type, status) " +
                "   values (7, :orderNo, :case_no, '宅配通', 0, 1)  ";

        String case_no = String.format("%03d", storeNo)+String.format("%06d", orderNo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        map.put("case_no", case_no);        

        Integer count = storeNamedParameterJdbcTemplate.get(String.format("%02d", storeNo)).update(sql, map);

        logger.info("[insert_cust_detail] " + "main:" + count);

        return count;
    }
}
