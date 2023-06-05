package com.rt.order.DrCustOrders.Model;

public class EcOrderItems {
    //OrderUid ==== 676221== 
    //Boxes===1== 
    //json === {"data":[{"item_no":"712950","vat":"1","qty":"0","order_df_uid":"3657913"}]}

    private String item_no;
	private String vat;
	private String qty;
    private String order_df_uid;

    public String getOrder_df_uid() {
        return order_df_uid;
    }
    public void setOrder_df_uid(String order_df_uid) {
        this.order_df_uid = order_df_uid;
    }
    public String getItem_no() {
        return item_no;
    }
    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }
    public String getVat() {
        return vat;
    }
    public void setVat(String vat) {
        this.vat = vat;
    }
    public String getQty() {
        return qty;
    }
    public void setQty(String qty) {
        this.qty = qty;
    }

}
