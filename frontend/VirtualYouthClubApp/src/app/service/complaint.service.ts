import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CreateComplaint } from '../model/create-complaint';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Message } from '../model/message';
import { MessagesPage } from '../model/messages-page';
import { MessageDetail } from '../model/message-detail';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {


  constructor(private http: HttpClient) { }

  createComplaint(name: CreateComplaint): Observable<void> {
    return this.http.post<void>(environment.backendUrl+"/complaints",name)
  }

  getComplaintsCount(): Observable<number> {
    return this.http.get<number>(environment.backendUrl+"/complaints/unread")
  }

  getComplaints(): Observable<MessagesPage> {
    return this.http.get<MessagesPage>(environment.backendUrl+"/complaints")
  }

  getComplaint(uuid: String): Observable<MessageDetail> {
    return this.http.get<MessageDetail>(environment.backendUrl+"/complaints/"+uuid)
  }

  deleteComplaint(uuid: String): Observable<void> {
    return this.http.delete<void>(environment.backendUrl+"/complaints/"+uuid)
  }



}
