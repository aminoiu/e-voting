import { Component, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { $animations } from '../login/login-animations';
import { $pages } from '../login/login-pages';
import { from } from 'rxjs';
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";

export type loginAction = 'register'|'signIn'|'forgotPassword'|'changePassword'|'changeEmail'|'delete'|'signOut';

@Component({
  selector : 'wm-login',
  templateUrl : '../login/login.component.html',
  styleUrls : ['./login.component.scss'],
  animations: $animations
})
export class LoginComponent {
  closeResult = '';

  constructor(private modalService: NgbModal) {}

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}

