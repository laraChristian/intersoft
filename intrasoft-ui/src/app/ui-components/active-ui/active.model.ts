import { Active } from "../../domain/active";

export class ActiveModel {

    _model: Array<Active>;
    public dtblColumns: any[];

    constructor() {
        this.init();
    }

    public init(): void {
        this._model = [];
        this.loadColumns();
    }

    private loadColumns(): void {
        this.dtblColumns = [
            { field: '_id', header: 'Id' },
            { field: 'name', header: 'Name' },
            { field: 'description', header: 'Description' },
            { field: 'serial', header: 'Serial' },
            { field: 'price', header: 'Price' },
            { field: 'dateOfPurchase', header: 'Date-Purchase' },
            { field: 'dischargeDate', header: 'Discharge-Date' },
        ];
    }

}