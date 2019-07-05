import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LitecoinRoutingModule } from './litecoin-routing.module';
import { LitecoinComponent } from './litecoin.component';

@NgModule({
  declarations: [LitecoinComponent],
  imports: [
    CommonModule,
    LitecoinRoutingModule
  ]
})
export class LitecoinModule { }
