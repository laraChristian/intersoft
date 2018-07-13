import { Status } from "./status";
import { Type } from "./type";
import { Area } from "./area";
import { Inventory } from "./inventory";

export class Active {

    _id: String;
    name: String;
    description: String;
    serial: String;
    weight: number;
    width: number;
    height: number;
    length: number;
    price: number;
    dateOfPurchase: Date;
    dischargeDate: Date;
    status: Status;
    color: String;
    type: Type;
    area: Area;
    inventory: Inventory;


}