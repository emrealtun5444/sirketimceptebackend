import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { MarketPlaceComponent } from './market-place.component';

describe('MarketPlaceComponent', () => {
  let component: MarketPlaceComponent;
  let fixture: ComponentFixture<MarketPlaceComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketPlaceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketPlaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
