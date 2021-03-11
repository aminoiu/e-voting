import { BrowserModule } from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {CandidateHomeComponent} from './components/candidate-home/candidate-home.component';
import {FooterComponent} from './components/footer/footer.component';
import {NavComponent} from './components/nav/nav.component';
import {CandidateElectionsComponent} from './components/candidate-elections/candidate-elections.component';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    CandidateHomeComponent,
    FooterComponent,
    NavComponent,
    CandidateElectionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

@NgModule({})
export class AppModuleCandidate {

  static forRoot(): ModuleWithProviders<AppModuleCandidate> {

    return {
      ngModule: AppModule,
      providers: []
    };
  }
}
