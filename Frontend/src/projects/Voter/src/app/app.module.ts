import { BrowserModule } from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ReactiveFormsModule} from '@angular/forms';
import {VoterNavComponent} from './components/voter-nav/voter-nav.component';
import {VoterFooterComponent} from './components/voter-footer/voter-footer.component';
import {VoterHomeComponent} from './components/voter-home/voter-home.component';
import {VoterElectionsComponent} from './components/voter-elections/voter-elections.component';
import {MatRadioModule} from "@angular/material/radio";

@NgModule({
  declarations: [
    AppComponent,
    VoterNavComponent,
    VoterFooterComponent,
    VoterHomeComponent,
    VoterElectionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatRadioModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

@NgModule({})
export class AppModuleVoter {

  static forRoot(): ModuleWithProviders<AppModuleVoter> {

    return {
      ngModule: AppModule,
      providers: []
    };
  }
}
