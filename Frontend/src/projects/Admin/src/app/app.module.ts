import {BrowserModule} from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AdminHomeComponent} from './components/admin-home/admin-home.component';
import {VotingMainComponent} from './components/voting-main/voting-main.component';
import {NavComponent} from './components/nav/nav.component';
import {FooterComponent} from './components/footer/footer.component';
import {AddCandidatesComponent} from './components/add-candidates/add-candidates.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MainRootComponent } from './components/main-root/main-root.component';
import { AddVotersComponent } from './components/add-voters/add-voters.component';
import { FinalInformationComponent } from './components/final-information/final-information.component';
import {FormDataService} from "./services/FormData/form-data.service";
import {NavbarComponent} from "./components/navbar/navbar.component";
import {MatStepperModule} from "@angular/material/stepper";
import {MatTabsModule} from "@angular/material/tabs";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {NewVotingModule} from "./module/new-voting/new-voting.module";
import {FormatTitlePipe} from "./pipes/format-title";
import { AdminElectionsComponent } from './components/admin-elections/admin-elections.component';
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  declarations: [
    AppComponent,
    AdminHomeComponent,
    NavComponent,
    FooterComponent,
    MainRootComponent,
    VotingMainComponent,
    AddCandidatesComponent,
    AddVotersComponent,
    FinalInformationComponent,
    NavbarComponent,
  FormatTitlePipe,
  AdminElectionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatFormFieldModule,
    FormsModule, ReactiveFormsModule, MatStepperModule, MatTabsModule, MatIconModule, MatInputModule, NewVotingModule, NgbDropdownModule
  ],
  providers: [{ provide: FormDataService, useClass: FormDataService }],
  exports: [

  ],

  bootstrap: [AppComponent]
})
export class AppModule {


}

@NgModule({})
export class AppModuleAdmin {

  static forRoot(): ModuleWithProviders<AppModuleAdmin> {

    return {
      ngModule: AppModule,
      providers: []
    }
  }
}
