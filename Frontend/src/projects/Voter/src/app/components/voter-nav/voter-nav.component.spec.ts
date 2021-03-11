import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoterNavComponent } from './voter-nav.component';

describe('VoterNavComponent', () => {
  let component: VoterNavComponent;
  let fixture: ComponentFixture<VoterNavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoterNavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoterNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
