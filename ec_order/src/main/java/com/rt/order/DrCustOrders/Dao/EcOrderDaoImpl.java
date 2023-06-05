package com.rt.order.DrCustOrders.Dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rt.order.DrCustOrders.Model.DrCustFreebie;
import com.rt.order.DrCustOrders.Model.DrCustOrderItems;
import com.rt.order.DrCustOrders.Model.DrCustOrders;
import com.rt.order.DrCustOrders.Model.DrCustTender;
import com.rt.order.DrCustOrders.Model.EcOrderItems;
import com.rt.order.DrCustOrders.Model.EcResponMessage;
import com.rt.order.DrCustOrders.Model.GetItemInfo;
import com.rt.order.DrCustOrders.Model.PrinterMessage;
import com.rt.order.DrCustOrders.Model.RowMapper.GetEcItemsRowMapper;
import com.rt.order.DrCustOrders.Model.RowMapper.GetItemInfoRowMapper;

@PropertySource(value = "classpath:config/host.properties")
@PropertySource(value = "classpath:config/environment.properties")
@Repository
public class EcOrderDaoImpl implements EcOrderDao {

    @Autowired
    @Qualifier("storeJDBC")
    private Map<String, NamedParameterJdbcTemplate> storeNamedParameterJdbcTemplate;

    @Value("${ec.api.packing.url}")
    private String packingUrl;

    //ec-api-path
    @Value("${ec-api-path}")
    private String ecUrl;

    @Value("${ec.url.update_cargo_status}")
    private String cargoUrl;

    Logger logger = LoggerFactory.getLogger("all.order");

    @Override
    public Integer chk_cust_orders(String storeNo, Integer orderNo) {

        String sql = " select nvl(b.status,-1)  " +
                "  from (select 1 as chk from dual " +
                " ) a, " +
                " (select status ,1 as chk " +
                "    from dr_cust_orders " +
                "   where road_no = 99 " +
                "     and store_no = :storeNo " +
                "     and order_no = :orderNo " +
                " )b " +
                " where a.chk=b.chk(+) ";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeNo", storeNo);
        map.put("orderNo", orderNo);

        Integer get_status = storeNamedParameterJdbcTemplate.get(storeNo).queryForObject(sql, map, Integer.class);

        System.out.println(" chk_cust_orders === " + get_status);

        return get_status;
    }

    @Override
    public Integer insertDrCustOrders(String storeNo, DrCustOrders drCustOrders) {

        String sql = "insert into dr_cust_orders (" +
                " road_no, " +
                " order_no, " +
                " store_no, " +
                " client_no, " +
                " uniform, " +
                " ord_date, " +
                " nbr_items, " +
                " date_seq, " +
                " picking_seq, " +
                " print, " +
                " pos_no, " +
                " shift_no, " +
                " pk_no, " +
                " status, " +
                " name, " +
                " phone_no, " +
                " ext_no, " +
                " address, " +
                " sex, " +
                " memo, " +
                " ro_client_no, " +
                " credit, " +
                " carry_time, " +
                " created_date, " +
                " changed_date, " +
                " delivery, " +
                " tender_no, " +
                " creadit_no, " +
                " payment_methods, " +
                " mart_store_no, " +
                " mart_client_no, " +
                " credit_card_no, " +
                " inv_code, " +
                " inv_love, " +
                " inv_cert, " +
                " pk_type," +
                " px_order_no" +
                " ) select " +
                " 99, " +
                " :order_no, " +
                " :store_no, " +
                " :client_no, " +
                " :uniform, " +
                " to_date(:ord_date,'yyyymmddhh24miss'), " +
                " :nbr_items, " +
                " :date_seq, " +
                " :picking_seq, " +
                " :print, " +
                " :pos_no, " +
                " :shift_no, " +
                " :pk_no, " +
                " :status, " +
                " :name, " +
                " :phone_no, " +
                " :ext_no, " +
                " :address, " +
                " :sex, " +
                " :memo, " +
                " :ro_client_no, " +
                " :credit, " +
                " to_date(:carry_time,'yyyymmddhh24miss'), " +
                " to_date(:created_date,'yyyymmddhh24miss'), " +
                " to_date(:changed_date,'yyyymmddhh24miss'), " +
                " :delivery, " +
                " :tender_no, " +
                " :creadit_no, " +
                " :payment_methods, " +
                " :mart_store_no, " +
                " :mart_client_no, " +
                " :credit_card_no, " +
                " :inv_code, " +
                " :inv_love, " +
                " :inv_cert, " +
                " :pk_type, " +
                " :px_order_no " +
                " from dual " +
                " where not exists ( " +
                " select 'x'  " +
                "   from dr_cust_orders_history " +
                "  where road_no = 99 " +
                "    and store_no = :store_no " +
                "    and order_no = :order_no " +
                "    and ord_date = to_date(:created_date,'yyyymmddhh24miss') )";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("road_no", drCustOrders.getRoad_no());
        map.put("order_no", drCustOrders.getOrder_no());
        map.put("store_no", drCustOrders.getStore_no());
        map.put("client_no", drCustOrders.getClient_no());
        map.put("uniform", drCustOrders.getUniform());
        map.put("ord_date", drCustOrders.getOrd_date());
        map.put("nbr_items", drCustOrders.getNbr_items());
        map.put("date_seq", drCustOrders.getDate_seq());
        map.put("picking_seq", drCustOrders.getPicking_seq());
        map.put("print", 0);
        map.put("pos_no", drCustOrders.getPos_no());
        map.put("shift_no", drCustOrders.getShift_no());
        map.put("pk_no", drCustOrders.getPk_no());
        map.put("status", drCustOrders.getStatus());
        map.put("name", drCustOrders.getName());
        map.put("phone_no", drCustOrders.getPhone_no());
        map.put("ext_no", drCustOrders.getExt_no());
        map.put("address", drCustOrders.getAddress());
        map.put("sex", drCustOrders.getSex());
        map.put("memo", drCustOrders.getMemo());
        map.put("ro_client_no", drCustOrders.getClient_no());
        map.put("credit", drCustOrders.getCredit());
        map.put("carry_time", drCustOrders.getCarry_time());
        map.put("created_date", drCustOrders.getCreated_date());
        map.put("changed_date", drCustOrders.getChanged_date());
        map.put("delivery", drCustOrders.getDelivery());
        map.put("tender_no", drCustOrders.getTender_no());
        map.put("creadit_no", drCustOrders.getCreadit_no());
        map.put("payment_methods", drCustOrders.getPayment_methods());
        map.put("mart_store_no", drCustOrders.getMart_store_no());
        map.put("mart_client_no", drCustOrders.getMart_client_no());
        map.put("credit_card_no", drCustOrders.getCredit_card_no());
        map.put("inv_code", drCustOrders.getInv_code());
        map.put("inv_love", drCustOrders.getInv_love());
        map.put("inv_cert", drCustOrders.getInv_cert());
        map.put("pk_type", drCustOrders.getPk_type());
        map.put("px_order_no", drCustOrders.getPx_order_no());

        return storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);
    }

    /* 
    @Override
    public Integer insertDrCustOrderItems(String storeNo, List<DrCustOrderItems> drCustOrderItemsList) {
        String sql = "insert into dr_cust_order_items (" +
                "road_no, " +
                "order_no, " +
                "line_no, " +
                "item_no, " +
                "vat_no, " +
                "promotion_no, " +
                "prom_level, " +
                "qty, " +
                "original_qty, " +
                "amount, " +
                "desc_amnt, " +
                "status, " +
                "action_type, " +
                "action_no, " +
                "module_code, " +
                "module_rule_type, " +
                "module_rule_value, " +
                "disc_action_type, " +
                "disc_action_no, " +
                "disc_rule_type, " +
                "disc_rule_value, " +
                "const_cnt, " +
                "const_amount, " +
                "type, " +
                "bonus_point, " +
                "avg_price, " +
                "item_memo " +
                ") select " +
                "99, " +
                ":ord_no, " +
                ":line_no, " +
                ":item_no, " +
                ":sell_vat, " +
                ":promotion_no, " +
                ":prom_level, " +
                ":qty, " +
                ":qty, " +
                ":amount, " +
                "0, " +
                "0, " +
                ":action_type, " +
                ":action_no, " +
                ":module_code, " +
                ":module_rule_type, " +
                ":module_rule_value, " +
                ":disc_action_type, " +
                ":disc_action_no, " +
                ":disc_rule_type, " +
                ":disc_rule_value, " +
                ":const_cnt, " +
                ":const_amount, " +
                ":ec_item_type, " +
                ":bonus_point, " +
                ":avg_price, " +
                ":item_memo  " +
                " from dual where not exists ( select 'x' from dr_cust_order_items " +
                " where road_no = 99 " +
                "   and order_no = :ord_no and line_no = :line_no )";

        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>(drCustOrderItemsList.size());
        Map<String, Object> map;
        for (int i = 0; i < drCustOrderItemsList.size(); i++) {
            map = new HashMap<String, Object>();
            map.put("ord_no", drCustOrderItemsList.get(i).getOrder_no());
            map.put("line_no", drCustOrderItemsList.get(i).getLine_no());
            map.put("item_no", drCustOrderItemsList.get(i).getItem_no());
            map.put("promotion_no", drCustOrderItemsList.get(i).getPromotion_no());
            map.put("prom_level", drCustOrderItemsList.get(i).getProm_level());
            map.put("qty", drCustOrderItemsList.get(i).getQty());
            map.put("action_type", drCustOrderItemsList.get(i).getAction_type());
            map.put("action_no", drCustOrderItemsList.get(i).getAction_no());
            map.put("module_code", drCustOrderItemsList.get(i).getModule_code());
            map.put("module_rule_type", drCustOrderItemsList.get(i).getModule_rule_type());
            map.put("module_rule_value", drCustOrderItemsList.get(i).getModule_rule_value());
            map.put("disc_action_type", drCustOrderItemsList.get(i).getDisc_action_type());
            map.put("disc_action_no", drCustOrderItemsList.get(i).getDisc_action_no());
            map.put("disc_rule_type", drCustOrderItemsList.get(i).getDisc_rule_type());
            map.put("disc_rule_value", drCustOrderItemsList.get(i).getDisc_rule_value());
            map.put("const_cnt", drCustOrderItemsList.get(i).getConst_cnt());
            map.put("const_amount", drCustOrderItemsList.get(i).getConst_amount());
            map.put("ec_item_type", drCustOrderItemsList.get(i).getType());
            map.put("bonus_point", drCustOrderItemsList.get(i).getBonus_point());
            map.put("avg_price", drCustOrderItemsList.get(i).getAvg_price());
            map.put("item_memo", drCustOrderItemsList.get(i).getItem_memo());

            GetItemInfo getItemInfo = get_untaxed_amount(storeNo, drCustOrderItemsList.get(i).getItem_no(),
                    drCustOrderItemsList.get(i).getAmount());
            map.put("sell_vat", getItemInfo.getVat_no());
            map.put("amount", getItemInfo.getUntaxed_amount());

            maplist.add(map);
        }

        int[] result = storeNamedParameterJdbcTemplate.get(storeNo).batchUpdate(sql,
                maplist.toArray(new Map[maplist.size()]));

        if (result.length > 0) {
            return 1;
        }
        return 0;
    }
    */

    @Override
    public Integer insertDrCustOrderItems(String storeNo, DrCustOrderItems drCustOrderItemsList) {
        String sql = "insert into dr_cust_order_items (" +
                "road_no, " +
                "order_no, " +
                "line_no, " +
                "item_no, " +
                "vat_no, " +
                "promotion_no, " +
                "prom_level, " +
                "qty, " +
                "original_qty, " +
                "amount, " +
                "desc_amnt, " +
                "status, " +
                "action_type, " +
                "action_no, " +
                "module_code, " +
                "module_rule_type, " +
                "module_rule_value, " +
                "disc_action_type, " +
                "disc_action_no, " +
                "disc_rule_type, " +
                "disc_rule_value, " +
                "const_cnt, " +
                "const_amount, " +
                "type, " +
                "bonus_point, " +
                "avg_price, " +
                "item_memo, " +
                "item_uid " +
                ") select " +
                "99, " +
                ":ord_no, " +
                ":line_no, " +
                ":item_no, " +
                ":sell_vat, " +
                ":promotion_no, " +
                ":prom_level, " +
                ":qty, " +
                ":qty, " +
                ":amount, " +
                "0, " +
                "0, " +
                ":action_type, " +
                ":action_no, " +
                ":module_code, " +
                ":module_rule_type, " +
                ":module_rule_value, " +
                ":disc_action_type, " +
                ":disc_action_no, " +
                ":disc_rule_type, " +
                ":disc_rule_value, " +
                ":const_cnt, " +
                ":const_amount, " +
                ":ec_item_type, " +
                ":bonus_point, " +
                ":avg_price, " +
                ":item_memo,  " +
                ":item_uid  " +
                " from dual where not exists ( select 'x' from dr_cust_order_items " +
                " where road_no = 99 " +
                "   and order_no = :ord_no and line_no = :line_no )";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ord_no", drCustOrderItemsList.getOrder_no());
        map.put("line_no", drCustOrderItemsList.getLine_no());
        map.put("item_no", drCustOrderItemsList.getItem_no());
        map.put("promotion_no", drCustOrderItemsList.getPromotion_no());
        map.put("prom_level", drCustOrderItemsList.getProm_level());
        map.put("qty", drCustOrderItemsList.getQty());
        map.put("action_type", drCustOrderItemsList.getAction_type());
        map.put("action_no", drCustOrderItemsList.getAction_no());
        map.put("module_code", drCustOrderItemsList.getModule_code());
        map.put("module_rule_type", drCustOrderItemsList.getModule_rule_type());
        map.put("module_rule_value", drCustOrderItemsList.getModule_rule_value());
        map.put("disc_action_type", drCustOrderItemsList.getDisc_action_type());
        map.put("disc_action_no", drCustOrderItemsList.getDisc_action_no());
        map.put("disc_rule_type", drCustOrderItemsList.getDisc_rule_type());
        map.put("disc_rule_value", drCustOrderItemsList.getDisc_rule_value());
        map.put("const_cnt", drCustOrderItemsList.getConst_cnt());
        map.put("const_amount", drCustOrderItemsList.getConst_amount());
        map.put("ec_item_type", drCustOrderItemsList.getType());
        map.put("bonus_point", drCustOrderItemsList.getBonus_point());
        map.put("avg_price", drCustOrderItemsList.getAvg_price());
        map.put("item_memo", drCustOrderItemsList.getItem_memo());
        
        map.put("item_uid", drCustOrderItemsList.getItem_uid());

        GetItemInfo getItemInfo = get_untaxed_amount(storeNo, drCustOrderItemsList.getItem_no(),
                drCustOrderItemsList.getAmount());
        if(getItemInfo.getVat_no()==-1){
            logger.info("[items not found] " + "storeNo: "+ storeNo + " ordNo: " + drCustOrderItemsList.getOrder_no() + 
            " itemNo: " + drCustOrderItemsList.getItem_no());
        }else{
            map.put("sell_vat", getItemInfo.getVat_no());
            map.put("amount", getItemInfo.getUntaxed_amount());
            logger.info("[新增訂單明細] == storeNo: " + storeNo + " ordNo: " + drCustOrderItemsList.getOrder_no() + 
            " lineNo: " + drCustOrderItemsList.getLine_no() + " itemNo: " + drCustOrderItemsList.getItem_no() + 
            " json_amount: " + drCustOrderItemsList.getAmount() + " vat: " + getItemInfo.getVat_no() + 
            " cal_amount: " + getItemInfo.getUntaxed_amount());
            
            storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);           
        }                
        return 1;
    }

    @Override
    public Integer insertDrCustTender(String storeNo, DrCustTender drCustTender) {
        String sql = " insert into dr_cust_tender (" +
                " order_no, " +
                " pk_type, " +
                " tender_no, " +
                " amount " +
                " ) select " +
                " :ord_no, " +
                " :pk_type, " +
                " :tender_no, " +
                " :amount " +
                " from dual " +
                " where not exists (select 'x' from dr_cust_tender " +
                "                    where order_no = :ord_no and tender_no = :tender_no) ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ord_no", drCustTender.getOrder_no());
        map.put("pk_type", drCustTender.getPk_type());
        map.put("tender_no", drCustTender.getTender_no());
        map.put("amount", drCustTender.getAmount());

        logger.info("[新增訂單抵用卷] == storeNo: " + storeNo + " ordNo: " + drCustTender.getOrder_no() + 
            " tender_no: " + drCustTender.getTender_no() + " amount: " + drCustTender.getAmount() );

        return storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);
    }

    @Override
    public Integer insertDrCustFreebie(String storeNo, List<DrCustFreebie> drCustFreebie) {

        String sql = " insert into dr_cust_freebie (" +
                " order_no, " +
                " pk_type, " +
                " wmkt_uid, " +
                " memo, " +
                " qty " +
                " ) select " +
                " :ord_no, " +
                " :pk_type, " +
                " :wmkt_uid, " +
                " :memo, " +
                " :qty " +
                " from dual " +
                " where not exists (select 'x' from dr_cust_freebie " +
                "                    where order_no = :ord_no and wmkt_uid = :wmkt_uid) ";
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>(drCustFreebie.size());
        Map<String, Object> map;
        for (int i = 0; i < drCustFreebie.size(); i++) {
            map = new HashMap<String, Object>();
            map.put("ord_no", drCustFreebie.get(i).getOrder_no());
            map.put("pk_type", drCustFreebie.get(i).getPk_type());
            map.put("wmkt_uid", drCustFreebie.get(i).getWmkt_uid());
            map.put("memo", drCustFreebie.get(i).getMemo());
            map.put("qty", drCustFreebie.get(i).getQty());

            maplist.add(map);
            logger.info("[新增訂單贈品] == storeNo: " + storeNo + " ordNo: " + drCustFreebie.get(i).getOrder_no() + 
            " wmkt_uid: " + drCustFreebie.get(i).getWmkt_uid());
        }
        int[] result = storeNamedParameterJdbcTemplate.get(storeNo).batchUpdate(sql,
                maplist.toArray(new Map[maplist.size()]));
            
        if (result.length > 0) {
            return 1;
        }
        return 0;
    }

    public GetItemInfo get_untaxed_amount(String storeNo, Integer itemNo, Double amount) {
        GetItemInfo getItemInfo;

        String sql = " select nvl(b.sell_vat,-1) as vat, nvl(round(:amount/(1+(b.perc/100)),2),-1) as untaxed " +
                "   from (select 1 as chk from dual " +
                "   ) A, " +
                "  (select i.sell_vat, v.perc, 1 as chk " +
                "     from items i, vat v " +
                "    where i.sell_vat = v.vat_no " +
                "      and i.item_no = :itemNo " +
                "   )B   " +
                "  where A.chk = B.chk(+) ";
                
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemNo", itemNo);
        map.put("amount", amount);

        getItemInfo = storeNamedParameterJdbcTemplate.get(storeNo).queryForObject(sql, map, new GetItemInfoRowMapper());

        return getItemInfo;
    }

    @Override
    public Integer updateDrCustOrders(String storeNo, DrCustOrders drCustOrders) {
        String sql = " update dr_cust_orders " +
                " set name = :name, " +
                " phone_no = :phone_no, " +
                " ext_no = :ext_no, " +
                " address = :address, " +
                " sex = :sex, " +
                " nbr_items = :nbr_items, " +
                " memo = :memo , " +
                " delivery = :delivery, " +
                " tender_no = :tender_no, " +
                " uniform = :uniform, " +
                " creadit_no =:creadit_no, " +
                " payment_methods =:payment_methods, " +
                " carry_time=to_date(:carry_time,'yyyymmddhh24miss'), " +
                " created_date=to_date(:created_date,'yyyymmddhh24miss'), " +
                " changed_date=to_date(:changed_date,'yyyymmddhh24miss'), " +
                " status  = :status, " +
                " mart_store_no = :mart_store_no, " +
                " mart_client_no = :mart_client_no, " +
                " credit_card_no = :credit_card_no, " +
                " inv_code = :inv_code, " +
                " inv_love = trim(:inv_love), " +
                " inv_cert = :inv_cert, " +
                " pk_type = :pk_type " +
                " where road_no = 99 " +
                "   and store_no = :store_no " +
                "   and order_no = :order_no ";

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("order_no", drCustOrders.getOrder_no());
        map.put("store_no", drCustOrders.getStore_no());
        map.put("name", drCustOrders.getName());
        map.put("phone_no", drCustOrders.getPhone_no());
        map.put("ext_no", drCustOrders.getExt_no());
        map.put("address", drCustOrders.getAddress());
        map.put("sex", drCustOrders.getSex());
        map.put("nbr_items", drCustOrders.getNbr_items());
        map.put("memo", drCustOrders.getMemo());
        map.put("delivery", drCustOrders.getDelivery());
        map.put("tender_no", drCustOrders.getTender_no());
        map.put("uniform", drCustOrders.getUniform());
        map.put("creadit_no", drCustOrders.getCreadit_no());
        map.put("payment_methods", drCustOrders.getPayment_methods());
        map.put("carry_time", drCustOrders.getCarry_time());
        map.put("created_date", drCustOrders.getCreated_date());
        map.put("changed_date", drCustOrders.getChanged_date());
        map.put("status", drCustOrders.getStatus());
        map.put("mart_store_no", drCustOrders.getMart_store_no());
        map.put("mart_client_no", drCustOrders.getMart_client_no());
        map.put("credit_card_no", drCustOrders.getCredit_card_no());
        map.put("inv_code", drCustOrders.getInv_code());
        map.put("inv_love", drCustOrders.getInv_love());
        map.put("inv_cert", drCustOrders.getInv_cert());
        map.put("pk_type", drCustOrders.getPk_type());

        return storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);
    }

    @Override
    public Integer deleteDrCustOrdersHistory(String storeNo, DrCustOrders drCustOrders) {

        String sql = " delete dr_cust_orders_history  " +
                " where road_no = 99 " +
                "   and store_no = :store_no " +
                "   and order_no = :ord_no  " +
                "   and status   = 6  " +
                "   and ord_date = to_date(:created_date,'yyyymmddhh24miss') ";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("store_no", drCustOrders.getStore_no());
        map.put("ord_no", drCustOrders.getOrder_no());
        map.put("created_date", drCustOrders.getCreated_date());

        return storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);
    }

    @Override
    public Integer insertDailyLog(String storeNo, Integer orderNo) {

        String sql = " INSERT INTO DAILY_LOG (TNAME,VALUE) " +
                " SELECT 'CUST_ORDERS', " +
                "        TO_CHAR(:ord_no) " +
                "   FROM SYS.DUAL " +
                " WHERE NOT EXISTS (SELECT 'X' FROM DAILY_LOG " +
                "                    WHERE TNAME = 'CUST_ORDERS' " +
                "                      AND VALUE = TO_CHAR(:ord_no))  ";

        Map<String, Object> map = new HashMap<String, Object>(); 
        map.put("ord_no", orderNo);

        return storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);
    }

    @Override
    public Integer chk_cust_items(String storeNo, Integer orderNo, Integer line_no) {

        String sql = " select count(*) from dr_cust_order_items " +
                " where road_no = 99 " +
                "   and order_no = :ord_no and line_no = :line_no ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ord_no", orderNo);
        map.put("line_no", line_no);

        return storeNamedParameterJdbcTemplate.get(storeNo).queryForObject(sql, map, Integer.class);
    }

    @Override
    public Integer api_SetOrderStatus19(Integer OrderUid) {

        String url = "http://10.240.0.2/RTMart/index.php/rtmart_pda/TEST/RT_PDAPickup/SetOrderStatus19";
        // String url = "http://10.240.0.2/RTMart/index.php/rtmart_pda/TEST/RT_PDAPickup/SetOrderStatus19?OrderUid=500580813";

        logger.info("[api_SetOrderStatus19] - url: "+ url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization",
        "r2XuQe/v4kZqdcS1AdGCpE11hHKZM7/tx2XTq1KZyoo6VWxSTllYSjBSVU5RY205cVpXTjBNREF4");

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("OrderUid", "500" + String.valueOf(OrderUid));

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params, headers);

        logger.info("[api_SetOrderStatus19] - entity: "+ entity.getHeaders() + " body: " + entity.getBody());

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<EcResponMessage> respoHttpEntity = restTemplate.postForEntity(url, entity, EcResponMessage.class);
        
        try {
            logger.info("[api_SetOrderStatus19] - respoHttpEntity: "+ new ObjectMapper().writeValueAsString(respoHttpEntity.getBody()));
        } catch (JsonProcessingException e) {            
            e.printStackTrace();
            logger.info("[api_SetOrderStatus19] - fail: "+ e.getMessage());
        }
        return respoHttpEntity.getBody().getStatus();
    }

    @Override
    public Integer api_box_v2(String storeNo, Integer OrderUid) {

        EcOrderItems ecOrderItems;

        String sql = " select item_no, original_qty-qty as qty, vat_no, nvl(item_uid,0) as item_uid" +
                "  from dr_cust_order_items " +
                " where order_no = :ord_no ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ord_no", OrderUid);

        ecOrderItems = storeNamedParameterJdbcTemplate.get(storeNo).queryForObject(sql, map, new GetEcItemsRowMapper());

        List<EcOrderItems> list = new ArrayList<EcOrderItems>();
        list.add(ecOrderItems);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("data", list);

        ObjectMapper mapper = new ObjectMapper();

        try {
            // System.out.println("ecOrderItems: " + mapper.writeValueAsString(map1));
            String url = ecUrl;
            logger.info("[api_box_v2] - url: "+ url);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            headers.add("Authorization",
                    "r2XuQe/v4kZqdcS1AdGCpE11hHKZM7/tx2XTq1KZyoo6VWxSTllYSjBSVU5RY205cVpXTjBNREF4");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("OrderUid", "500" + String.valueOf(OrderUid));
            params.add("Boxes", "1");
            params.add("JsonData", mapper.writeValueAsString(map1));

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);            
            logger.info("[api_box_v2] - entity: "+ entity.getHeaders() + " body: " +entity.getBody());

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<EcResponMessage> respoHttpEntity = restTemplate.postForEntity(url, entity,
                    EcResponMessage.class);

            //logger.info("[api_box_v2] - succcess: " + respoHttpEntity.getBody().getMessage() + respoHttpEntity.getBody().getStatus());           
            return respoHttpEntity.getBody().getStatus();
        } catch (JsonProcessingException e) {            
            e.printStackTrace();
            logger.info("[api_box_v2] - fail: " + e.getMessage());
            return 1;
        }        
    }

    @Override
    public Integer print_invoice(String storeNo, Integer orderNo) {
        String url = packingUrl;
        logger.info("[print_invoice]: " + url + " storeNo: "+storeNo);

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("storeNo", storeNo);
        params.add("order_no",orderNo+"");
        params.add("uniform", "0");
        params.add("printer", "ecinv");

        RestTemplate restTemplate = new RestTemplate();
        PrinterMessage printerMessage = restTemplate.postForObject(url, params, PrinterMessage.class);

        logger.info("print_invoice: "+ printerMessage.getStatusCode());

        Integer code = Integer.parseInt(printerMessage.getStatusCode());

        return code;
    }

    @Override
    public Integer chk_order_is_ti(String storeNo, Integer orderNo) {

        String sql = " select count(*) " +
                "  from dr_cust_orders a, dr_cust_order_items b, items c " +
                " where a.order_no = b.order_no " +
                "   and a.road_no = b.road_no " +
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
    public Integer upd_dr_cust_orders(String storeNo, Integer orderNo) {

        String sql = " update dr_cust_orders set status = 2 " +
                " where store_no = :store_no " +
                "   and order_no = :ord_no  ";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("store_no", storeNo);
        map.put("ord_no", orderNo);

        Integer count = storeNamedParameterJdbcTemplate.get(storeNo).update(sql, map);

        logger.info("[upd_dr_cust_orders] " + "main:" + count);

        if (count == 1) {
            String sql1 = " update dr_cust_order_items set status = 1, type = 1 " +
                    " where order_no = :ord_no ";

            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("ord_no", orderNo);
    
            return storeNamedParameterJdbcTemplate.get(storeNo).update(sql1, map1);
        }else {
            logger.info("[upd_dr_cust_orders] " + "fail:" + " ord: " + orderNo);
            return 0;
        }
    }

    @Override
    public Integer update_cargo_status(Integer storeNo, Integer orderNo) {
        String url = cargoUrl;

        // {
        //     "order_uid": 500670076,
        //     "state": 22,
        //     "logistic_name": "宅配通",
        //     "tracking_number": "123456",
        //     "status_change_date": "2023-04-04 15:50:00"
        //   }
        // {
        //     "isSuccess": false,
        //     "statusCode": "400",
        //     "message": "更新22貨態失敗,更新物流資訊有誤"
        //   }

        //取得當下日期
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));
                

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_uid", Integer.parseInt("500"+String.valueOf(orderNo)));
        map.put("state", 22);
        map.put("logistic_name", "pelican");
        map.put("tracking_number", String.format("%03d",storeNo)+String.valueOf(orderNo));
        map.put("status_change_date", dateFormat.format(date));
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("update_cargo_status-json: " + mapper.writeValueAsString(map));
            RestTemplate restTemplate = new RestTemplate();
            EcResponMessage ecResponMessage = restTemplate.postForObject(url, mapper.writeValueAsString(map),EcResponMessage.class);
            logger.info("ecResponMessage: " + ecResponMessage.getStatusCode() + " message: " + ecResponMessage.getMessage());
            return Integer.parseInt(ecResponMessage.getStatusCode());
        } catch (JsonProcessingException e) {                        
            logger.info("update_cargo_status-error: " + e.getMessage().toString());
            return 0;
        }        
    }
}
