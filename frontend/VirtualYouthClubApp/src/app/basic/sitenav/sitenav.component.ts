import { Component, OnInit } from '@angular/core';
import { faSearch, faPlus } from '@fortawesome/free-solid-svg-icons';
import { RoomService } from 'src/app/service/room.service';

@Component({
  selector: 'app-sitenav',
  templateUrl: './sitenav.component.html',
  styleUrls: ['./sitenav.component.css']
})
export class SitenavComponent implements OnInit {
  faSearch = faSearch;
  faPlus = faPlus;
  searchText = ""

  rooms = []

  constructor(public roomService: RoomService) {
    roomService.getRooms(0,10,"").subscribe( data => {
       this.rooms = data;
    })
   }

  ngOnInit(): void {
  }

  search():void{
    this.roomService.getRooms(0,10,this.searchText).subscribe( data => {
      this.rooms = data;
   })
  }

}
