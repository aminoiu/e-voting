import { TestBed } from '@angular/core/testing';

import { ErrorInterceptor } from './error.interceptor';
import {HttpClient, HttpHandler} from "@angular/common/http";
import {Router} from "@angular/router";

describe('ErrorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      ErrorInterceptor,
      HttpClient,
      HttpHandler,
      Router
      ]
  }));

  it('should be created', () => {
    const interceptor: ErrorInterceptor = TestBed.inject(ErrorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
