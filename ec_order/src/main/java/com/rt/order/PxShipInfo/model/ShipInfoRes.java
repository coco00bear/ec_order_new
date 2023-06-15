package com.rt.order.PxShipInfo.model;

import java.util.List;

public class ShipInfoRes {
    
//     {
//     "ship_infos": [
//         {
//             "order_uid": 500580102,
//             "prod_uid": 41464,
//             "status": 130,
//             "reply_time": "2022-09-02T15:30:00",
//             "logistic_name": "宅配通",
//             "tracking_number": "999999999999"
//         },
//         {
//             "order_uid": 500580102,
//             "prod_uid": 60364,
//             "status": 130,
//             "reply_time": "2022-09-02T15:30:00",
//             "logistic_name": "宅配通",
//             "tracking_number": "999999999999"
//         }
//     ]
// }

    private List<ShipInfoResInfo> ship_infos;

    public List<ShipInfoResInfo> getShip_infos() {
        return ship_infos;
    }

    public void setShip_infos(List<ShipInfoResInfo> ship_infos) {
        this.ship_infos = ship_infos;
    }

    @Override
    public String toString() {
        return "ShipInfoRes [ship_infos=" + ship_infos + "]";
    }
}
