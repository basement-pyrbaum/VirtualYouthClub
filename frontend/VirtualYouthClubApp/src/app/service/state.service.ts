import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { State } from '../model/state';

@Injectable({
  providedIn: 'root'
})
export class StateService {

  constructor(private http: HttpClient) { }

  extendedstate(): Observable<State> {
    return this.http.get<State>(environment.backendUrl+"/extendedState")
  }

  isOpen(): Observable<boolean> {
    return this.http.get<boolean>(environment.backendUrl+"/state")
  }

  isAdmin(): Observable<boolean> {
    return this.http.get<boolean>(environment.backendUrl+"/admin")
  }


  setOpen(open: boolean): Observable<void> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<void>(environment.backendUrl+"/state",open,{headers: headers})
  }

  agreement(): Observable<void> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<void>(environment.backendUrl+"/agreement","",{headers: headers})
  }


}
