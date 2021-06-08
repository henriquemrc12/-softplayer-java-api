import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PersonComponent } from './person/person.component';


const routes: Routes = [
  { path: '', component: PersonComponent },
  { path: 'login', component: LoginComponent },
  { path: 'person', component: PersonComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
