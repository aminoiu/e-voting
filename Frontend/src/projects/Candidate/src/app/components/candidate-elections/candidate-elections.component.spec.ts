import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateElectionsComponent } from './candidate-elections.component';

describe('AdminElectionsComponent', () => {
  let component: CandidateElectionsComponent;
  let fixture: ComponentFixture<CandidateElectionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidateElectionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidateElectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
