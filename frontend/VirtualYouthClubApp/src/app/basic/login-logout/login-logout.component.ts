import { Component, OnInit } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { Claims } from 'src/app/model/claims';

@Component({
  selector: 'app-login-logout',
  templateUrl: './login-logout.component.html',
  styleUrls: ['./login-logout.component.css']
})
export class LoginLogoutComponent implements OnInit {

  isLogin = false;
  name= "";

  constructor(private oauthService: OAuthService) {
    if(this.oauthService.hasValidAccessToken()){
      this.isLogin = true;
      console.log(this.oauthService.getIdentityClaims());
      this.name =this.oauthService.getIdentityClaims()['name'];
    }
   }

  ngOnInit(): void {
  }

  logout() : void{
    this.oauthService.logOut(false);
  }

  login(){
    this.oauthService.initImplicitFlow();
  }

}
