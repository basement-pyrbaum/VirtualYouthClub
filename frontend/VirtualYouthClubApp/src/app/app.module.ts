import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViedeoComponent } from './basic/viedeo/viedeo.component';
import { MenuComponent } from './basic/menu/menu.component';
import { RoomComponent } from './site/room/room.component';
import { OAuthModule } from 'angular-oauth2-oidc';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SitenavComponent } from './basic/sitenav/sitenav.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ChatComponent } from './basic/chat/chat.component';
import { StartComponent } from './site/start/start.component';
import { ImpressumComponent } from './site/impressum/impressum.component';
import { DatenschutzComponent } from './site/datenschutz/datenschutz.component';
import { ClosedComponent } from './site/closed/closed.component';


@NgModule({
  declarations: [
    AppComponent,
    ViedeoComponent,
    MenuComponent,
    RoomComponent,
    SitenavComponent,
    ChatComponent,
    StartComponent,
    ImpressumComponent,
    DatenschutzComponent,
    ClosedComponent
  ],
  imports: [
    OAuthModule.forRoot({
      resourceServer: {
          allowedUrls: ["/api"],
          sendAccessToken: true
      }}),
      NgbModule,
      BrowserModule,
      AppRoutingModule,
      HttpClientModule,
      FormsModule,
      FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
