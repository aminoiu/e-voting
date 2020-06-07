import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinalInformationComponent } from './final-information.component';

describe('FinalInformationComponent', () => {
  let component: FinalInformationComponent;
  let fixture: ComponentFixture<FinalInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinalInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinalInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
