import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ITrade } from 'src/app/models/trade';
import { TradeDataService } from 'src/app/services/trade-data.service';

@Component({
  selector: 'app-thistory-table',
  templateUrl: './thistory-table.component.html',
  styleUrls: ['./thistory-table.component.css']
})
export class ThistoryTableComponent implements OnInit {

  trades: ITrade[]=[];
  dataSource!: MatTableDataSource<ITrade>;
  searchText="";
  
  displayedColumns: string[] = ['fund','units','price','account','date', 'type', 'loss_profit'];
  constructor(private tradeDataService: TradeDataService) { }
   
  ngOnInit(): void {
    this.getAllStocks();
    
  }

  getAllStocks()
  {
  this.tradeDataService.getTrades().subscribe((data)=>
    {
      this.trades=data;
      this.dataSource = new MatTableDataSource(this.trades);
    })
   }

   isPositive(num:number):boolean{
    if(num>0){return true;}
    else
      return false;
  }

  search(e:any){
    console.log("Insie parent..fuction search called..");
    this.searchText = e;
    this.dataSource.filter = e.trim().toLowerCase();
  }

}
