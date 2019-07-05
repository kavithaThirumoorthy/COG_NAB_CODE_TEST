import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';

import { GetCurrencyDetails } from '../services/get-currency-details.service';

@Component({
  selector: 'app-bitcoin',
  templateUrl: './bitcoin.component.html',
  styleUrls: ['./bitcoin.component.css']
})
export class BitcoinComponent implements OnInit {

  constructor(private service: GetCurrencyDetails) {  }

  dataSource;
  profitData;
  displayedColumns = ['id', 'tradeDate', 'tradeTime', 'price'];

  ngOnInit() {
    const BTCResponse = this.service.getCurrencyDetails('BTC');
    const { cryptoCurrTradeDetailsDto, ...profit} = BTCResponse;
    this.profitData = profit;
    
    this.dataSource = new MatTableDataSource(BTCResponse.cryptoCurrTradeDetailsDto);
  }

}
