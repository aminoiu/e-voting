import {Component,  OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-voters',
  templateUrl: './add-voters.component.html',
  styleUrls: ['./add-voters.component.css']
})
export class AddVotersComponent implements OnInit {
voting_title:string;
  public form2: FormGroup;
  public formExport: FormGroup;
  public title:string;


  constructor(private formBuilder: FormBuilder, private router:Router) {
  }

  ngOnInit(): void {

    this.form2 = this.formBuilder.group(
      {title: ['', [Validators.required]],
        voting_type: ['', [Validators.required]],
        categories:[],
        start_date:[],
        start_time:[],
        end_date:[],
        end_time:[],
        add_voters:[],
        add_candidates:[]
      })
    console.log('Voting votingTitle: '+ sessionStorage.getItem('voting_title'));
    this.title=sessionStorage.getItem('voting_title');

  }

  continue1() {
  }

  onSubmit() {

  }
  getVotingTitle(){
    sessionStorage.getItem('voting_title');
  }

  back() {
    this.router.navigateByUrl('admin/create-voting-session');
  }
}
