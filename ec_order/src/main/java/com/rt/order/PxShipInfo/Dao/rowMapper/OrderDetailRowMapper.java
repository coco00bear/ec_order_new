package com.rt.order.PxShipInfo.Dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rt.order.PxShipInfo.model.OrderDetail;

@SuppressWarnings("rawtypes")
public class OrderDetailRowMapper implements RowMapper {
    @Override
    public OrderDetail mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder_no(resultSet.getInt("order_no"));
        orderDetail.setItem_no(resultSet.getInt("item_no"));
        orderDetail.setType(resultSet.getInt("type"));
        orderDetail.setReply_time(resultSet.getString("reply"));
        orderDetail.setLogistic_name(resultSet.getString("logistic_name"));
        orderDetail.setTracking_number(resultSet.getString("case_no"));
        System.out.println("OrderDetailRowMapper: "+ orderDetail.toString());
        return orderDetail;
    }
    
}
