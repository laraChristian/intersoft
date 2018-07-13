import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions} from '@angular/http'
import { ApiUtilities } from 'src/app/infraestructure/services/api.utilities';
import { Observable} from 'rxjs';
import { map, filter, catchError, mergeMap } from 'rxjs/operators';
import { element } from 'protractor';
import { Flight } from 'src/app/domain/Flight';


@Injectable()
export class FlightsApiService {

  private _apiUri = ApiUtilities.apiServiciesEndPoint;
  private _headers: Headers;
  private _options: RequestOptions;

  constructor(private _http: Http) { 
    this._headers = new Headers({'Content-Type':'application/json'});
    this._options = new RequestOptions({headers:this._headers});
  }

  /**
   * list all flights in DB
   * @arguments(none)
   */
  public listFlights(): Observable<Array<Flight>>{
    console.log('start -- list method')
    let response = this._http.get(this._apiUri + '/list-flights', this._options).pipe(map(response => response.json()));
    console.log('end -- list method')
    return response;    
  }

}
