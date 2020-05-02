import { Component, OnInit } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'app-group-menu',
  templateUrl: './group-menu.component.html',
  styleUrls: ['./group-menu.component.css']
})
export class GroupMenuComponent implements OnInit {

  isAdmin = false;


  constructor(private oauthService: OAuthService, private complaintService: ComplaintService) {
    if (this.oauthService.hasValidAccessToken()) {
      this.isAdmin = this.oauthService.getIdentityClaims()['groups'].includes("admin");
    }
  }
  
  ngOnInit(): void {
  }

}
