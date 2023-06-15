package com.rt.order.PxShipInfo.Dao;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rt.order.PxShipInfo.Dao.rowMapper.OrderDetailRowMapper;
import com.rt.order.PxShipInfo.model.ForwardParam;
import com.rt.order.PxShipInfo.model.OrderDetail;
import com.rt.order.PxShipInfo.model.ReverseParam;
import com.rt.order.PxShipInfo.model.ShipInfoRes;
import com.rt.order.utility.model.ReturnMessage;

@PropertySource(value = "classpath:config/host.properties")
@Repository
public class ShipInfoDaoImpl implements ShipInfoDao {

    @Autowired
    @Qualifier("storeJDBC")
    private Map<String, NamedParameterJdbcTemplate> storeNamedParameterJdbcTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Value("${px.shipinfo.url}")
    private String pxShipInfoUrl;

    Logger shipInfoLog = LoggerFactory.getLogger("pxship.detail");


    @Override
    public Integer chk_order(Integer storeNo, Integer orderNo) {
        shipInfoLog.debug("[chk_order - param]: " + " storeNo: "+ storeNo + " orderNo: "+ orderNo);
        String sql = " select nvl(b.status,-1) as stat "
                + " from (select 1 as chk from dual) a, "
                + " (select status, 1 as chk from delivery_orders where platform_no = 7 and order_no = :order_no) b "
                + " where a.chk=b.chk(+) ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_no", orderNo);
        return storeNamedParameterJdbcTemplate.get(String.format("%02d",storeNo)).queryForObject(sql, map, Integer.class);
    }

    @Override
    public List<OrderDetail> get_order_data(ForwardParam forwardParam) {
        shipInfoLog.debug("[get_order_data - param]: " + forwardParam.toString());

        String sql = " select a.order_no as order_no, b.item_no as item_no, b.type as type, to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as reply, c.logistic_type_dc as logistic_name, c.logistic_case_no as case_no"
                + "  from delivery_orders a, delivery_order_items b, delivery_order_cust_detail c "
                + " where a.platform_no = b.platform_no "
                + "   and a.order_no = b.order_no "
                + "   and a.platform_no = c.platform_no(+) "
                + "   and a.order_no = c.order_no(+) "
                + "   and a.platform_no = 7 "
                + "   and a.order_no = :order_no ";
        if (forwardParam.getStatus().equals("delivered") || forwardParam.getStatus().equals("arrived")) {
            sql += " and b.type = 1 ";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_no", forwardParam.getOrder_no());
        try {
            List<OrderDetail> list = storeNamedParameterJdbcTemplate
                    .get(String.format("%02d", forwardParam.getStore_no())).query(sql, map, new OrderDetailRowMapper());            
            shipInfoLog.debug("[get_order_data - list]: " + list.toString());
            return list;
        } catch (Exception e) {
            shipInfoLog.debug("[get_order_data - fail]: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Integer chk_return_order(Integer storeNo, Integer returnNo) {
        shipInfoLog.debug("[chk_return_order]: "+ " storeNo: " + storeNo + " returnNo: " + returnNo);
        String sql = " select status "
                + " from delivery_order_return "
                + " where platform_no = 7 "
                + " and return_order_no = :returnNo ";               
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("returnNo", returnNo);
        try{
            Integer get_return_status = storeNamedParameterJdbcTemplate.get(String.format("%02d",storeNo)).queryForObject(sql, map, Integer.class);
            shipInfoLog.debug("[chk_return_order - get_return_status]: " + get_return_status);
            return get_return_status;
        }catch(Exception e){
            shipInfoLog.debug("[chk_return_order - fail]: " + e.getMessage());
            return -1;
        }        
    }

    @Override
    public Integer chk_refund_order(Integer storeNo,  Integer refundNo) {
        shipInfoLog.debug("[chk_refund_order]: "+ " storeNo: " + storeNo + " refundNo: " + refundNo);
        String sql = " select b.status "
                + " from delivery_order_return a, delivery_order_refund_data b "
                + " where a.platform_no = b.platform_no "
                + " and a.order_no = b.order_no "
                + " and a.refund_no = b.refund_no "
                + " and a.platform_no = 7 "
                + " and a.refund_no = :refundNo ";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("refundNo", refundNo);
        try{
            Integer get_refund_status = storeNamedParameterJdbcTemplate.get(String.format("%02d",storeNo)).queryForObject(sql, map, Integer.class);
            shipInfoLog.debug("[chk_refund_order - get_refund_status]: " + get_refund_status);
            return get_refund_status;
        }catch(Exception e){
            shipInfoLog.debug("[chk_refund_order - fail]: " + e.getMessage());
            return -1;
        }        
    }

    @Override
    public List<OrderDetail> get_return_data(ReverseParam reverseParam) {
        String sql = " select a.order_no as order_no, b.item_no as item_no, 0 as type, to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') as reply "
                + " case when a.logistic_type = 1 then '嘉禮大榮' else '宅配通' end as logistic_name , a.logistic_case_no as case_no "
                + "  from delivery_order_return a, delivery_order_return_items b "
                + " where a.platform_no = b.platform_no "
                + "   and a.order_no = b.order_no "
                + "   and a.return_order_no = b.return_order_no "
                + "   and a.platform_no = 7 ";                
        shipInfoLog.debug("[get_return_data - param]: "+ reverseParam.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        if (reverseParam.getStatus().equals("return_process")) {
            sql += " and a.return_order_no = :return_order_no ";
            map.put("return_order_no", reverseParam.getReturn_order_no());
            shipInfoLog.debug("[get_return_data]: "+ "return_process: 退貨單號: "+ reverseParam.getReturn_order_no());
        }else{
            sql += " and a.refund_no = :refund_no ";
            map.put("refund_no", reverseParam.getRefund_no());
            shipInfoLog.debug("[get_return_data]: "+ "return_finish: 退款單號: "+ reverseParam.getRefund_no());
        }
        try {
            List<OrderDetail> list = storeNamedParameterJdbcTemplate
                    .get(String.format("%02d", reverseParam.getStore_no())).query(sql, map, new OrderDetailRowMapper());
            shipInfoLog.debug("[get_return_data - response]: " + list.toString());
            return list;
        } catch (Exception e) {
            shipInfoLog.debug("[get_return_data - fail]: "+ e.getMessage());
            return null;
        }
    }

    @Override
    public ReturnMessage call_px_api(ShipInfoRes shipInfoRes) {
        String url = pxShipInfoUrl;
        shipInfoLog.debug("[call_px_api]: "+ url);
        shipInfoLog.debug("[call_px_api - param]: "+ shipInfoRes.toString());

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            shipInfoLog.debug("[call_px_api - json]: "+ objectMapper.writeValueAsString(shipInfoRes));
            ReturnMessage returnMessage = restTemplate.postForObject(url, objectMapper.writeValueAsString(shipInfoRes), ReturnMessage.class);
            shipInfoLog.debug("[call_px_api - returnMessage]: "+ returnMessage.toString());
            return returnMessage;
        }catch(Exception e){
            ReturnMessage returnMessage = new ReturnMessage();
            returnMessage.setStatusCode(9999);
            returnMessage.setMessage("未定義異常");
            shipInfoLog.debug("[call_px_api - fail]: "+ e.getMessage());
            return returnMessage;
        }
    }   
}
