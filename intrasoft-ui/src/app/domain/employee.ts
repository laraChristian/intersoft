import { Area } from "./area";
import { Active } from "./active";

export class Employee{

    _id: String;
    firstName: String;
    lastName: String;
    identification: String;
    area: Area;
    actives: Array<Active>;
}