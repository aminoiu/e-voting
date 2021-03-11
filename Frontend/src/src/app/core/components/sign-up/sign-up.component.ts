import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../services/auth/auth.service';
import {AlertService} from '../../services/alert/alert.service';
import {AdminDto} from '../../models/register-dto.model';
import {UserService} from '../../services/user/user.service';
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class  SignUpComponent implements OnInit {
  signUpForm: FormGroup;
  confirmationForm: FormGroup;
  showModal: boolean;
  submitted = false;
  showModalWithResult: boolean;
  private formValid = true;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private alertService: AlertService,
              @Inject(DOCUMENT) document) {

  }

  get f() {
    return this.signUpForm.controls;
  }

  get email() {
    return this.signUpForm.get('regEmail');
  }

  get password() {
    return this.signUpForm.get('password');
  }

  get sighUpForm() {
    return this.signUpForm;
  }

  ngOnInit(): void {
    this.signUpForm = this.formBuilder.group({
      full_name: ['', [Validators.required]],
      regEmail: ['', [Validators.required]],
      repeatEmail: ['', [Validators.required]],
      password: ['', [Validators.required]],
      repPassword: ['', [Validators.required]],
      birthDate: ['', [Validators.required]],
      country: ['', [Validators.required]],
      street: ['', [Validators.required]],
      city: ['', [Validators.required]],
      workPlace: ['', [Validators.required]],
      phoneNumber: ['', [Validators.required]]
    });
  }


  isFormValid() {
    return this.formValid;
  }

  onSubmit() {
  }

  sendRegisterData() {
    const registerDto: AdminDto =
      {
        name: this.f.full_name.value,
        workPlace: this.f.workPlace.value,
        email: this.f.regEmail.value,
        password: this.f.password.value,
        country: this.f.country.value,
        city: this.f.city.value,
        street: this.f.street.value,
        phoneNumber: this.f.phoneNumber.value,
        birthDate: this.f.birthDate.value,
        userId: 0
      };
    this.submitted = true;

    if (this.submitted) {
      this.showModal = false;
    }

    // TODO: this should be done when from server was send a confirmation response that fingerprint is registered
    this.userService.register(registerDto)
      .subscribe(message => {
          console.log(message);
          this.showModalWithResult = true;
          document.getElementById('responseHeader').innerText = 'Admin registered!';
        },
        error => {
          this.showModalWithResult = true;
          document.getElementById('responseHeader').innerText = error.error.message;
          console.log(error.error.message);
        });
      /*.toPromise()
      .then(response => {
        console.log(response);
        this.showModalWithResult = true;
        document.getElementById('responseHeader').innerText = response.;
      }
    ).catch(error => {
      this.showModalWithResult = true;
      document.getElementById('responseHeader').innerText = error.error.message;
      console.log(error.error.message);
    });*/
  }

  show() {
    this.showModal = true; // Show-Hide Modal Check

  }

  // Bootstrap Modal Close event
  hide() {
    this.showModal = false;
  }

  hideModalWithResults() {
    this.showModalWithResult = false;
    document.getElementById('responseHeader').innerText = '';
    this.router.navigate(['/']);
  }
}
