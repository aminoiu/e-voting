import { BrowserModule } from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { NewVotingComponent } from './components/new-voting/new-voting.component';
import { NavComponent } from './components/nav/nav.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminHomeComponent,
    NewVotingComponent,
    NavComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  exports: [
    NavComponent
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
}}
