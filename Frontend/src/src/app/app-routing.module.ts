import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router'; // CLI imports router
import {AppModuleAdmin} from '../../projects/Admin/src/app/app.module';
import {LoginComponent} from './core/components/login/login.component';
import {AppComponent} from './app.component';
import {ViewMainComponent} from './core/components/home/view-main.component';
import {AuthGuard} from './core/helpers/guard/auth-guard.guard';
import {SignUpComponent} from './core/components/sign-up/sign-up.component';
import {AppModuleCandidate} from "../../projects/Candidate/src/app/app.module";
import {AppModuleVoter} from "../../projects/Voter/src/app/app.module";


const routes: Routes = [
  {
    path: 'admin',
    loadChildren: '../../projects/Admin/src/app/app.module#AppModuleAdmin', canActivate: [AuthGuard]
  },
  {
    path: 'candidate',
    loadChildren: '../../projects/Candidate/src/app/app.module#AppModuleCandidate', canActivate: [AuthGuard]
  },
  {
    path: 'voter',
    loadChildren: '../../projects/Voter/src/app/app.module#AppModuleVoter', canActivate: [AuthGuard]
  },
  /* {path: 'admin',
     loadChildren: '../../projects/app2/src/app/app.module#App2SharedModule'},*/
  {path: 'login', component: LoginComponent},
  {path: 'register', component: SignUpComponent},
  {path: '', component: ViewMainComponent}];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    AppModuleAdmin.forRoot(),
    AppModuleCandidate.forRoot(),
  AppModuleVoter.forRoot()],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
