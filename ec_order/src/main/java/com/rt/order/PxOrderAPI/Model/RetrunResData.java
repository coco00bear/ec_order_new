package com.rt.order.PxOrderAPI.Model;

import java.util.List;

public class RetrunResData {
    private List<OrderReturnDfData>  order_return_df_data;
    private Integer order_uid;//訂單編號
    
    public List<OrderReturnDfData> getOrder_return_df_data() {
        return order_return_df_data;
    }
    public void setOrder_return_df_data(List<OrderReturnDfData> order_return_df_data) {
        this.order_return_df_data = order_return_df_data;
    }
    public Integer getOrder_uid() {
        return order_uid;
    }
    public void setOrder_uid(Integer order_uid) {
        this.order_uid = order_uid;
    }
    
}
