import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {CandidateService} from '../../services/Candidate/candidate.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProfileDto} from '../../model/Profile/profile.dto';
import {Router} from '@angular/router';
import {DOCUMENT} from '@angular/common';
import {PassModel} from '../../model/Password/pass.model';

@Component({
  selector: 'app-view1',
  templateUrl: './candidate-home.component.html',
  styleUrls: ['./candidate-home.component.css']
})
export class CandidateHomeComponent implements OnInit {
  title = 'Candidate view1';
  confirmationForm: FormGroup;
  showModal: boolean;
  private formValid = true;

  constructor(private candidateService: CandidateService, @Inject(DOCUMENT) document,
              private formBuilder: FormBuilder, private router: Router) {
  }

  get f() {
    return this.confirmationForm.controls;
  }

  ngOnInit(): void {
    this.confirmationForm = this.formBuilder.group(
      {
        newPassword: ['', Validators.required]
      }
    );
    this.candidateService.isTempPass(sessionStorage.getItem('currentUser'))
      .toPromise()
      .then(result => {
        if (result.temp === true) {
          this.showModal = true;
        }else if (result.temp === false) {
          this.router.navigate(['evoting/candidate']);
          this.showModal = false;
        }
      });
  }

  onSave() {
    const newPassF = this.f.newPassword.value;
    const passDto: PassModel = {
      newPass: newPassF
    };
    let pass: string;
    pass = newPassF.toString();
    this.candidateService.updatePassword(sessionStorage.getItem('currentUser'), passDto)
      .toPromise()
      .then(result => {
        this.router.navigate(['login']);
        this.showModal = false;
      })
      .catch(error => {
        console.log(error);
      });
  }

  show() {
    this.showModal = true; // Show-Hide Modal Check
  }

  hide() {
    this.router.navigate(['/']);
    this.showModal = false;
  }
}
