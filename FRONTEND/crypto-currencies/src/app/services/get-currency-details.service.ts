import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { map, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GetCurrencyDetails {

  constructor(
    private httpClient: HttpClient
  ) { }

  getCurrencyDetails(currencyCode: string): Observable<any> {
    const url = `${environment.apiBasePath}/cryptoCurrency/currencyProfitDetails/${currencyCode}`;

    return this.httpClient.get(url).pipe(map((res) => {
      retry(2);
      return res;
    }));
  }
}