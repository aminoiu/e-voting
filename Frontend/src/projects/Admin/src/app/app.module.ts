import { BrowserModule } from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { View1Component } from './components/view1/view1.component';
import { View2Component } from './components/view2/view2.component';
import { NavComponent } from './components/nav/nav.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    View1Component,
    View2Component,
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
