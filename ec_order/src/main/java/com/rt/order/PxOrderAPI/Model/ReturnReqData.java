package com.rt.order.PxOrderAPI.Model;

import java.util.List;

public class ReturnReqData {
    
    private OrderReturnData order_return_data;//退貨單主檔
    private List<OrderReturnDfData> order_return_df_data;//部分退貨才有

    public OrderReturnData getOrder_return_data() {
        return order_return_data;
    }
    public void setOrder_return_data(OrderReturnData order_return_data) {
        this.order_return_data = order_return_data;
    }
    public List<OrderReturnDfData> getOrder_return_df_data() {
        return order_return_df_data;
    }
    public void setOrder_return_df_data(List<OrderReturnDfData> order_return_df_data) {
        this.order_return_df_data = order_return_df_data;
    }
    @Override
    public String toString() {
        return "ReturnReqData [order_return_data=" + order_return_data + ", order_return_df_data="
                + order_return_df_data + "]";
    }    
}
