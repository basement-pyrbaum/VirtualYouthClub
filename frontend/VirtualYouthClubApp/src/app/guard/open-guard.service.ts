import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { StateService } from '../service/state.service';
import { Observable } from 'rxjs';
import { OAuthService } from 'angular-oauth2-oidc';

@Injectable({
  providedIn: 'root'
})
export class OpenGuardService implements CanActivate {
  constructor(public oauthService: OAuthService, public stateService: StateService, public router: Router) { }
  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (this.oauthService.hasValidAccessToken()) {
      return new Promise(res => {
        this.stateService.extendedstate().subscribe(
          (data) => {
            console.log("fuu")
            console.log(data)
            if (data.state == 'ok') {
              res(true);
            } else if (data.state == 'need-agreement'){
              this.router.navigate(['/agreement']);
              res(false);
            } else if (data.state == 'is-closed'){
              this.router.navigate(['/closed']);
              res(false);
            }else {
              this.router.navigate(['/error']);
              res(false);
            }
          },
          (error) => {
            console.log("error")
            this.router.navigate(['/error']);
            res(false);
          }
        );
      });
    }else{
      this.oauthService.initImplicitFlow();
      return false;
    }
  }
}