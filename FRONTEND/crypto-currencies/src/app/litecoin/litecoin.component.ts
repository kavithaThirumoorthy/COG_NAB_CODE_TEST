import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { GetCurrencyDetails } from '../services/get-currency-details.service';
@Component({
  selector: 'app-litecoin',
  templateUrl: './litecoin.component.html',
  styleUrls: ['./litecoin.component.css']
})
export class LitecoinComponent implements OnInit {

  constructor(private service: GetCurrencyDetails) {  }
  dataSource;
  profitData;
  displayedColumns = ['currencyName', 'tradeDate', 'tradeTime', 'price'];


  ngOnInit() {    
    this.service.getCurrencyDetails('3').subscribe((res) => {
      const { cryptoCurrTradeDetailsDto, ...profit } = res;

      this.profitData = profit;
      this.dataSource = new MatTableDataSource(res.cryptoCurrTradeDetailsDto);
    });
  }
}
