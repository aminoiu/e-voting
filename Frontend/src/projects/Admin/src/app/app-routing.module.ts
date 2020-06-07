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
  { path: 'admin/one', component: AdminHomeComponent },
  { path: 'admin', redirectTo: 'admin/one' },
  { path: 'admin/start-new-voting', component: VotingMainComponent },
/*  { path: 'admin/start-new-voting/main-info', component: VotingMainComponent },
  { path: 'admin/start-new-voting/add-voters', component: AddVotersComponent },
  { path: 'admin/start-new-voting/add-candidates', component: AddCandidatesComponent },*/
  // { path: 'admin/start-new-voting/final-information', component: FinalInformationComponent },
  { path: 'admin/start-new-voting/**', component: VotingMainComponent },
  { path: 'admin/elections', component: AdminElectionsComponent },
  {path:'admin/home-admin', component:AdminHomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
