import { Flight } from "src/app/domain/Flight";

export class FlightModel {

    public flights: Array<Flight>;
    public dtblColumns: any[];
    public _selectedFlights: Array<Flight>;

    constructor() {
        this.init();
    }

    public init(): void {
        this.flights = [];
        this.loadColumns();
        this._selectedFlights = [];
    }

    public loadColumns(): void {
        this.dtblColumns = [
            { field: 'externalKey', header: 'External-Key' },
            { field: 'name', header: 'Flight-Name' },
            { field: 'masterFlightNumber', header: 'Master-FlightNumber' },
            { field: 'departureAirport', header: 'Departure-Airport' },
            { field: 'arrivalAirport', header: 'Arrival-Airport' },
            { field: 'departureGate', header: 'Departure-Gate' },
            { field: 'arrivalGate', header: 'Arrival-Gate' },
        ];
    }
}