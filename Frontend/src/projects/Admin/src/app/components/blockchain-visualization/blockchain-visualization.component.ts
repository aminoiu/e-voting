import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {Router} from "@angular/router";
import {VotigDataService} from "../../services/VotingData/votig-data.service";
import {HttpErrorResponse} from "@angular/common/http";
import {HttpStatus} from "../../../../../../src/app/core/models/http-status.enum";
import {BlockchainService} from "../../services/BlockchainData/blockchain.service";
import {DynamicGrid} from "../../model/DynamicGrid/dynamic-grid.model";

interface VotingTitles {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-blockchain-visualization',
  templateUrl: './blockchain-visualization.component.html',
  styleUrls: ['./blockchain-visualization.component.css']
})
export class BlockchainVisualizationComponent implements OnInit {
  blocksArray: Array<DynamicGrid> = [];
  newDynamicBlock: any = {};

  form: FormGroup;
  options = Array<string>();

  titlesControl = new FormControl(this.options[1]);
  selected = '----';

  constructor(private formBuilder: FormBuilder, private router: Router, private votingDataService: VotigDataService, private blockchainService: BlockchainService) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      titles: new FormControl()
    });

    this.votingDataService.getVotingData(sessionStorage.getItem('currentUser')).toPromise()
      .then(response => {
        for (let i = 0; i < response.length; i++) {
          console.log(response[i]);
          this.options.push(response[i].votingTitle);
        }
      })
      .catch(error => {
        this.handleError(error);
      });

  }


  update(e) {
    this.selected = e.target.value;

    this.blockchainService.getBlockchainContent(e.target.value).toPromise()
      .then(response => {
        for (let i = 0; i < response.length; i++) {
          console.log(response[i]);
          this.newDynamicBlock  = {
            title1: response[i].chainId,
            title2: response[i].hash,
            title3: response[i].version,
            title4: response[i].hashPrev,
            title5: response[i].hashMerkleRoot,
            title6: response[i].transactionList.length
          };
          this.blocksArray.push(this.newDynamicBlock);
        }
      })
      .catch(error => {
        this.handleError(error);
      });

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
