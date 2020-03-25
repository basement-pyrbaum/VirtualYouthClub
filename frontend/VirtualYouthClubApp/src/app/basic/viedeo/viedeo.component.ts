import { Component, OnInit, Input } from '@angular/core';
import '../../../vendor/jitsi/external_api.js';

declare var JitsiMeetExternalAPI: any;

@Component({
  selector: 'app-viedeo',
  templateUrl: './viedeo.component.html',
  styleUrls: ['./viedeo.component.css']
})
export class ViedeoComponent implements OnInit {


  @Input() room;
  @Input() password;
  pwReqiered = false;
  title = 'app';
  domain:string = "meet.example.com";
  options: any;
  api: any;

constructor()
{
}

ngAfterViewInit(): void {
   
  this.options = {
    roomName: this.room,
    width: 700,
    height: 700,
    parentNode: document.querySelector('#meet'),
  userInfo: {
      email: 'email@jitsiexamplemail.com',
displayName: 'sascha'},
    interfaceConfigOverwrite: { 
TOOLBAR_BUTTONS: [
      'microphone', 'camera', 'chat','videoquality','sharedvideo','desktop','settings'
  ],
  SETTINGS_SECTIONS: [ 'devices', 'language' ],

}
  }

  this.api = new JitsiMeetExternalAPI(this.domain, this.options);


    // when local user is trying to enter in a locked room 
    this.api.addEventListener('passwordRequired', () => {
      this.pwReqiered = true;
      this.api.executeCommand('password', this.password);
  });

  setTimeout(() => {
    console.log(this.api.get)
    this.api.addEventListener('videoConferenceJoined', async (response) => {
      await delay(2000);
      if(this.pwReqiered == false){
        this.api.executeCommand('password', this.password);
      }
      this.api.executeCommand('sendEndpointTextMessage', 'Test');

  });
},20);
  this.api.executeCommand('displayName', 'Sascha');
  }

  ngOnInit(): void {
  }


}

function delay(ms: number) {
  return new Promise( resolve => setTimeout(resolve, ms) );
}
