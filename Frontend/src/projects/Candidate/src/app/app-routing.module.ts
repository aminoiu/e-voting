import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CandidateHomeComponent} from "./components/candidate-home/candidate-home.component";
import {CandidateElectionsComponent} from "./components/candidate-elections/candidate-elections.component";


const routes: Routes = [
  {path: 'evoting/candidate/one', component: CandidateHomeComponent},
  {path: 'evoting/candidate', redirectTo: 'evoting/candidate/one'},
  {path: 'evoting/candidate/elections', component: CandidateElectionsComponent},
  {path: 'evoting/candidate/home-admin', component: CandidateHomeComponent}, ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
