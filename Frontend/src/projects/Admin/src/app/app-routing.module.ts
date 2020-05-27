import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NewVotingComponent} from "./components/new-voting/new-voting.component";
import {AdminHomeComponent} from "./components/admin-home/admin-home.component";


const routes: Routes = [
  { path: 'admin/one', component: AdminHomeComponent },
  { path: 'admin/start-new-voting', component: NewVotingComponent },
  { path: 'admin', redirectTo: 'admin/one' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
