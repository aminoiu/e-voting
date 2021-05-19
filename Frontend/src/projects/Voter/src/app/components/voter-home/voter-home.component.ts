import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {VoterService} from '../../services/Voter/voter.service';
import {DOCUMENT} from '@angular/common';
import {Router} from '@angular/router';
import {PassModel} from '../../../../../Candidate/src/app/model/Password/pass.model';
import {DynamicGrid} from '../../../../../Admin/src/app/model/DynamicGrid/dynamic-grid.model';
import {CastedVoteDto} from '../../model/CastedVote/casted-vote.dto';
import {HttpErrorResponse} from '@angular/common/http';
import {HttpStatus} from '../../../../../../src/app/core/models/http-status.enum';

@Component({
  selector: 'app-voter-home',
  templateUrl: './voter-home.component.html',
  styleUrls: ['./voter-home.component.css']
})
export class VoterHomeComponent implements OnInit {

  title = 'Admin view1';
  confirmationForm: FormGroup;
  showModal: boolean;
  codeForm: FormGroup;
  dynamicCandidatesArray: Array<DynamicGrid> = [];
  newDynamicCandidate: any = {};
  showCandidatesModal: boolean;
  submitVote: FormGroup;
  showModalWithResult: boolean;
  castedVoteDto: CastedVoteDto;
  private formValid = true;
  @ViewChild('info') inforef: ElementRef;

  constructor(private voterService: VoterService, @Inject(DOCUMENT) document,
              private formBuilder: FormBuilder, private router: Router) {
  }

  get f() {
    return this.confirmationForm.controls;
  }

  get vf() {
    return this.codeForm.controls;
  }

  get sv() {
    return this.submitVote.controls;
  }

  ngOnInit(): void {
    this.confirmationForm = this.formBuilder.group(
      {
        newPassword: ['', Validators.required]
      }
    );
    this.codeForm = this.formBuilder.group(
      {
        votingCode: ['', Validators.required]
      }
    );
    this.submitVote = this.formBuilder.group(
      {
        vote: ['', Validators.required]
      }
    );
    this.voterService.isTempPass(sessionStorage.getItem('currentUser'))
      .toPromise()
      .then(result => {
        if (result.temp === true) {
          this.showModal = true;
        } else if (result.temp === false) {
          this.router.navigate(['evoting/voter']);
          this.showModal = false;
        }
      });
    this.newDynamicCandidate = {title1: '', title2: '', title3: '', title4: '', title5: '', title6: '', title7: ''};
  }

  onSave() {
    const newPassF = this.f.newPassword.value;
    const passDto: PassModel = {
      newPass: newPassF
    };
    let pass: string;
    pass = newPassF.toString();
    this.voterService.updatePassword(sessionStorage.getItem('currentUser'), passDto)
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

  showCand() {
    this.showCandidatesModal = true; // Show-Hide Modal Check
  }

  hideCand() {
    this.router.navigate(['evoting/voter/home']);
    this.showCandidatesModal = false;
  }


  onSubmit() {
    this.voterService.getCandidatesProfileByVotingCode(this.vf.votingCode.value, sessionStorage.getItem('currentUser')).toPromise()
      .then(response => {
        this.showCandidatesModal = true;
        response.forEach(value => {
          this.addCandidateRow(value.name, value.email, value.position, value.education, value.selfDescription);

        });
      })
      .catch(error => {
        this.handleError(error);
      });
  }

  addCandidateRow(name: string, email: string, position: string, education: string, selfDescription: string) {
    console.log('name ' + name, 'email ' + email);
    this.newDynamicCandidate = {
      title1: name,
      title2: email,
      title3: position,
      title4: education,
      title5: selfDescription
    };
    this.dynamicCandidatesArray.push(this.newDynamicCandidate);
    console.log('New row added successfully', 'New Row');
    console.log(this.dynamicCandidatesArray);
    return true;
  }

  isEmptyC() {
    if (this.dynamicCandidatesArray.length < 1) {
      console.log('Table is empty');
      return true;
    } else {
      return false;
    }
  }

  onSaveCand() {
    const submittedVote = this.submitVote.get('vote').value;
  }

  castVote(index) {
    const submittedVote: CastedVoteDto = {
      castedVote: this.dynamicCandidatesArray[index].title2,
      voterEmail: sessionStorage.getItem('currentUser'),
      votingCode: this.vf.votingCode.value
    };
    this.voterService.castVote(submittedVote).toPromise()
      .then(response => {
        this.showModalWithResult = true;
        document.getElementById('responseHeader').innerText = 'Votul tău a fost salvat! Îți mulțumim!';
      })
      .catch(error => {
        this.showModalWithResult = true;
        document.getElementById('responseHeader').innerText = error;
        this.handleError(error);

      });
  }

  hideModalWithResults() {
    this.showModalWithResult = false;
    document.getElementById('responseHeader').innerText = '';
    this.router.navigateByUrl('/evoting/voter')
      .then(value => console.log('moved to dashboard'))
      .catch(reason => console.error('Could not navigate to dashboard:' + reason));
    this.hideCand();
  }


  private handleError(httpError: HttpErrorResponse) {
    console.error('Ceva nu a mers bine! ->' + httpError.message);

    if (httpError.status === HttpStatus.INTERNAL_SERVER_ERROR) {
      console.error('Ceva nu a mers bine!');
    }

    if (httpError.status === HttpStatus.ERR_CONNECTION_REFUSED) {
      console.error('Serverul poate fi deconectat!');
    }

    if (httpError.status === HttpStatus.BAD_REQUESTS) {
      console.error(httpError.error);
      this.inforef.nativeElement.innerText = httpError.error ;
      setTimeout(function() {  this.document.location.reload(); }, 6000);
    }
  }
}
