import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ComplaintService } from 'src/app/service/complaint.service';
import { MessageDetail } from 'src/app/model/message-detail';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-messages-detail',
  templateUrl: './messages-detail.component.html',
  styleUrls: ['./messages-detail.component.css']
})
export class MessagesDetailComponent implements OnInit {

  messageid: string;
  message = {
    id: "",
    from: "",
    createDate:"",
    subject:"",
    read: false,
    message : "",
    to : ""
  };

  constructor(private route: ActivatedRoute,private complaintService : ComplaintService) { }

  ngOnInit(): void {
    this.messageid = this.route.snapshot.paramMap.get('id');
    this.complaintService.getComplaint(this.messageid).subscribe(msg => this.message = msg);
  }

}
