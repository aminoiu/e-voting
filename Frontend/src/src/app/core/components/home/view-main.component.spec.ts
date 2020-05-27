import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMainComponent } from './view-main.component';
import {Router} from "@angular/router";
import {RouterTestingModule} from "@angular/router/testing";

describe('ViewMainComponent', () => {
  let component: ViewMainComponent;
  let fixture: ComponentFixture<ViewMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers:[Router,RouterTestingModule],
      declarations: [ ViewMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
