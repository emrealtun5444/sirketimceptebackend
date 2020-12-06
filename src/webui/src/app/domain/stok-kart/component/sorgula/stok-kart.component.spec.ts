import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { StokKartComponent } from './stok-kart.component';

describe('StokKartComponent', () => {
  let component: StokKartComponent;
  let fixture: ComponentFixture<StokKartComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ StokKartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StokKartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
