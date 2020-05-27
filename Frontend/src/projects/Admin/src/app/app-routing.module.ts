import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {View2Component} from "./components/view2/view2.component";
import {View1Component} from "./components/view1/view1.component";


const routes: Routes = [
  { path: 'admin/one', component: View1Component },
  { path: 'admin/start-new-voting', component: View2Component },
  { path: 'admin', redirectTo: 'admin/one' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
