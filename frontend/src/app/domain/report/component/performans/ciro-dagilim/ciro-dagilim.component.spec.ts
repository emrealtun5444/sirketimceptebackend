import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CiroDagilimComponent } from './ciro-dagilim.component';

describe('CiroDagilimComponent', () => {
  let component: CiroDagilimComponent;
  let fixture: ComponentFixture<CiroDagilimComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CiroDagilimComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CiroDagilimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
