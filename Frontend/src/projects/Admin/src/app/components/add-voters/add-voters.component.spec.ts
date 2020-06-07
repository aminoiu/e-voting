import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVotersComponent } from './add-voters.component';

describe('AddVotersComponent', () => {
  let component: AddVotersComponent;
  let fixture: ComponentFixture<AddVotersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddVotersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddVotersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
