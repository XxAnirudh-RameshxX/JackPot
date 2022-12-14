import { Component, OnInit } from '@angular/core';
import { MarketMover } from 'src/app/models/market-movers.model';
import { JackpotService } from 'src/app/services/jackpot.service';

@Component({
  selector: 'app-trade-content',
  templateUrl: './trade-content.component.html',
  styleUrls: ['./trade-content.component.css']
})
export class TradeContentComponent implements OnInit {

  assetCardDetails!: MarketMover

  assetTypeId: number = 1;

  constructor(private jackpotService: JackpotService) { }

  ngOnInit(): void {
  }

  getStockDetails(ticker: string) {
    this.jackpotService.getStockDetails(ticker).subscribe({
      next: (response: MarketMover) => {
        this.assetCardDetails = response;
      }
    })
  }

  changeAssetType(assetType: string) {
    
    this.assetTypeId = parseInt(assetType);
  }

  marketMoverAssetChange(asset: MarketMover) {
    this.getStockDetails(asset.name);
  }

}
