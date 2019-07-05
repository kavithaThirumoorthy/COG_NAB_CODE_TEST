import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EthereumRoutingModule } from './ethereum-routing.module';
import { EthereumComponent } from './ethereum.component';

@NgModule({
  declarations: [EthereumComponent],
  imports: [
    CommonModule,
    EthereumRoutingModule
  ]
})
export class EthereumModule { }
