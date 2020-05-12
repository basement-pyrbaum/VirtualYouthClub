import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Group } from '../model/group';

@Injectable({
  providedIn: 'root'
})
export class GroupService {


  constructor(private http: HttpClient) { }

  addGroup(name: String): Observable<void> {
    return this.http.post<void>(environment.backendUrl+"/rooms",name)
  }

  getGroups(): Observable<Group[]> {
    return this.http.get<Group[]>(environment.backendUrl+"/groups")
  }
}
