import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';

import { BitcoinRoutingModule } from './bitcoin-routing.module';
import { BitcoinComponent } from './bitcoin.component';

@NgModule({
  declarations: [BitcoinComponent],
  imports: [
    CommonModule,
    BitcoinRoutingModule,
    MatTableModule
  ]
})
export class BitcoinModule { }
