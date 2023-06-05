package com.rt.order.PxOrderAPI.Model;


public class OrderReturnDfData {//request的order_return_df_data

    private Integer order_return_df_uid;//退貨明細編號
    private Integer prod_uid;//商品編號
    private Integer order_df_price;//訂單實際售價
    private Integer delivery_qty;//出貨數量
    private Integer return_qty;//退貨數量

    public Integer getOrder_return_df_uid() {
        return order_return_df_uid;
    }
    public void setOrder_return_df_uid(Integer order_return_df_uid) {
        this.order_return_df_uid = order_return_df_uid;
    }
    public Integer getProd_uid() {
        return prod_uid;
    }
    public void setProd_uid(Integer prod_uid) {
        this.prod_uid = prod_uid;
    }
    public Integer getOrder_df_price() {
        return order_df_price;
    }
    public void setOrder_df_price(Integer order_df_price) {
        this.order_df_price = order_df_price;
    }
    public Integer getDelivery_qty() {
        return delivery_qty;
    }
    public void setDelivery_qty(Integer delivery_qty) {
        this.delivery_qty = delivery_qty;
    }
    public Integer getReturn_qty() {
        return return_qty;
    }
    public void setReturn_qty(Integer return_qty) {
        this.return_qty = return_qty;
    }
}
