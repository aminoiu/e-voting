import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoterElectionsComponent } from './voter-elections.component';

describe('VoterElectionsComponent', () => {
  let component: VoterElectionsComponent;
  let fixture: ComponentFixture<VoterElectionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoterElectionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoterElectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
