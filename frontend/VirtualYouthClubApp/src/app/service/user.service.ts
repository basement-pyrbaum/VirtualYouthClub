import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsers(page,size,search): Observable<User[]> {
    var searchString = "";
    if(search != ""){
      searchString = "&search="+search;
    }
    return this.http.get<User[]>(environment.backendUrl+"/users?page="+page+"&size="+size+searchString)
  }
}
