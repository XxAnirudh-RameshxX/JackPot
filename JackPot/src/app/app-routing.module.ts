import { AssetCardComponent } from './components/organisms/asset-card/asset-card.component';
import { MarginSummaryComponent } from './components/organisms/margin-summary/margin-summary.component';
import { BuySellPanelComponent } from './components/molecules/buy-sell-panel/buy-sell-panel.component';
import { ButtonTradeComponent } from './components/atoms/button-trade/button-trade.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DownloadComponent } from './components/molecules/download/download.component';
import { DropdownComponent } from './components/molecules/dropdown/dropdown.component';
import { SearchBarComponent } from './components/molecules/search-bar/search-bar.component';
import { TableOptionsComponent } from './components/organisms/table-options/table-options.component';
import { ThistoryTableComponent } from './components/organisms/thistory-table/thistory-table.component';
import { TradeHistoryPageComponent } from './components/pages/trade-history-page/trade-history-page.component'; 
import { TabsComponent } from './components/organisms/tabs/tabs.component';
import { ProfilePageComponent } from './components/pages/profile-page/profile-page.component';
import { TradePageComponent } from './components/pages/trade-page/trade-page.component';
import { PorfolioComponent } from './components/pages/porfolio/porfolio.component';

//Let's add temporary routes with our names here for testing and not change app.component as it'll be easier to merge etc

const routes: Routes = [

  { path: 'trade', component: TradePageComponent },

  {
    path: 'profile',
    component: ProfilePageComponent,
  },
  {
    path:'portfolio',
    component: PorfolioComponent
  },
  { path: 'shreyanshi', component: TradeHistoryPageComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
