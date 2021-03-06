import {Component, Inject, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../../../../../src/app/core/services/auth/auth.service";
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-voter-nav',
  templateUrl: './voter-nav.component.html',
  styleUrls: ['./voter-nav.component.css']
})
export class VoterNavComponent implements OnInit {
  constructor(private router: Router,
              private authenticationService: AuthService,
              @Inject(DOCUMENT) document
  ) { }

  ngOnInit(): void {
    document.getElementById('userEmail').innerText = sessionStorage.getItem('currentUser');
  }

  logOut() {
    sessionStorage.removeItem('curentUserRole');
    sessionStorage.removeItem(this.authenticationService.TOKEN_KEY);
    sessionStorage.removeItem('currentUser');
    this.router.navigate(['/']);

  }
}
