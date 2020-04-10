import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import '../../../vendor/jitsi/external_api.js';
import { OAuthService } from 'angular-oauth2-oidc';
import { RoomService } from 'src/app/service/room.service.js';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons';

declare var JitsiMeetExternalAPI: any;

@Component({
  selector: 'app-viedeo',
  templateUrl: './viedeo.component.html',
  styleUrls: ['./viedeo.component.css']
})
export class ViedeoComponent implements OnInit {
  faEnvelope = faEnvelope
  @ViewChild('meet') elementView: ElementRef;
  @Input() room;
  @Input() password;
  roomName = "";
  pwReqiered = false;
  isInit = false;
  title = 'app';
  domain: string = "vrjitsi.youthclubstage.de";
  options: any;
  api: any;

  constructor(public oauthService: OAuthService,public roomService:RoomService) {
  }

  ngAfterViewInit(): void {
    this.initJitsiOrWait();
  }

  ngOnInit(): void {
  }

  initJitsiOrWait() :void {
    if(this.oauthService.hasValidAccessToken()){
      this.roomService.getRoom(this.room).subscribe( data => this.initJitsi(data.name));
    }else{
      delay(500).then( x =>{ this.initJitsiOrWait()});
    }
  }

  initJitsi(roomName) :void {
    var height;
    var width;
    var isWidthGreater = ((this.elementView.nativeElement.width/4*3) > this.elementView.nativeElement.offsetHeight);
    if(isWidthGreater){
      height = this.elementView.nativeElement.offsetHeight;
      width = this.elementView.nativeElement.offsetHeight/3*4;
    }else{
      width = this.elementView.nativeElement.width;
      height = this.elementView.nativeElement.width/4*3;
    }


    this.roomName = roomName;
    this.options = {
      roomName: "ycs"+roomName,
      width: '100%',
      height: '100%',
      jwt: this.oauthService.getAccessToken(),
      parentNode: document.querySelector('#meet'),
      interfaceConfigOverwrite: {
        TOOLBAR_BUTTONS: [
          'microphone', 'camera', 'chat', 'videoquality', 'sharedvideo', 'desktop', 'settings'
        ],
        SETTINGS_SECTIONS: ['devices', 'language'],

      }
    }

    this.api = new JitsiMeetExternalAPI(this.domain, this.options);
    this.isInit=true;

    // when local user is trying to enter in a locked room 
    this.api.addEventListener('passwordRequired', () => {
      this.pwReqiered = true;
      this.api.executeCommand('password', this.password);
    });

      setTimeout(() => {
        this.api.addEventListener('videoConferenceJoined', async (response) => {
          //this.api.executeCommand('displayName', 'Sascha');
        });
      }, 20);
  }


}

function delay(ms: number) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
