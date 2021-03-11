import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {MatToolbarModule} from "@angular/material/toolbar";

import {AppRoutingModule} from './app-routing.module'; // CLI imports AppRoutingModule
import {AppComponent} from './app.component';
import {ViewMainComponent} from './core/components/home/view-main.component';
import {AppModuleAdmin} from "../../projects/Admin/src/app/app.module";
import {NavComponent} from './core/components/nav/nav.component';
import {MatMenuModule} from "@angular/material/menu";
import {LoginComponent} from './core/components/login/login.component';
import {AlertComponent} from './core/components/alert/alert.component';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatFormFieldModule} from "@angular/material/form-field";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {JwtInterceptor} from "./core/helpers/interceptor/jwt.interceptor";
import {ErrorInterceptor} from "./core/helpers/interceptor/error.interceptor";
import { SignUpComponent } from './core/components/sign-up/sign-up.component';
import {FooterComponent} from "./core/components/footer/footer.component";
import {AppModuleCandidate} from "../../projects/Candidate/src/app/app.module";
import {AppModuleVoter} from "../../projects/Voter/src/app/app.module";


@NgModule({
  declarations: [
    AppComponent,
    ViewMainComponent,
    NavComponent,
    LoginComponent,
    AlertComponent,
    SignUpComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    MatToolbarModule,
    AppRoutingModule,
    AppModuleAdmin.forRoot(),
    AppModuleCandidate.forRoot(),
    AppModuleVoter.forRoot(),
    MatMenuModule,
    MatDialogModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatFormFieldModule,
    FormsModule,
    NgbModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }, ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
