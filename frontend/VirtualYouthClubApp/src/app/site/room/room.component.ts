import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { RoomService } from 'src/app/service/room.service';
import { interval, Subscription } from 'rxjs';
import { StateService } from 'src/app/service/state.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  public isCollapsed = false;
  isStartRoom = true;
  room = "";
  mySubscription: Subscription


  constructor(private route: ActivatedRoute,
    private router: Router,
    private roomService: RoomService,
    private stateService: StateService) {
    this.mySubscription = interval(10000).subscribe((x => {
      this.check();
    }));
  }

  ngOnInit(): void {
    var currentRoom = this.route.snapshot.paramMap.get('id');
    if (currentRoom != "start") {
      this.room = currentRoom;
      this.isStartRoom = false;
      this.isCollapsed = true;
    }
  }

  check() {
    this.stateService.extendedstate().subscribe(
      (data) => {
        if (data.state == 'ok') {
        } else if (data.state == 'need-agreement') {
          this.router.navigate(['/agreement']);
        } else if (data.state == 'is-closed') {
          this.router.navigate(['/closed']);
        } else {
          this.router.navigate(['/error']);
        }
      });
  }

}
