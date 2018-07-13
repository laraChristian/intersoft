import { Component, OnInit } from '@angular/core';
import { FlightModel } from 'src/app/ui-components/active-ui/flights.model';
import { FlightsApiService } from 'src/app/infraestructure/services/flights-api.service';
import { Flight } from 'src/app/domain/Flight';
import { ActiveModel } from './active.model';
import { ActiveApiService } from '../../infraestructure/services/active-api.service';

@Component({
  selector: 'active-ui',
  templateUrl: './active-ui.component.html',
  styleUrls: ['./active-ui.component.css'],
  providers: [FlightsApiService, ActiveApiService]
})
export class ActiveUiComponent implements OnInit {

  private model: FlightModel;
  private _activeModel: ActiveModel;

  constructor(private flightService: FlightsApiService, private activeService: ActiveApiService) {
    this.model = new FlightModel();
    this._activeModel = new ActiveModel();
  }

  ngOnInit() {
    this.listActives();
  }

  public listActives(): void {
    console.log('start -- list-actives method');
    this.activeService.listActives().forEach(resp => {
      this._activeModel._model = resp['actives'];
      this._activeModel._model.forEach(actv => {
        actv.dateOfPurchase = new Date(actv.dateOfPurchase);
        actv.dischargeDate = new Date(actv.dischargeDate);
      });
    });
  }

}
