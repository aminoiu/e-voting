import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import {AlertService} from "../../services/alert/alert.service";
import {AuthService} from "../../services/auth/auth.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {LoginDto} from "../../models/login-dto";
import {HttpErrorResponse} from "@angular/common/http";
import {HttpStatus} from "../../models/http-status.enum";



@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthService,
    private alertService: AlertService
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  get email() { return this.loginForm.get('email'); }

  get password() { return this.loginForm.get('password'); }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required,Validators.email]],
      password: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    // this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields

  get f() { return this.loginForm.controls; }

  onSubmit() {


    const loginDto: LoginDto = {
      email: this.f.email.value,
      password: this.f.password.value};

    console.log("login and pass: "+loginDto.email, loginDto.password);

    this.authenticationService.login(loginDto)
      .toPromise()
      .then(tokenDto=>{
        sessionStorage.setItem(this.authenticationService.TOKEN_KEY, tokenDto.token);
        if (sessionStorage.getItem(this.authenticationService.curentUserRole)==="Admin") //TODO: Enums for roles
        {this.router.navigate(['/admin'])}
      })
      .catch(error => {
        this.handleError(error);
      });
  }

  resetPassword() {

  }

  private handleError(httpError: HttpErrorResponse) {
    if (httpError.status === HttpStatus.NOT_FOUND) {
      this.email.setErrors({ invalid: true });
      this.password.setErrors({ invalid: true });
    }

    if (httpError.status === HttpStatus.INTERNAL_SERVER_ERROR) {
      console.error('Something went wrong!');
    }

    if (httpError.status === HttpStatus.ERR_CONNECTION_REFUSED) {
      console.error('Servers might be down');
    }
  }
}
