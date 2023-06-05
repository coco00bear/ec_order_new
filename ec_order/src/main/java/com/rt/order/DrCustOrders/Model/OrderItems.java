package com.rt.order.DrCustOrders.Model;

public class OrderItems {
    /*
     * "items": [
     * {
     * "store_no": 10,
     * "order_uid": 646042,
     * "line_no": 1,
     * "item_no": 801615,
     * "promotion_no": 0,
     * "prom_level": 0,
     * "qty": 1,
     * "amount": 165,
     * "action_type": "",
     * "action_no": 0,
     * "module_code": 0,
     * "module_rule_type": "",
     * "module_rule_value": 0,
     * "disc_action_type": "",
     * "disc_action_no": 0,
     * "disc_rule_type": "",
     * "disc_rule_value": 0,
     * "disc_cnt": 0,
     * "disc_amnt": 0,
     * "ec_item_type": 0,
     * "bonus_point": 0,
     * "avg_price": 165,
     * "prod_memo": ""
     * }
     * ]
     */
    private Integer store_no;
    private Integer order_uid;
    private Integer line_no;
    private Integer item_no;
    private Integer promotion_no;
    private Integer prom_level;
    private Integer qty;
    private Double amount;
    private String action_type;
    private Integer action_no;
    private Integer module_code;
    private String module_rule_type;
    private Integer module_rule_value;
    private String disc_action_type;
    private Integer disc_action_no;
    private String disc_rule_type;
    private Integer disc_rule_value;
    private Integer disc_cnt;
    private Integer disc_amnt;
    private Integer ec_item_type;
    private Integer bonus_point;
    private Double avg_price;
    private String prod_memo;
    
    private Integer order_df_uid;

    public Integer getOrder_df_uid() {
        return order_df_uid;
    }

    public void setOrder_df_uid(Integer order_df_uid) {
        this.order_df_uid = order_df_uid;
    }

    public Integer getStore_no() {
        return store_no;
    }

    public void setStore_no(Integer store_no) {
        this.store_no = store_no;
    }

    public Integer getOrder_uid() {
        return order_uid;
    }

    public void setOrder_uid(Integer order_uid) {
        this.order_uid = order_uid;
    }

    public Integer getLine_no() {
        return line_no;
    }

    public void setLine_no(Integer line_no) {
        this.line_no = line_no;
    }

    public Integer getItem_no() {
        return item_no;
    }

    public void setItem_no(Integer item_no) {
        this.item_no = item_no;
    }

    public Integer getPromotion_no() {
        return promotion_no;
    }

    public void setPromotion_no(Integer promotion_no) {
        this.promotion_no = promotion_no;
    }

    public Integer getProm_level() {
        return prom_level;
    }

    public void setProm_level(Integer prom_level) {
        this.prom_level = prom_level;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public Integer getAction_no() {
        return action_no;
    }

    public void setAction_no(Integer action_no) {
        this.action_no = action_no;
    }

    public Integer getModule_code() {
        return module_code;
    }

    public void setModule_code(Integer module_code) {
        this.module_code = module_code;
    }

    public String getModule_rule_type() {
        return module_rule_type;
    }

    public void setModule_rule_type(String module_rule_type) {
        this.module_rule_type = module_rule_type;
    }

    public Integer getModule_rule_value() {
        return module_rule_value;
    }

    public void setModule_rule_value(Integer module_rule_value) {
        this.module_rule_value = module_rule_value;
    }

    public String getDisc_action_type() {
        return disc_action_type;
    }

    public void setDisc_action_type(String disc_action_type) {
        this.disc_action_type = disc_action_type;
    }

    public Integer getDisc_action_no() {
        return disc_action_no;
    }

    public void setDisc_action_no(Integer disc_action_no) {
        this.disc_action_no = disc_action_no;
    }

    public String getDisc_rule_type() {
        return disc_rule_type;
    }

    public void setDisc_rule_type(String disc_rule_type) {
        this.disc_rule_type = disc_rule_type;
    }

    public Integer getDisc_rule_value() {
        return disc_rule_value;
    }

    public void setDisc_rule_value(Integer disc_rule_value) {
        this.disc_rule_value = disc_rule_value;
    }

    public Integer getDisc_cnt() {
        return disc_cnt;
    }

    public void setDisc_cnt(Integer disc_cnt) {
        this.disc_cnt = disc_cnt;
    }

    public Integer getDisc_amnt() {
        return disc_amnt;
    }

    public void setDisc_amnt(Integer disc_amnt) {
        this.disc_amnt = disc_amnt;
    }

    public Integer getEc_item_type() {
        return ec_item_type;
    }

    public void setEc_item_type(Integer ec_item_type) {
        this.ec_item_type = ec_item_type;
    }

    public Integer getBonus_point() {
        return bonus_point;
    }

    public void setBonus_point(Integer bonus_point) {
        this.bonus_point = bonus_point;
    }

    public Double getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(Double avg_price) {
        this.avg_price = avg_price;
    }

    public String getProd_memo() {
        return prod_memo;
    }

    public void setProd_memo(String prod_memo) {
        this.prod_memo = prod_memo;
    }
}
