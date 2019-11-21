import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { primengConfig } from './primengConfig'
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './auth/login.component';
import { HomeComponent } from './home/home.component';
import { interceptorProvider } from './interceptors/interceptor.service';
import { NewIdeaComponent } from './ideas/new-idea/new-idea.component';
import { UserComponent } from './users/user.component';
import { AdminComponent } from './admin/admin.component';
import { NewUserComponent } from './register/new-user.component';
import { ShowIdeaComponent } from './ideas/show-idea/show-idea.component';
import { TransactionComponent } from './transaction/transaction.component'




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NewIdeaComponent,
    UserComponent,
    AdminComponent,
    NewUserComponent,
    ShowIdeaComponent,
    TransactionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    primengConfig,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
    
    
    
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
