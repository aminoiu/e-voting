import {Component, ElementRef, EventEmitter, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {DynamicGrid} from '../../model/DynamicGrid/dynamic-grid.model';
import {NewVotingDto} from '../../model/NewVoting/new-voting.dto';
import {NewVotingService} from '../../services/NewVoting/new-voting.service';
import {HttpErrorResponse} from '@angular/common/http';
import {HttpStatus} from '../../../../../../src/app/core/models/http-status.enum';
import {UploadFile, UploadInput, UploadOutput} from 'ng-uikit-pro-standard';
import {humanizeBytes} from 'ng-uikit-pro-standard';
import xml2js from 'xml2js';
import {Time} from '@angular/common';
import {Timestamp} from "rxjs/internal-compatibility";
import {StatusesEnum} from "../../../../../../src/app/core/models/statuses.enum";


@Component({
  selector: 'app-voting-main',
  templateUrl: './voting-main.component.html',
  styleUrls: ['./voting-main.component.css']
})
export class VotingMainComponent implements OnInit {
  public form1: FormGroup;
  public form2: FormGroup;
  public form3: FormGroup;
  showModal: boolean;
  confirmationForm: FormGroup;
  submitted = false;
  state = false;
  votersError = false;
  titleError = false;
  dynamicVotersArray: Array<DynamicGrid> = [];
  newDynamicVoter: any = {};
  dynamicCandidatesArray: Array<DynamicGrid> = [];
  newDynamicCandidate: any = {};
  candidateError: boolean;
  formData: FormData;
  files: UploadFile[];
  uploadInput: EventEmitter<UploadInput>;
  humanizeBytes: Function;
  dragOver: boolean;
  showAddFile = false;
  addFileHidden = true;
  addFile2Hidden = true;
  showModalWithResult: boolean;
  private formValid = true;
  @ViewChild('errorMessageVoters', {static: false}) private errorMessageVoters: ElementRef<HTMLElement>;
  @ViewChild('errorMessageTitle', {static: false}) private errorMessageTitle: ElementRef<HTMLElement>;

  @ViewChild('error_message_candidates', {static: false}) private error_message_candidates: ElementRef<HTMLElement>;
  @ViewChild('tableRowElement', {static: false}) private table_row: ElementRef<HTMLElement>;

  constructor(private formBuilder: FormBuilder, private newVotingService: NewVotingService, private router: Router) {
    this.files = [];
    this.uploadInput = new EventEmitter<UploadInput>();
    this.humanizeBytes = humanizeBytes;
  }

  get f1() {
    return this.form1.controls;
  }

  get f2() {
    return this.form2.controls;
  }

  get f3() {
    return this.form3.controls;
  }

  get f() {
    return this.confirmationForm.controls;
  }

  get title() {
    return this.form1.get('title');
  }

  ngOnInit(): void {
    this.form1 = this.formBuilder.group(
      {
        title: ['', [Validators.required]],
        voting_type: ['', [Validators.required]],
        categories: ['', [Validators.required]],
        start_date: ['', [Validators.required]],
        start_time: ['', [Validators.required]],
        end_date: ['', [Validators.required]],
        end_time: ['', [Validators.required]],
        add_voters: ['', [Validators.required]],
        add_candidates: ['', [Validators.required]]
      });
    this.form2 = this.formBuilder.group(
      {
        voter_name: ['', [Validators.required]],
        voter_email: ['', [Validators.required]],
        voter_name_table: [],
        voter_email_table: []
      });
    this.form3 = this.formBuilder.group(
      {
        candidate_name: ['', [Validators.required]],
        candidate_email: ['', [Validators.required]],
        candidate_name_table: [],
        candidate_email_table: []
      });
    this.confirmationForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });

    this.newDynamicVoter = {title1: '', title2: ''};
    this.newDynamicCandidate = {title1: '', title2: ''};

  }

  /*  continue1() {
      console.log("Title: " + this.f1.votingTitle.value)

      this.router.navigateByUrl('admin/create-voting-session/add-votersList');
    }*/

  onSubmit() {

  }

  onSubmitConfirmation() {
    this.validateForms();
    // tslint:disable-next-line:prefer-const
    let sDate = this.f1.start_date.value;
    // const timeS = this.f1.start_time.value;
    // const hoursS = timeS.substr(0, 2);
    // const minutesS = timeS.substr(3, 5);
    // startDate.setHours(hoursS, minutesS, 0);

    let eDate = this.f1.end_date.value;
    let sTime = this.f1.start_time.value;
    let eTime = this.f1.end_time.value;
    // const timeE = this.f1.end_time.value;
    // const hoursE = timeE.substr(0, 2);
    // const minutesE = timeE.substr(3, 5);
    // endDate.setHours(hoursE, minutesE, 0);
    const listOfVoters: Array<string> = new Array<string>();
    const listOfCandidates: Array<string> = new Array<string>();

    for (let i = 0; i < this.dynamicVotersArray.length; i++) {
      listOfVoters.push(this.dynamicVotersArray[i].title1 + ',' + this.dynamicVotersArray[i].title2);
    }

    for (let i = 0; i < this.dynamicCandidatesArray.length; i++) {
      listOfCandidates.push(this.dynamicCandidatesArray[i].title1 + ',' + this.dynamicCandidatesArray[i].title2);
    }
    const newVotingDto: NewVotingDto = {
      votingTitle: this.f1.title.value,
      votersNumber: this.getVotersNumber(),
      votesNumber: 0,
      votingWinner: '',
      candidatesNumber: this.getCandidatesNumber(),
      adminId: sessionStorage.getItem('currentUser'),
      startDate: sDate,
      endDate: eDate,
      startTime: sTime,
      endTime: eTime,
      voting_type: this.f1.voting_type.value,
      categories: this.f1.categories.value,
      status: ' ',
      votersList: listOfVoters,
      candidatesList: listOfCandidates
    };
    this.submitted = true;
    // stop here if form is invalid
    // if (this.confirmationForm.invalid) {
    //   return;
    // }
    if (this.submitted) {
      this.showModal = false;
    }
    this.newVotingService.startNewVoting(newVotingDto).toPromise()
      .then(response => {
        this.showModalWithResult = true;
        document.getElementById('responseHeader').innerText = 'Sesiunea de vot înregistrată cu succes!';
      })
      .catch(error => {
        this.showModalWithResult = true;
        document.getElementById('responseHeader').innerText = error.error.message;
        this.handleError(error);

      });
  }

  getVotersNumber() {
    return this.dynamicVotersArray.length;
  }

  getCandidatesNumber() {
    return this.dynamicCandidatesArray.length;
  }

  getStartDateTime() {
    return this.f1.start_date.value + ', ' + this.f1.start_time.value;
  }

  getEndDateTime() {
    return this.f1.end_date.value + ', ' + this.f1.end_time.value;
  }

  show() {
    this.showModal = true; // Show-Hide Modal Check

  }

  // Bootstrap Modal Close event
  hide() {
    this.showModal = false;
  }

  addVoter() {
    console.log('name ' + this.f2.voter_name.value);
    if (this.f2.voter_name.value === '' || this.f2.voter_name.value === null || this.f2.voter_email.value === '' || this.f2.voter_email.value === null) {
      this.errorMessageVoters.nativeElement.innerText = 'Input is required!';
      this.votersError = true;
    } else {
      this.addVoterRow(this.f2.voter_name.value, this.f2.voter_email.value);
      this.errorMessageVoters.nativeElement.innerText = '';
    }


  }


  /*
    goToPreviousStep() {
      this.previousStepClicked.emit();
    }

    goToNextStep() {
      this.nextStepClicked.emit();
    }*/

  addVoterRow(name: string, email: string) {
    console.log('name ' + name, 'email ' + email);
    this.newDynamicVoter = {title1: name, title2: email};
    this.dynamicVotersArray.push(this.newDynamicVoter);
    console.log('New row added successfully', 'New Row');
    console.log(this.dynamicVotersArray);
    this.f2.voter_name.reset();
    this.f2.voter_email.reset();
    return true;
  }

  deleteVoterRow(index) {
    this.dynamicVotersArray.splice(index, 1);
    console.warn('Row deleted successfully', 'Delete row');
    return true;

  }

  addCandidate() {
    console.log('name ' + this.f3.candidate_name.value);
    if (this.f3.candidate_name.value === '' || this.f3.candidate_name.value === null || this.f3.candidate_email.value === '' || this.f3.candidate_email.value === null) {
      this.error_message_candidates.nativeElement.innerText = 'Input is required!';
      this.candidateError = true;
    } else {
      this.addCandidateRow(this.f3.candidate_name.value, this.f3.candidate_email.value);
      this.error_message_candidates.nativeElement.innerText = '';
    }

  }

  addCandidateRow(name: string, email: string) {
    console.log('name ' + name, 'email ' + email);
    this.newDynamicCandidate = {title1: name, title2: email};
    this.dynamicCandidatesArray.push(this.newDynamicCandidate);
    console.log('New row added successfully', 'New Row');
    console.log(this.dynamicCandidatesArray);
    this.f3.candidate_name.reset();
    this.f3.candidate_email.reset();
    return true;
  }

  deleteCandidateRow(index) {
    this.dynamicCandidatesArray.splice(index, 1);
    console.warn('Row deleted successfully', 'Delete row');
    return true;

  }

  isAllowed = (optional) => {
    return optional === 0 ? true : this.state;
  }

  isNotEmpty() {
    if (this.dynamicVotersArray.length > 0) {
      this.state = true;
    }

  }

  isEmptyV() {
    if (this.dynamicVotersArray.length < 1) {
      console.log('Table is empty');
      return true;
    } else {
      return false;
    }
  }

  isEmptyC() {
    if (this.dynamicCandidatesArray.length < 1) {
      console.log('Table is empty');
      return true;
    } else {
      return false;
    }
  }

  isFormValid() {
    return this.formValid;
  }

  showFiles() {
    let files = '';
    for (let i = 0; i < this.files.length; i++) {
      files += this.files[i].name;
      if (!(this.files.length - 1 === i)) {
        files += ',';
      }
    }
    return files;
  }

  startUpload(): void {
    //
    // const event: UploadInput = {
    //   type: 'uploadAll',
    //   url: 'your-path-to-backend-endpoint',
    //   method: 'POST',
    //   data: {foo: 'bar'},
    // };
    // this.files = [];
    // this.uploadInput.emit(event);
  }

  cancelUpload(id: string): void {
    this.uploadInput.emit({type: 'cancel', id});
  }

  reset(): void {
    this.files = [];
  }

  onUploadOutput(output: UploadOutput | any): void {

    if (output.type === 'allAddedToQueue') {
    } else if (output.type === 'addedToQueue') {
      this.files.push(output.file); // add file to array when added
    } else if (output.type === 'uploading') {
      // update current data in files array for uploading file
      const index = this.files.findIndex(file => file.id === output.file.id);
      this.files[index] = output.file;
    } else if (output.type === 'removed') {
      // remove file from array when removed
      this.files = this.files.filter((file: UploadFile) => file !== output.file);
    } else if (output.type === 'dragOver') {
      this.dragOver = true;
    } else if (output.type === 'dragOut') {
    } else if (output.type === 'drop') {
      this.dragOver = false;
    }
    this.showFiles();
  }

  // For candidatesList
  showFilesCandidates() {
    let files = '';
    for (let i = 0; i < this.files.length; i++) {
      files += this.files[i].name;
      if (!(this.files.length - 1 === i)) {
        files += ',';
      }
    }
    return files;
  }

  startUploadCandidates(): void {
    const event: UploadInput = {
      type: 'uploadAll',
      url: 'your-path-to-backend-endpoint',
      method: 'POST',
      data: {foo: 'bar'},
    };
    this.files = [];
    this.uploadInput.emit(event);
  }

  cancelUploadCandidates(id: string): void {
    this.uploadInput.emit({type: 'cancel', id});
  }

  resetCandidates(): void {
    this.files = [];
  }

  onUploadOutputCandidates(output: UploadOutput | any): void {

    if (output.type === 'allAddedToQueue') {
    } else if (output.type === 'addedToQueue') {
      this.files.push(output.file); // add file to array when added
    } else if (output.type === 'uploading') {
      // update current data in files array for uploading file
      const index = this.files.findIndex(file => file.id === output.file.id);
      this.files[index] = output.file;
    } else if (output.type === 'removed') {
      // remove file from array when removed
      this.files = this.files.filter((file: UploadFile) => file !== output.file);
    } else if (output.type === 'dragOver') {
      this.dragOver = true;
    } else if (output.type === 'dragOut') {
    } else if (output.type === 'drop') {
      this.dragOver = false;
    }
    this.showFiles();
  }

  onOptionsSelected(type: string, value: string) {
    console.log('The selected value is ' + value);
    if (type === 'Voters') {
      if (value === 'Import') {
        this.addFileHidden = false;
      } else {
        this.addFileHidden = true;
      }
    } else if (type === 'Candidates') {
      if (value === 'Import') {
        this.addFile2Hidden = false;
      } else {
        this.addFile2Hidden = true;
      }
    }
  }

  hideModalWithResults() {
    this.showModalWithResult = false;
    document.getElementById('responseHeader').innerText = '';
    this.router.navigateByUrl('/evoting/admin')
      .then(value => console.log('moved to dashboard'))
      .catch(reason => console.error('Could not navigate to dashboard:' + reason));
  }

  private setStatus() {
    sessionStorage.setItem(this.f1.title.value, StatusesEnum.IN_PROGRESS_STATUS);
    return StatusesEnum.IN_PROGRESS_STATUS;
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

  private validateForms() {
    if (this.f1.title.value === '' || this.f1.title.value === null) {
      console.log('Input is required for votingTitle!');
      this.errorMessageTitle.nativeElement.innerText = 'Input is required!';
      this.titleError = true;
    } else {
      console.log('Title provided!');
      this.errorMessageTitle.nativeElement.innerText = '';
    }
  }

}
