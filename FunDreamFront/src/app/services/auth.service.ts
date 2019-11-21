import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginUser } from '../models/login-user';
import { Observable } from 'rxjs';
import { JwtModel } from '../models/jwt-model';
import { NewUser } from '../models/new-user';


const header = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authURL = 'api/auth/';
  
  constructor(private httpClient: HttpClient) { }

  public login(loginUser: LoginUser): Observable<JwtModel> {
    return this.httpClient.post<JwtModel>(this.authURL + "login", loginUser, header);
  }

  public saveUser(newUser: NewUser): Observable<any> {
    return this.httpClient.post<any>(this.authURL + "new-user", newUser, header);
  }
}
