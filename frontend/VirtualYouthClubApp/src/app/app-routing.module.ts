import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViedeoComponent } from './basic/viedeo/viedeo.component';
import { RoomComponent } from './site/room/room.component';


const routes: Routes = [  
  { path: 'main', component:  RoomComponent},
{ path: '',  redirectTo: '/main',  pathMatch: 'full'},];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
