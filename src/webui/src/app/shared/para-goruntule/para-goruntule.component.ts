import {AbstractBaseComponent} from '../abstract-base-component';
import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {Para} from '../para/para';
import {AppStore} from "../app.store";

@Component({
  selector: 'app-para-goruntule',
  templateUrl: './para-goruntule.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./para-goruntule.component.css']
})
export class ParaGoruntuleComponent extends AbstractBaseComponent implements OnInit {

  constructor(appStore: AppStore) {
    super(appStore);
  }

  private _para: any;

  @Input()
  get para() {
    return this._para;
  }

  set para(val) {
    if (!val) {
      this._para = Para.newInstance();
    } else if (typeof val == 'number') {
      this._para = Para.get(val);
    } else if (val.value == undefined || val.value == null) {
      this._para = Para.newInstance();
    } else if (val.paraBirimi == undefined || val.paraBirimi == null) {
      this._para = Para.get(val.value);
    } else {
      this._para = val;
    }
  }

  ngOnInit() {
  }


}
