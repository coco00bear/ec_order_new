package com.rt.order.DrCustOrders.Model.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rt.order.DrCustOrders.Model.EcOrderItems;


public class GetEcItemsRowMapper implements RowMapper<EcOrderItems>{

    @Override
    public EcOrderItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        EcOrderItems getEcOrderItems = new EcOrderItems();
        getEcOrderItems.setItem_no(rs.getString("item_no"));
        getEcOrderItems.setVat(rs.getString("vat_no"));
        getEcOrderItems.setQty(rs.getString("qty"));
        getEcOrderItems.setOrder_df_uid(rs.getString("item_uid"));
        return getEcOrderItems;
    }
    
}
