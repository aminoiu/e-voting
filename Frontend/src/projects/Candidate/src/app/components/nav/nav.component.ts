import {Component, Inject, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from '../../../../../../src/app/core/services/auth/auth.service';
import {DOCUMENT} from "@angular/common";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NewVotingService} from "../../../../../Admin/src/app/services/NewVoting/new-voting.service";
import {ProfileDto} from "../../model/Profile/profile.dto";
import {ProfileService} from "../../services/Profile/profile.service";
import {error} from "@angular/compiler/src/util";
import {HttpErrorResponse} from "@angular/common/http";
import {HttpStatus} from "../../../../../../src/app/core/models/http-status.enum";

@Component({
  selector: 'candidate-app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  confirmationForm: FormGroup;
  showModal: boolean;
  private formValid = true;

  constructor(private router: Router,
              private authenticationService: AuthService,
              private profileService: ProfileService,
              @Inject(DOCUMENT) document,
              private formBuilder: FormBuilder
  ) {
  }

  get f() {
    return this.confirmationForm.controls;
  }

  ngOnInit(): void {
    document.getElementById('userEmail').innerText = sessionStorage.getItem('currentUser');
    this.confirmationForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  logOut() {
    sessionStorage.removeItem('curentUserRole');
    sessionStorage.removeItem(this.authenticationService.TOKEN_KEY);
    sessionStorage.removeItem('currentUser');
    this.router.navigate(['/']);

  }

  onSave() {

    const profileDto: ProfileDto = {
      name: document.getElementById('name').innerText,
      email: document.getElementById('email').innerText,
      position: document.getElementById('position').innerText,
      education: document.getElementById('education').innerText,
      selfDescription: document.getElementById('selfDescription').innerText
    };
    this.profileService.updateProfile(sessionStorage.getItem('currentUser'), profileDto)
      .toPromise()
      .then(result => {
        console.log('Profile updated: ' + result);
        this.showModal = false;
      })
      .catch(error => {
        this.handleError(error);
      });
  }

  isFormValid() {
    return this.formValid;
  }

  show() {
    this.showModal = true; // Show-Hide Modal Check
    this.profileService.getProfile(sessionStorage.getItem('currentUser'))
      .toPromise()
      .then(result => {
        document.getElementById('name').innerText = result.name;
        document.getElementById('email').innerText = result.email;
        document.getElementById('position').innerText = result.position;
        document.getElementById('education').innerText = result.education;
        document.getElementById('selfDescription').innerText = result.selfDescription;
      })
      .catch(error => {
        this.handleError(error);
      });

  }

  // Bootstrap Modal Close event
  hide() {
    this.showModal = false;
  }

  private handleError(httpError: HttpErrorResponse) {
    console.error('Something went wrong! ->' + httpError.message);

    if (httpError.status === HttpStatus.INTERNAL_SERVER_ERROR) {
      console.error('Something went wrong!');
    }

    if (httpError.status === HttpStatus.ERR_CONNECTION_REFUSED) {
      console.error('Servers might be down');
    }
  }
}
