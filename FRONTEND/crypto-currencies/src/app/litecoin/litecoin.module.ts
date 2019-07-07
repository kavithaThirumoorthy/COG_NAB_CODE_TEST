import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import { LitecoinRoutingModule } from './litecoin-routing.module';
import { LitecoinComponent } from './litecoin.component';
import { MatTableModule } from '@angular/material/table';
import {MatButtonModule} from '@angular/material'
@NgModule({
  declarations: [LitecoinComponent],
  imports: [
    CommonModule,
    LitecoinRoutingModule,
    MatTableModule,
MatToolbarModule,
MatButtonModule

    
    
    
  ]
})
export class LitecoinModule { }
