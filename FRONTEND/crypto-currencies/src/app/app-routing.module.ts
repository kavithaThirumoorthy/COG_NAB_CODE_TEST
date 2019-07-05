import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';

const routes: Routes = [
	{
		path: '',
		pathMatch: 'full',
		component: HomeComponent
	},
	{
		path: 'BTC',
		loadChildren: () => import('./bitcoin/bitcoin.module').then(mod => mod.BitcoinModule)
  },
  {
    path: 'ETH',
    loadChildren: () => import('./ethereum/ethereum.module').then(mod => mod.EthereumModule)
  },
  {
    path: 'LTC',
    loadChildren: () => import('./litecoin/litecoin.module').then(mod => mod.LitecoinModule)
  }
]

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }