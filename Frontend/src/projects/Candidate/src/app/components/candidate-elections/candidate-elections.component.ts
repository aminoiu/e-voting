import {Component, OnInit} from '@angular/core';
import {DynamicGrid} from '../../model/DynamicGrid/dynamic-grid.model';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {VotigDataService} from '../../services/VotingData/votig-data.service';
import {HttpStatus} from '../../../../../../src/app/core/models/http-status.enum';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
    selector: 'app-candidate-elections',
    templateUrl: './candidate-elections.component.html',
    styleUrls: ['./candidate-elections.component.css']
})
export class CandidateElectionsComponent implements OnInit {
    electionsArray: Array<DynamicGrid> = [];
    newDynamicElection: any = {};
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
                        title4: response[i].endDateAndTime,
                        title5: response[i].votingWinner
                    };
                    this.electionsArray.push(this.newDynamicElection);
                }
            })
            .catch(error => {
                this.handleError(error);
            });
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
