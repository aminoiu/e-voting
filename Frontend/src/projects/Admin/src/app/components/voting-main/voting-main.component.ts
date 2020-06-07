import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {DynamicGrid} from "../../model/DynamicGrid/dynamic-grid.model";
import {NewVotingDto} from "../../model/NewVoting/new-voting.dto";
import {NewVotingService} from "../../services/NewVoting/new-voting.service";
import {HttpErrorResponse} from "@angular/common/http";
import {HttpStatus} from "../../../../../../src/app/core/models/http-status.enum";

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
  state: boolean = false;
  voters_error: boolean = false;
  dynamicVotersArray: Array<DynamicGrid> = [];
  newDynamicVoter: any = {};
  dynamicCandidatesArray: Array<DynamicGrid> = [];
  newDynamicCandidate: any = {};
  candidate_error: boolean;
  private formValid: boolean = true;
  @ViewChild('error_message_v', {static: false}) private error_message_v: ElementRef<HTMLElement>;
  @ViewChild('error_message_c', {static: false}) private error_message_c: ElementRef<HTMLElement>;
  @ViewChild('tableRowElement', {static: false}) private table_row: ElementRef<HTMLElement>;

  constructor(private formBuilder: FormBuilder, private newVotingService: NewVotingService, private router:Router) {
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

  ngOnInit(): void {
    this.form1 = this.formBuilder.group(
      {
        title: ['', [Validators.required]],
        voting_type: ['', [Validators.required]],
        categories: [],
        start_date: [],
        start_time: [],
        end_date: [],
        end_time: [],
        add_voters: [],
        add_candidates: []
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

    this.newDynamicVoter = {title1: "", title2: ""};
    this.newDynamicCandidate = {title1: "", title2: ""};

  }

  /*  continue1() {
      console.log("Title: " + this.f1.title.value)

      this.router.navigateByUrl('admin/start-new-voting/add-voters');
    }*/

  onSubmit() {

  }

  onSubmitConfirmation() {
    const newVotingDto: NewVotingDto = {
      title: this.f1.title.value,
      voting_type: this.f1.voting_type.value,
      categories: this.f1.categories.value,
      voting_start_date: this.f1.start_date.value,
      voting_start_time: this.f1.start_time.value,
      voting_end_date: this.f1.end_date.value,
      voting_end_time: this.f1.end_time.value,
      voters: this.dynamicVotersArray,
      candidates: this.dynamicCandidatesArray
    };

    this.submitted = true;
    // stop here if form is invalid
    if (this.confirmationForm.invalid) {
      return;
    }
    if (this.submitted) {
      this.showModal = false;
    }
    this.newVotingService.startNewVoting(newVotingDto).toPromise()
      .then(response => {
      this.router.navigateByUrl('/admin')
        .then(value => console.log('moved to dashboard'))
        .catch(reason => console.error('Could not navigate to dashboard:' + reason));
    })
      .catch(error => {
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
    return this.f1.start_date.value + ", " + this.f1.start_time.value;
  }

  getEndDateTime() {
    return this.f1.end_date.value + ", " + this.f1.end_time.value;
  }

  show() {
    this.showModal = true; // Show-Hide Modal Check

  }

  //Bootstrap Modal Close event
  hide() {
    this.showModal = false;
  }


  /*
    goToPreviousStep() {
      this.previousStepClicked.emit();
    }

    goToNextStep() {
      this.nextStepClicked.emit();
    }*/

  addVoter() {
    console.log("name " + this.f2.voter_name.value);
    if (this.f2.voter_name.value === "" || this.f2.voter_name.value === null || this.f2.voter_email.value === "" || this.f2.voter_email.value === null) {
      this.error_message_v.nativeElement.innerText = "Input is required!";
      this.voters_error = true;
    } else {
      this.addVoterRow(this.f2.voter_name.value, this.f2.voter_email.value);
      this.error_message_v.nativeElement.innerText = "";
    }

  }

  addVoterRow(name: string, email: string) {
    console.log("name " + name, "email " + email);
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
    console.log("name " + this.f3.candidate_name.value);
    if (this.f3.candidate_name.value === "" || this.f3.candidate_name.value === null || this.f3.candidate_email.value === "" || this.f3.candidate_email.value === null) {
      this.error_message_c.nativeElement.innerText = "Input is required!";
      this.candidate_error = true;
    } else {
      this.addCandidateRow(this.f3.candidate_name.value, this.f3.candidate_email.value);
      this.error_message_c.nativeElement.innerText = "";
    }

  }

  addCandidateRow(name: string, email: string) {
    console.log("name " + name, "email " + email);
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
    if (this.dynamicVotersArray.length > 0) this.state = true;

  }

  isEmptyV() {
    if (this.dynamicVotersArray.length < 1) {
      console.log("Table is empty");
      return true;
    } else return false;
  }

  isEmptyC() {
    if (this.dynamicCandidatesArray.length < 1) {
      console.log("Table is empty");
      return true;
    } else return false;
  }

  isFormValid() {
    return this.formValid;
  }


  private handleError(httpError: HttpErrorResponse) {
    console.error('Something went wrong! ->'+httpError.message);

    if (httpError.status === HttpStatus.INTERNAL_SERVER_ERROR) {
      console.error('Something went wrong!');
    }

    if (httpError.status === HttpStatus.ERR_CONNECTION_REFUSED) {
      console.error('Servers might be down');
    }
  }
}


