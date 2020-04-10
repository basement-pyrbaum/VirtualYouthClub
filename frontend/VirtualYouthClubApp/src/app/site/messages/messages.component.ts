import { Component, OnInit } from '@angular/core';
import { ComplaintService } from 'src/app/service/complaint.service';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  faTrashAlt = faTrashAlt

  messages =[];

  constructor(private complaintService : ComplaintService) { 
    complaintService.getComplaints().subscribe(x => this.messages = x.content);
  }

  delete(uuid: string){
    this.complaintService.deleteComplaint(uuid).subscribe( x => this.complaintService.getComplaints().subscribe(x => this.messages = x.content))
  }

  ngOnInit(): void {
  }

}
