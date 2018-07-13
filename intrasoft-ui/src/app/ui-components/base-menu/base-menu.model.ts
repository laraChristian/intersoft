import { MenuItem, MenuItemContent } from "primeng/primeng";

export class BaseMenuModel{

    public _items: MenuItem[];
 
    constructor(){
        this.init();
    }

    public init(): void {
        this._items = [];
    }

}