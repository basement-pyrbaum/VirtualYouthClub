import { Component, OnInit } from '@angular/core';
import { faSearch, faPlus , faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { RoomService } from 'src/app/service/room.service';
import { StateService } from 'src/app/service/state.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-sitenav',
  templateUrl: './sitenav.component.html',
  styleUrls: ['./sitenav.component.css']
})
export class SitenavComponent implements OnInit {
  faTrashAlt = faTrashAlt;
  faSearch = faSearch;
  faPlus = faPlus;
  searchText = ""
  hasPermission = false;
  edit = false;
  createRoom ="";
  ycstate = "geschlossen"
  state = false;
  admin = false;
  idforDelete: String;
  nameforDelete: String;

  rooms = []

  constructor(public roomService: RoomService, public stateService: StateService,private modalService: NgbModal) {
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

  deleteRoom(content,uuid : String,name: String){
    this.idforDelete = uuid;
    this.nameforDelete = name;
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});

  }

  deleteComitted(){
    this.roomService.deleteRoom(this.idforDelete).subscribe( r => {this.searchfunc()})
    this.modalService.dismissAll();
  }

}
