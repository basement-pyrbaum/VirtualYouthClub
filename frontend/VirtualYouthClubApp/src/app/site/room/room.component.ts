import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { RoomService } from 'src/app/service/room.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  public isCollapsed = false;
  isStartRoom = true;
  room = "";

  constructor(private route: ActivatedRoute,
    private router: Router,
    private roomService: RoomService) { }

  ngOnInit(): void {
    var currentRoom = this.route.snapshot.paramMap.get('id'); 
    if(currentRoom != "start"){
      this.room = currentRoom;
      this.isStartRoom = false;
      this.isCollapsed = true;
    } 
  }

}
