import { Component, OnInit } from '@angular/core';
import { faWindowClose } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-cookiebanner',
  templateUrl: './cookiebanner.component.html',
  styleUrls: ['./cookiebanner.component.css']
})
export class CookiebannerComponent implements OnInit {

  cookieShow = true;
  faWindowClose = faWindowClose;

  constructor() { 
    this. initCookieShow();
  }

  ngOnInit(): void {
  }

  initCookieShow(): void{
    if(sessionStorage.getItem("closedCookieBanner") != null){
      this.cookieShow = false;
    }else{
      this.cookieShow = true;
    }
  }
  
  
  closeCookie(): void{
    sessionStorage.setItem("closedCookieBanner","");
    this.initCookieShow();
  }

}
