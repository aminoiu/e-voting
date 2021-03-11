import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, UrlTree} from '@angular/router';
import {AuthService} from "../../services/auth/auth.service";
import {MatDialog} from "@angular/material/dialog";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private auth: AuthService) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const currentUser = this.auth.currentUserValue;
    if (currentUser) {
      // authorised so return true
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
    return false;
  }
  // constructor(readonly auth: AuthService) {}

  /** Returns true whenever the user is authenticated */
  get authenticated() { return this.auth.authenticated; }

  /** Returns the current authenticated user id */
  get userId() { return this.auth.currentUserValue; }



  /** Performs the user authentication prompting the user when neeed or resolving to the current authenticated user otherwise */


  /** Disconnects the user */
  public disconnect(): void {
    return this.auth.logout();
  }
  //
  // // Implements single route user authentication guarding
  // canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
  //   return this.auth.authenticated;
  // }

}
