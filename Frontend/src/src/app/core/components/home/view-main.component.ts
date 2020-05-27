import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {LoginComponent} from "../login/login.component";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";
import {LocalizeFn} from "@angular/localize/init";

@Component({
  selector: 'app-view-main',
  templateUrl: './view-main.component.html',
  styleUrls: ['./view-main.component.css']
})
export class ViewMainComponent implements OnInit {

  private loginDialogV: MatDialogRef<LoginComponent>;
  private email: string;
  private password: string;
  closeResult = '';


  constructor(public router: Router) {
  }

  ngOnInit(): void {
  }


  login() {

    this.router.navigate(['/login'])
  }

  resetPassword() {

  }
}
