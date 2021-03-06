import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../services/alert/alert.service';
import {AuthService} from '../../services/auth/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {HttpStatus} from '../../models/http-status.enum';
import {LoginDto} from '../../models/login-dto.model';


@Component({templateUrl: 'login.component.html'})
export class LoginComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthService,
    private alertService: AlertService
  ) {
    // // redirect to home if already logged in
    // if (sessionStorage.getItem('currentUser')) {
    //   this.router.navigate(['/']);
    // }
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

  get f() {
    return this.loginForm.controls;
  }

  public get curentUserRole(): string {
    return this.role;
  }
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  private role: string;
  error: string;
  @ViewChild('info') inforef: ElementRef;

  // convenience getter for easy access to form fields

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    // this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {


    const loginDto: LoginDto = {
      email: this.f.email.value,
      password: this.f.password.value
    };

    console.log('login and pass: ' + loginDto.email + ', ' + loginDto.password);

    this.authenticationService.login(loginDto)
      .toPromise()
      .then(user => {
        console.log(user.token);
        sessionStorage.setItem('curentUserRole', user.roles[0]);
        sessionStorage.setItem(this.authenticationService.TOKEN_KEY, user.token);
        sessionStorage.setItem('currentUser', user.username);
        if (sessionStorage.getItem('curentUserRole') === 'ADMIN') // TODO: Enums for roles
        {
          this.router.navigate(['/evoting/admin']);
        } else if (sessionStorage.getItem('curentUserRole') === 'CANDIDATE') // TODO: Enums for roles
        {
          this.router.navigate(['/evoting/candidate']);
        } else if (sessionStorage.getItem('curentUserRole') === 'VOTER') // TODO: Enums for roles
        {
          this.router.navigate(['/evoting/voter']);
        }
        console.log('role: ' + sessionStorage.getItem('curentUserRole'));
      })
      .catch(error => {
        this.handleError(error);
      });
  }

  resetPassword() {
  }

  private handleError(httpError: HttpErrorResponse) {
    if (httpError.status === HttpStatus.NOT_FOUND) {
      this.email.setErrors({invalid: true});
      this.password.setErrors({invalid: true});
      this.inforef.nativeElement.innerText = 'Utilizatorul nu a fost găsit!';
    }
    if (httpError.status === HttpStatus.BAD_REQUESTS) {
      console.error(httpError.error);
      this.error = httpError.error;
      this.inforef.nativeElement.innerText = httpError.error;
    }
    if (httpError.status === HttpStatus.INTERNAL_SERVER_ERROR) {
      console.error('Ceva nu a mers bine!');
      this.inforef.nativeElement.innerText = 'Eroare internă de server!';
    }

    if (httpError.status === HttpStatus.ERR_CONNECTION_REFUSED) {
      console.error('Serverul poate fi deconectat');
      this.inforef.nativeElement.innerText = 'Eroare de conexiune!';
    }

    setTimeout(function() {  this.document.location.reload(); }, 3000);

  }
}
