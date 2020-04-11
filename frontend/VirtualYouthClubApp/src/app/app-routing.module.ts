import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViedeoComponent } from './basic/viedeo/viedeo.component';
import { RoomComponent } from './site/room/room.component';
import { StartComponent } from './site/start/start.component';
import { DatenschutzComponent } from './site/datenschutz/datenschutz.component';
import { ImpressumComponent } from './site/impressum/impressum.component';
import { OpenGuardService } from './guard/open-guard.service';
import { ClosedComponent } from './site/closed/closed.component';
import { ErrorComponent } from './site/error/error.component';
import { AgreementComponent } from './site/agreement/agreement.component';
import { KniggeComponent } from './site/knigge/knigge.component';
import { ComplaintComponent } from './site/complaint/complaint.component';
import { MessagesComponent } from './site/messages/messages.component';
import { MessagesDetailComponent } from './site/messages-detail/messages-detail.component';
import { LoginsuccessComponent } from './site/loginsuccess/loginsuccess.component';


const routes: Routes = [  
{ path: 'main', component:  StartComponent },
{ path: 'datenschutz', component:  DatenschutzComponent},
{ path: 'impressum', component:  ImpressumComponent},
{ path: 'closed', component:  ClosedComponent},
{ path: 'knigge', component:  KniggeComponent},
{ path: 'error', component:  ErrorComponent},
{ path: 'loginsuccess', component:  LoginsuccessComponent},
{ path: 'agreement', component:  AgreementComponent},
{ path: 'room/:id', component:  RoomComponent, canActivate: [OpenGuardService] },
{ path: 'complaint', component:  ComplaintComponent, canActivate: [OpenGuardService] },
{ path: 'messages', component:  MessagesComponent, canActivate: [OpenGuardService] },
{ path: 'messages/:id', component:  MessagesDetailComponent, canActivate: [OpenGuardService] },
{ path: '',  redirectTo: '/main',  pathMatch: 'full'},];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
