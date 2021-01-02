import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerformansReportComponent } from './performans-report.component';

describe('PerformansReportComponent', () => {
  let component: PerformansReportComponent;
  let fixture: ComponentFixture<PerformansReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PerformansReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PerformansReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
