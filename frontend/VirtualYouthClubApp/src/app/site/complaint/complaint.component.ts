import { Component, OnInit } from '@angular/core';
import { ComplaintService } from 'src/app/service/complaint.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {


  model = {
    "subject" : "",
    "message" : "" 
  }

  constructor(private router:Router,private complaintService : ComplaintService,private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  submit(content){
    this.complaintService.createComplaint(this.model).subscribe( ok => this.modalService.open(content, {backdropClass: 'light-blue-backdrop'}))    
  }

  ok(){
    this.modalService.dismissAll();
    this.router.navigate(['/room/start']);
  }

}
