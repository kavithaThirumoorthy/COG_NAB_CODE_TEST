import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) { }

  paths = 
    {
      'BITCOIN': 'BTC',
      'ETHEREUM': 'ETH',
      'LITECOIN': 'LTC'
    }
  

  ngOnInit() {
  }

  navigate(e) {
    this.router.navigateByUrl(this.paths[e.target.innerText]);
  }
}
