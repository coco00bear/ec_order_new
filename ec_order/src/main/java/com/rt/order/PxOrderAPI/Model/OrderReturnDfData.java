package com.rt.order.PxOrderAPI.Model;


public class OrderReturnDfData {//request的order_return_df_data

    private Integer order_return_df_uid;//退貨明細編號
    private Integer item_no;//商品編號
    private Integer total_price;//訂單實際售價
    private Integer delivery_qty;//出貨數量
    private Integer return_qty;//退貨數量
    public Integer getOrder_return_df_uid() {
        return order_return_df_uid;
    }
    public void setOrder_return_df_uid(Integer order_return_df_uid) {
        this.order_return_df_uid = order_return_df_uid;
    }
    public Integer getItem_no() {
        return item_no;
    }
    public void setItem_no(Integer item_no) {
        this.item_no = item_no;
    }
    public Integer getTotal_price() {
        return total_price;
    }
    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
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
    @Override
    public String toString() {
        return "OrderReturnDfData [order_return_df_uid=" + order_return_df_uid + ", item_no=" + item_no
                + ", total_price=" + total_price + ", delivery_qty=" + delivery_qty + ", return_qty=" + return_qty
                + "]";
    }
}
