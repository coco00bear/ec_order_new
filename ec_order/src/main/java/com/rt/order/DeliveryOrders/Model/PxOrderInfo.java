package com.rt.order.DeliveryOrders.Model;

public class PxOrderInfo {
    // {
    //     "store_no": 10,
    //     "logistic_carriage": 0,
    //     "data": {
    //         "orders_data": {
    //             "px_order_no": "RX23042410258040",
    //             "order_addressee": "kuma",
    //             "order_email": "ecpx@gmail.com",
    //             "order_telcellphone": "0912345678",
    //             "order_city": "新北市",
    //             "order_zip": "220",
    //             "order_address": "板橋區三民路二段144號888樓",
    //             "pay_money_ssl": 24900,
    //             "order_date": "2023-04-24 16:33:32",
    //             "order_remarks": "消費者備註:電梯(無)本人簽收",
    //             "recycle": "Y",
    //             "collection_amount": 300
    //         },
    //         "order_items_data": [
    //             {
    //                 "item_no": 316332,
    //                 "item_name": "東元變頻豪華雙門冰箱-480L",
    //                 "sell_price": 24900,
    //                 "qty": 1
    //             }
    //         ]
    //     }
    // }

    private Integer store_no;
    private Integer logistic_carriage;
    private data data;

    public Integer getStore_no() {
        return store_no;
    }

    public void setStore_no(Integer store_no) {
        this.store_no = store_no;
    }

    public Integer getLogistic_carriage() {
        return logistic_carriage;
    }

    public void setLogistic_carriage(Integer logistic_carriage) {
        this.logistic_carriage = logistic_carriage;
    }

    public data getData() {
        return data;
    }

    public void setData(data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PxOrderInfo [store_no=" + store_no + ", logistic_carriage=" + logistic_carriage + ", data=" + data
                + "]";
    }
}
