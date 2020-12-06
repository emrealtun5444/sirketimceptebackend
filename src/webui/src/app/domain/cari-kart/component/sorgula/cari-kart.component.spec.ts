import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { CariKartComponent } from './cari-kart.component';

describe('CariKartComponent', () => {
  let component: CariKartComponent;
  let fixture: ComponentFixture<CariKartComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CariKartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CariKartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
