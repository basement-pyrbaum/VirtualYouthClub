import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { StateService } from '../service/state.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OpenGuardService implements CanActivate {
  constructor(public stateService:StateService, public router: Router) {}
  canActivate():  Observable<boolean> | Promise<boolean> | boolean {
    return new Promise(res => {
      this.stateService.isOpenOrAdmin().subscribe(
          (data) => {
              if (data === true) {
                  res(true);
              } else {
                  this.router.navigate(['/closed']);
                  res(false);
              }
          },
          (error) => {
              this.router.navigate(['/closed']);
              res(false);
          }
      );
  });
  }
}