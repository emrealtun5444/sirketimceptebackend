import { Component, OnInit } from '@angular/core';
import {AppStore} from "../../../../shared/app.store";
import {User} from "../../dto/user";
import {UserService} from "../../service/user.service";
import {AbstractBaseComponent} from "../../../../shared/abstract-base-component";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent extends AbstractBaseComponent implements OnInit {

  constructor(public  appStore: AppStore,
              private userService: UserService) {
      super(appStore);
  }

  user : User;

  ngOnInit(): void {
      this.subscribeToResponseBase(this.userService.userById(this.appStore.user.id), this.sorgulaSuccess, undefined);
  }

    private sorgulaSuccess(data: any) {
        this.user = data;
    }
}
