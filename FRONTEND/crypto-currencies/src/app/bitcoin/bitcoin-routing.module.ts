import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BitcoinComponent } from './bitcoin.component';

const routes: Routes = [{
  path: '',
  component: BitcoinComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BitcoinRoutingModule { }
