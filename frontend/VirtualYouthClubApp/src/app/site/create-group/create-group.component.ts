import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/model/user';
import { faSearch, faPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {

  users: User[];
  chooseUsers: User[] = [];
  faSearch = faSearch;
  faPlus = faPlus;
  searchText = ""

  constructor(public userService: UserService) {
    userService.getUsers(0, 5, "").subscribe(users => this.users = users);
  }

  ngOnInit(): void {
  }

  searchfunc(): void {
    this.userService.getUsers(0, 5, this.searchText).subscribe(users => this.users = users);
  }

  addUser(user: User) {
    this.chooseUsers.push(user);
  }

  deleteUser(user: User) {
    const index = this.chooseUsers.indexOf(user, 0);
    if (index > -1) {
      this.chooseUsers.splice(index, 1);
    }
  }

  isChoose(user: User) {
    const index = this.chooseUsers.indexOf(user, 0);
    if (index > -1) {
     return true;
    }
    return false;
  }

}
