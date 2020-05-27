import { Injectable } from '@angular/core';
import {User} from "../../models/user";
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {map} from "rxjs/operators";
import {AlertService} from "../alert/alert.service";
import {LoginDto} from "../../models/login-dto";
import {HttpStatus} from "../../models/http-status.enum";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public currentUser: Observable<User>;
  authenticated = false;
  public readonly TOKEN_KEY: string = 'E-voting-SecretKeyToGenJWTs';
  private currentUserSubject: BehaviorSubject<User>;
  private alertService: AlertService;
  private role: string;
  private readonly BASE_URL: string = 'http://localhost:8080';
  private readonly SING_IN_URL: string = '';
  private readonly SING_UP_URL: string = '';
  private readonly EMAIL_VERIFICATION_URL;
  private router: Router;

  constructor(private http: HttpClient, router: Router) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  public get curentUserRole(): string {
    return this.role;
  }

  login(loginDTO: LoginDto) {

    this.getRole(loginDTO.email).toPromise()
      .then(role => {
        this.role = role;
        console.log(loginDTO.email + " has role: " + role);
      })
      .catch(error => {
        this.handleError(error, loginDTO.email);
      });


    return this.http.post<any>('http://localhost:8080/evoting/users/', loginDTO)
      .pipe(map(user => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        sessionStorage.setItem('currentUser', JSON.stringify(user));
        sessionStorage.setItem('userRole', this.role);
        this.currentUserSubject.next(user);
        this.authenticated = true;
        return user;
      }));

  }

  // logout() {
  //
  // }

  getToken() {

    let token: string = sessionStorage.getItem(this.TOKEN_KEY);
    if (token == null) {
      token = localStorage.getItem(this.TOKEN_KEY);
    }
    return token;
  }

  private getRole(email: string) {
    console.log("url: " + 'http://localhost:8080/evoting/users/{' + email + '}');
    return this.http.get<string>('http://localhost:8080/evoting/users/{' + email + '}');
  }

  private handleError(httpError: HttpErrorResponse, email: string) {
    if (httpError.status === HttpStatus.NOT_FOUND) {
      this.router.navigateByUrl('/login')
        .then(() => alert('User with email:' + email + ' does not exist.'))
        .catch(error => console.error('Something went wrong: ' + error));
    }

    if (httpError.status === HttpStatus.INTERNAL_SERVER_ERROR) {
      console.error('Something went wrong!');
    }

    if (httpError.status === HttpStatus.ERR_CONNECTION_REFUSED) {
      console.error('Something went wrong!');
    }
  }
}
