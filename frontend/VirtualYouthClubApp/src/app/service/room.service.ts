import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Room } from '../model/room';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient) { }

  getRooms(page,size,search): Observable<Room[]> {
    var searchString = "";
    if(search != ""){
      searchString = "&search="+search;
    }
    return this.http.get<Room[]>(environment.backendUrl+"/rooms?page="+page+"&size="+size+searchString)
  }

  getRoom(uuid: String): Observable<Room> {
    return this.http.get<Room>(environment.backendUrl+"/rooms/"+uuid)
  }
}
