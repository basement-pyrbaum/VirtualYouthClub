import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StateService } from 'src/app/service/state.service';
import { RoomService } from 'src/app/service/room.service';
import { interval } from 'rxjs';

@Component({
  selector: 'app-closed',
  templateUrl: './closed.component.html',
  styleUrls: ['./closed.component.css']
})
export class ClosedComponent implements OnInit {
  mySubscription: any;

  constructor(private router: Router,
    private stateService: StateService) {
    this.mySubscription = interval(10000).subscribe((x => {
      this.check();
    }));
  }

  ngOnInit(): void {
  }



  check() {
    this.stateService.extendedstate().subscribe(
      (data) => {
        if (data.state == 'ok') {
          this.router.navigate(['/room/start']);
        } else if (data.state == 'need-agreement') {
          this.router.navigate(['/agreement']);
        } else if (data.state == 'is-closed') {
        } else {
          this.router.navigate(['/error']);
        }
      });
  }

}
