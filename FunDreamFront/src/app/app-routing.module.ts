import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login.component';
import { NewIdeaComponent } from './ideas/new-idea/new-idea.component';

import { GuardService as guard} from './guards/guard.service';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './users/user.component';
import { NewUserComponent } from './register/new-user.component';
import { ShowIdeaComponent } from './ideas/show-idea/show-idea.component';
import { TransactionComponent } from './transaction/transaction.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: NewUserComponent},  
  {path: 'show-idea', component: ShowIdeaComponent},
  {path: 'show-idea/:id', component: ShowIdeaComponent},
  {path: 'new-idea', component: NewIdeaComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user']}},   
  {path: 'transaction/:id', component: TransactionComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user']}},
  {path: 'admin', component: AdminComponent, canActivate: [guard], data: {expectedRol: ['admin']}},
  {path: 'user', component: UserComponent, canActivate: [guard], data: {expectedRol: ['user']}},  
  //Redireccionamiento cuando se ingresa una ruta no valida 
  {path: "**", redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
