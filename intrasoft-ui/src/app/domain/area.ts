import { City } from "./city";
import { Employee } from "./employee";
import { Active } from "./active";
import { Inventory } from "./inventory";

export class Area{

    _id: String;
    name: String;
    city: City;
    employees: Array<Employee>;
    actives: Array<Active>;
    inventories: Array<Inventory>;

}