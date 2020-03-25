import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViedeoComponent } from './basic/viedeo/viedeo.component';
import { MenuComponent } from './basic/menu/menu.component';
import { RoomComponent } from './site/room/room.component';

@NgModule({
  declarations: [
    AppComponent,
    ViedeoComponent,
    MenuComponent,
    RoomComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
