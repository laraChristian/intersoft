import { Area } from "./area";

export class Inventory {

    _id: String;
    number: number;
    creationDate: Date;
    area: Area;
    inventories: Array<Inventory>;

}