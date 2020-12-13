import {Component} from '@angular/core';
import {AppMainComponent} from './app.main.component';
import {AppStore} from './shared/app.store';
import {Router} from '@angular/router';

@Component({
  selector: 'app-topbar',
  templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent {

  showChangePasword = false;

  constructor(public app: AppMainComponent,
              public appStore: AppStore,
              private router: Router) {
  }


  signOut() {
    this.router.navigate(['logout']);
  }

  onChangePaswordShow() {
    this.showChangePasword = true;
  }

  onChangePaswordClose(event) {
    this.showChangePasword = event;
  }
}
