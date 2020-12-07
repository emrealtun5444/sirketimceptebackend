import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { FaturaComponent } from './fatura.component';

describe('FaturaComponent', () => {
  let component: FaturaComponent;
  let fixture: ComponentFixture<FaturaComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ FaturaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FaturaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
