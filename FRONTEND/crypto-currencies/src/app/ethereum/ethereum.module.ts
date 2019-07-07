import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import { EthereumRoutingModule } from './ethereum-routing.module';
import { EthereumComponent } from './ethereum.component';
import {MatButtonModule} from '@angular/material'
@NgModule({
  declarations: [EthereumComponent],
  imports: [
    CommonModule,
    EthereumRoutingModule,
    MatTableModule,
    MatToolbarModule,
    MatButtonModule
  ]
})
export class EthereumModule { }
