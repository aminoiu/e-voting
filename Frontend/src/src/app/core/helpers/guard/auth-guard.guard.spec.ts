import { TestBed } from '@angular/core/testing';
import {AuthGuard} from "./auth-guard.guard";
import {HttpClient, HttpHandler} from "@angular/common/http";
import {Router} from "@angular/router";



describe('AuthGuardGuard', () => {
  let guard: AuthGuard

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers:[HttpClient,HttpHandler,Router]
    });
    guard = TestBed.inject(AuthGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
