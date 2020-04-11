import { Component, OnInit } from '@angular/core';
import { Subscription, interval } from 'rxjs';
import { StateService } from 'src/app/service/state.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  open = false;
  isCollapsed = false;
  mySubscription: Subscription;

  constructor(private stateService: StateService) {
    this.yopen();
    this.mySubscription = interval(10000).subscribe((x => {
      this.yopen();
    }));
   }

   yopen(){
     this.stateService.isOpenPublic().subscribe(result => this.open = result);
   }

   

  ngOnInit(): void {
  }

}
