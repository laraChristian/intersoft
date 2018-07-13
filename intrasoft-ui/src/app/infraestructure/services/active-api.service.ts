import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http'
import { ApiUtilities } from 'src/app/infraestructure/services/api.utilities';
import { Observable } from 'rxjs';
import { map, filter, catchError, mergeMap } from 'rxjs/operators';
import { Active } from '../../domain/active';


@Injectable()
export class ActiveApiService {

    private _apiUri = ApiUtilities.apiServiciesEndPoint;
    private _headers: Headers;
    private _options: RequestOptions;

    constructor(private _http: Http) {
        this._headers = new Headers({ 'Content-Type': 'application/json' });
        this._options = new RequestOptions({ headers: this._headers });
    }

    public listActives(): Observable<Array<Active>> {
        console.log('start -- list method')
        let response = this._http.get(this._apiUri + '/list-actives', this._options).pipe(map(resp => resp.json()));
        return response;
    }

}