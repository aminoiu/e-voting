import {ModuleWithProviders, NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CandidateHomeComponent} from '../../../Candidate/src/app/components/candidate-home/candidate-home.component';
import {CandidateElectionsComponent} from '../../../Candidate/src/app/components/candidate-elections/candidate-elections.component';
import {VoterHomeComponent} from './components/voter-home/voter-home.component';
import {VoterElectionsComponent} from './components/voter-elections/voter-elections.component';
import {AppModule} from './app.module';


const routes: Routes = [
  { path: 'evoting/voter/one', component: VoterHomeComponent },
  { path: 'evoting/voter', redirectTo: 'evoting/voter/one' },
  { path: 'evoting/voter/elections', component: VoterElectionsComponent },
  { path: 'evoting/voter/home', component: VoterHomeComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

