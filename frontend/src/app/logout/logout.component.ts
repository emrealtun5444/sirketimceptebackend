import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../shared/service/token-storage.service";
import {Router} from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

    constructor(private tokenStorage: TokenStorageService, private router: Router) {}

    ngOnInit(): void {
        this.tokenStorage.signOut();
        this.router.navigate(['login']);
    }

}
