import { TestBed } from '@angular/core/testing';

import { AlertService } from './alert.service';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

describe('AlertService', () => {
  let service: AlertService;

  beforeEach(() => {
    TestBed.configureTestingModule({providers: [
        HttpClient,
        Router
      ]});
    service = TestBed.inject(AlertService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
