import {Observable} from 'rxjs';
import {AbstractBaseComponent} from './abstract-base-component';
import {AppStore} from "./app.store";

export abstract class AbstractComponent extends AbstractBaseComponent {

  constructor(appStore: AppStore) {
    super(appStore);
  }

  protected subscribeToValueChange(valueChanges: Observable<any>, func: Function) {
    valueChanges.subscribe((data: any) => {
      func.bind(this, data).call();
    });
  }

  protected startFN() {
    this.appStore.loading = true;
    this.appStore.clearMessage();
  }

  protected endFN() {
    this.appStore.loading = false;
  }
}
