import { Component, OnInit } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { ComplaintService } from 'src/app/service/complaint.service';
import { Subscription, interval } from 'rxjs';

@Component({
  selector: 'app-message-count',
  templateUrl: './message-count.component.html',
  styleUrls: ['./message-count.component.css']
})
export class MessageCountComponent implements OnInit {
  faEnvelope = faEnvelope;
  isAdmin=false;
  count = 0;
  mySubscription: Subscription

  constructor(private oauthService: OAuthService, private complaintService :ComplaintService) {
    if(this.oauthService.hasValidAccessToken()){
      this.isAdmin = this.oauthService.getIdentityClaims()['groups'].includes("admin");
      if(this.isAdmin){
        complaintService.getComplaintsCount().subscribe( x => this.count = x);
        this.mySubscription = interval(30000).subscribe((x => {
          complaintService.getComplaintsCount().subscribe( x => this.count = x);
        }));
      }

    }
   }

  ngOnInit(): void {
  }

}
