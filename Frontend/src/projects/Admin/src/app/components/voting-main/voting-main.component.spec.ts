import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VotingMainComponent } from './voting-main.component';

describe('View2Component', () => {
  let component: VotingMainComponent;
  let fixture: ComponentFixture<VotingMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VotingMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VotingMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
