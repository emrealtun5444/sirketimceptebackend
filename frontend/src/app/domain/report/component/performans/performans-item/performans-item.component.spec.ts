import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerformansItemComponent } from './performans-item.component';

describe('PerformansItemComponent', () => {
  let component: PerformansItemComponent;
  let fixture: ComponentFixture<PerformansItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PerformansItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PerformansItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
