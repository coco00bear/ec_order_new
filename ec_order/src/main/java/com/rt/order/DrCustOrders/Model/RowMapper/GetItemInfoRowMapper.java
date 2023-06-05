package com.rt.order.DrCustOrders.Model.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rt.order.DrCustOrders.Model.GetItemInfo;

public class GetItemInfoRowMapper implements RowMapper<GetItemInfo>{

    @Override
    public GetItemInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        GetItemInfo getItemInfo = new GetItemInfo();
        getItemInfo.setVat_no(rs.getInt("vat"));
        getItemInfo.setUntaxed_amount(rs.getDouble("untaxed"));
        return getItemInfo;
    }
    
}
