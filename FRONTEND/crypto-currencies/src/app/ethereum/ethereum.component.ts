import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';

import { GetCurrencyDetails } from '../services/get-currency-details.service';
@Component({
  selector: 'app-ethereum',
  templateUrl: './ethereum.component.html',
  styleUrls: ['./ethereum.component.css']
})
export class EthereumComponent implements OnInit {

  constructor(private service: GetCurrencyDetails) {  }
  dataSource;
  profitData;
  displayedColumns = ['currencyName', 'tradeDate', 'tradeTime', 'price'];


  ngOnInit() {  
    this.service.getCurrencyDetails('2').subscribe((res) => {
      const { cryptoCurrTradeDetailsDto, ...profit } = res;

      this.profitData = profit;
      this.dataSource = new MatTableDataSource(res.cryptoCurrTradeDetailsDto);
    });
  }
}


