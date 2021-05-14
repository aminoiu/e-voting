import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {VotingMainComponent} from './components/voting-main/voting-main.component';
import {AdminHomeComponent} from './components/admin-home/admin-home.component';
import {AdminElectionsComponent} from './components/admin-elections/admin-elections.component';
import {BlockchainVisualizationComponent} from './components/blockchain-visualization/blockchain-visualization.component';


const routes: Routes = [
  { path: 'evoting/admin/one', component: AdminHomeComponent },
  { path: 'evoting/admin', redirectTo: 'evoting/admin/one' },
  { path: 'evoting/admin/create-voting-session', component: VotingMainComponent },
  { path: 'evoting/admin/create-voting-session/**', component: VotingMainComponent },
  { path: 'evoting/admin/elections', component: AdminElectionsComponent },
  {path: 'evoting/admin/home-admin', component: AdminHomeComponent},
  {path: 'evoting/admin/blockchain', component: BlockchainVisualizationComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
