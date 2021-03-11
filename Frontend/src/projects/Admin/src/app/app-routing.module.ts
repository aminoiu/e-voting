import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {VotingMainComponent} from "./components/voting-main/voting-main.component";
import {AdminHomeComponent} from "./components/admin-home/admin-home.component";
import {AddVotersComponent} from "./components/add-voters/add-voters.component";
import {AddCandidatesComponent} from "./components/add-candidates/add-candidates.component";
import {FinalInformationComponent} from "./components/final-information/final-information.component";
import {MainRootComponent} from "./components/main-root/main-root.component";
import {AdminElectionsComponent} from "./components/admin-elections/admin-elections.component";


const routes: Routes = [
  { path: 'evoting/admin/one', component: AdminHomeComponent },
  { path: 'evoting/admin', redirectTo: 'evoting/admin/one' },
  { path: 'evoting/admin/create-voting-session', component: VotingMainComponent },
/*  { path: 'admin/create-voting-session/main-info', component: VotingMainComponent },
  { path: 'admin/create-voting-session/add-votersList', component: AddVotersComponent },
  { path: 'admin/create-voting-session/add-candidatesList', component: AddCandidatesComponent },*/
  // { path: 'admin/create-voting-session/final-information', component: FinalInformationComponent },
  { path: 'evoting/admin/create-voting-session/**', component: VotingMainComponent },
  { path: 'evoting/admin/elections', component: AdminElectionsComponent },
  {path: 'evoting/admin/home-admin', component: AdminHomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
