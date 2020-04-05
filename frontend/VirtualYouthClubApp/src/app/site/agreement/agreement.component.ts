import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/service/state.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agreement',
  templateUrl: './agreement.component.html',
  styleUrls: ['./agreement.component.css']
})
export class AgreementComponent implements OnInit {

  agreementcb = false;

  constructor(private stateService : StateService, public router: Router) { }

  ngOnInit(): void {
  }

  agreement(): void{
    if(this.agreementcb){
      this.stateService.agreement().subscribe( x => {
        this.router.navigate(["/room/start"]);
      })
    }
  }

}
