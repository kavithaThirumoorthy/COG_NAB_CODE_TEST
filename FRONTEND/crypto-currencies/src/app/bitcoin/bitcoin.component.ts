import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { GetCurrencyDetails } from '../services/get-currency-details.service';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-bitcoin',
  templateUrl: './bitcoin.component.html',
  styleUrls: ['./bitcoin.component.css']
})
export class BitcoinComponent implements OnInit {

  constructor(private service: GetCurrencyDetails) { }
  dataSource;
  profitData;
  displayedColumns = ['currencyName', 'tradeDate', 'tradeTime', 'price'];


  ngOnInit() {

    this.service.getCurrencyDetails('1').subscribe((res) => {
      const { cryptoCurrTradeDetailsDto, ...profit } = res;
      this.profitData = profit;
      this.dataSource = new MatTableDataSource(res.cryptoCurrTradeDetailsDto);
    });
  }
}