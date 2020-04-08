import { Component, OnInit } from '@angular/core';
import { faSearch, faPlus , faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { RoomService } from 'src/app/service/room.service';
import { StateService } from 'src/app/service/state.service';

@Component({
  selector: 'app-sitenav',
  templateUrl: './sitenav.component.html',
  styleUrls: ['./sitenav.component.css']
})
export class SitenavComponent implements OnInit {
  faTrashAlt = faTrashAlt
  faSearch = faSearch;
  faPlus = faPlus;
  searchText = ""
  hasPermission = false;
  edit = false;
  createRoom ="";
  ycstate = "geschlossen"
  state = false;
  admin = false;

  rooms = []

  constructor(public roomService: RoomService, public stateService: StateService) {
    roomService.getRooms(0,10,"").subscribe( data => {
       this.rooms = data;
    })

    roomService.hasCreatePermission().subscribe( x => 
      {
        this.hasPermission = x;
        console.log(this.hasPermission)
    } )
   
    this.refreshState();
    stateService.isAdmin().subscribe(x => this.admin = x)
  }

   refreshState(){
    this.stateService.isOpen().subscribe( x => {
      this.stateChanged(x);
    })
  }

  stateChanged(x : boolean){
    console.log(x);
    this.state = x;
    this.ycstate = x ? "geöffnet" : "geschlossen";
  }


  ngOnInit(): void {
  }

  searchfunc():void{
    this.roomService.getRooms(0,10,this.searchText).subscribe( data => {
      this.rooms = data;
   })
  }

  showRoom(){
    this.edit = true;
  }

  changeState(){
    this.stateService.setOpen(!this.state).subscribe(x => this.refreshState())
  }

  addRoom():void{
    this.roomService.addRoom(this.createRoom).subscribe( r => {this.searchfunc()})
    this.createRoom = "";
    this.edit = false;

  }

  deleteRoom(uuid : String){
    this.roomService.deleteRoom(uuid).subscribe( r => {this.searchfunc()})
  }

}
