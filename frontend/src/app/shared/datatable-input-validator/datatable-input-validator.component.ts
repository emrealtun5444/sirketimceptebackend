import {Component, EventEmitter, forwardRef, Input, Output, ViewChild} from '@angular/core';
import {
  AbstractControl,
  ControlValueAccessor,
  FormControl,
  NG_VALUE_ACCESSOR,
  NgModel,
  ValidatorFn
} from '@angular/forms';
import {AbstractBaseComponent} from '../abstract-base-component';
import {AppStore} from "../app.store";

@Component({
  selector: 'app-datatable-input-validator',
  templateUrl: './datatable-input-validator.component.html',
  providers: [
    {provide: NG_VALUE_ACCESSOR, useExisting: forwardRef(() => DatatableInputValidatorComponent), multi: true}
  ]
})
export class DatatableInputValidatorComponent extends AbstractBaseComponent implements ControlValueAccessor {

  control: AbstractControl = new FormControl();

  _disabled: boolean = false;
  _type: string = 'input';
  _validators: ValidatorFn[];

  @Output() notifyChange: EventEmitter<any> = new EventEmitter<any>();

  @Input() label: string = '';
  @Input() optionList: any[] = [];
  @Input() disableParaBirimi: boolean;

  @Input()
  set disabled(disabled: boolean) {
    this._disabled = disabled;
  }

  value: any;
  private _initialValue: any;
  private _initialized: boolean = false;

  private onChange = (_: any) => {
  };

  get type() {
    return this._type;
  }

  @Input()
  set type(type: string) {
    if (!type) {
      return;
    }

    this._type = type;
  }

  @Input()
  set validators(validators: ValidatorFn []) {
    this._validators = validators;

    if (this.control) {
      this.control.setValidators(this._validators);
      this.control.updateValueAndValidity();
    }
  }

  @ViewChild(NgModel)
  set model(model: NgModel) {
    this.control = model.control;
    this.control.setValidators(this._validators);

    this.control.valueChanges.subscribe(_value => {
      let emitValue = null;
      if (this.control.valid || this.control.disabled) {
        emitValue = _value;
      }

      this.onChange(emitValue);
      this.notifyChange.emit(emitValue);
    });
  }

  constructor(appStore: AppStore) {
    super(appStore);
  }

  writeValue(value: string) {
    if (!this._initialized && value) {
      this._initialized = true;
      this._initialValue = value;
    }
    if (this.type == 'calendar' && value) {
      this.value = new Date(value)
    } else {
      this.value = value;
    }
  }

  registerOnChange(fn: any) {
    this.onChange = fn;
  }

  registerOnTouched() {
  }

  fix() {
    if (this.control.valid) {
      return;
    }

    this.value = this._initialValue;
  }
}
