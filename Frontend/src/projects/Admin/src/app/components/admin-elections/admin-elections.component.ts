import { Component, OnInit } from '@angular/core';
import {DynamicGrid} from "../../model/DynamicGrid/dynamic-grid.model";
import {FormBuilder, Validators} from "@angular/forms";
import {NewVotingService} from "../../services/NewVoting/new-voting.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-elections',
  templateUrl: './admin-elections.component.html',
  styleUrls: ['./admin-elections.component.css']
})
export class AdminElectionsComponent implements OnInit {
  electionsArray: Array<DynamicGrid> = [];

  newDynamicElection: any = {};

  participatedElectionsArray: Array<DynamicGrid> = [];

  newParticipatedDynamicElection: any = {};

  form2: any;
  main: any;
  form1: any;

  constructor(private formBuilder: FormBuilder, private router:Router) { }

  ngOnInit(): void {
    this.form1 = this.formBuilder.group(
      {
        election_actions:[]
      });
    this.newDynamicElection={title1: "Title Test", title2: "DateTime Test", title3:"Initial"};
    this.electionsArray.push(this.newDynamicElection);
    this.newDynamicElection={title1: "Title Test2", title2: "DateTime Test2", title3:"Finished"};
    this.electionsArray.push(this.newDynamicElection);


    this.newParticipatedDynamicElection={title1: "Title Test3", title2: "DateTime Test3", title3:"Finished"};
    this.participatedElectionsArray.push(this.newParticipatedDynamicElection);
    this.newParticipatedDynamicElection={title1: "Title Test3", title2: "DateTime Test3", title3:"In progress"};
    this.participatedElectionsArray.push(this.newParticipatedDynamicElection);

  }

  isEmptyV() {

  }

  onSubmit() {

  }
}
