package com.rt.order.PxOrderAPI.Dao;

import org.springframework.jdbc.core.RowMapper;

import com.rt.order.PxOrderAPI.Model.OrderReturnDfData;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("rawtypes")
public class OrderReturnDfDataRowMapper implements RowMapper {
    @Override
    public OrderReturnDfData mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderReturnDfData orderReturnDfDataList = new OrderReturnDfData();
        orderReturnDfDataList.setOrder_return_df_uid(resultSet.getInt("RETURN_ORDER_NO"));
        orderReturnDfDataList.setItem_no(resultSet.getInt("item_no"));
        orderReturnDfDataList.setTotal_price(resultSet.getInt("DELIVERY_QTY"));
        orderReturnDfDataList.setDelivery_qty(resultSet.getInt("return_qty"));
        orderReturnDfDataList.setReturn_qty(resultSet.getInt("total_price"));

        return orderReturnDfDataList;
    }

}
