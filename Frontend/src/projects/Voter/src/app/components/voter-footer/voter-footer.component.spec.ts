import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoterFooterComponent } from './voter-footer.component';

describe('VoterFooterComponent', () => {
  let component: VoterFooterComponent;
  let fixture: ComponentFixture<VoterFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoterFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoterFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
