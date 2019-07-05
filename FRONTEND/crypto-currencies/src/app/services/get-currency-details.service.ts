import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { data } from './dummy';

@Injectable({
  providedIn: 'root'
})
export class GetCurrencyDetails {

  constructor(
    private httpClient: HttpClient
    ) {}

    data = data;

    apiBasePath: string = 'http://localhost:8080/cryptoCurrency/currencyDetails';

    getCurrencyDetails(currencyCode) {
      // this.httpClient.get(`${this.apiBasePath}/${currencyCode}`).subscribe((res) => {
      //   res = this.data;
      //   return res;
      // });

      return this.data;
    }
}