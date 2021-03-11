import { Component, OnInit } from '@angular/core';
import {DynamicGrid} from "../../model/DynamicGrid/dynamic-grid.model";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {VotigDataService} from "../../services/VotingData/votig-data.service";
import {HttpErrorResponse} from "@angular/common/http";
import {HttpStatus} from "../../../../../../src/app/core/models/http-status.enum";

@Component({
  selector: 'app-voter-elections',
  templateUrl: './voter-elections.component.html',
  styleUrls: ['./voter-elections.component.css']
})
export class VoterElectionsComponent implements OnInit {

  electionsArray: Array<DynamicGrid> = [];

  newDynamicElection: any = {};

  participatedElectionsArray: Array<DynamicGrid> = [];

  newParticipatedDynamicElection: any = {};

  form2: any;
  main: any;
  form1: any;

  constructor(private formBuilder: FormBuilder, private router: Router, private votingDataService: VotigDataService) {
  }

  ngOnInit(): void {
    this.form1 = this.formBuilder.group(
      {
        election_actions: []
      });
    this.votingDataService.getVotingData(sessionStorage.getItem('currentUser')).toPromise()
      .then(response => {
        for (let i = 0; i < response.length; i++) {
          console.log(response[i]);
          this.newDynamicElection = {
            title1: response[i].votingTitle,
            title2: response[i].startDateAndTime,
            title3: response[i].status,
            title4: response[i].endDateAndTime
          };
          this.electionsArray.push(this.newDynamicElection);
        }
      })
      .catch(error => {
        this.handleError(error);
      });



    /*this.newParticipatedDynamicElection = {
      title1: "Title Test3",
      title2: "DateTime Test3",
      title3: "Finished",
      title4: ' '
    };
    this.participatedElectionsArray.push(this.newParticipatedDynamicElection);
    this.newParticipatedDynamicElection = {
      title1: "Title Test3",
      title2: "DateTime Test3",
      title3: "In progress",
      title4: ' '
    };
    this.participatedElectionsArray.push(this.newParticipatedDynamicElection);*/

  }

  isEmptyV() {

  }

  onSubmit() {

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
