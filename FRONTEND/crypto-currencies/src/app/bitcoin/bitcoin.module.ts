import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import { BitcoinRoutingModule } from './bitcoin-routing.module';
import { BitcoinComponent } from './bitcoin.component';
import {MatButtonModule} from '@angular/material'

@NgModule({
  declarations: [BitcoinComponent],
  imports: [
    CommonModule,
    BitcoinRoutingModule,
    MatTableModule,
    MatToolbarModule,
    MatButtonModule
  ]
})
export class BitcoinModule { }
