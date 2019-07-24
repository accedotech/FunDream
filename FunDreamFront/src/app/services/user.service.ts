import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { Idea } from '../models/idea';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  headers: HttpHeaders;
  usersURL = 'http://localhost:8080/api/user'
  constructor(private httpClient: HttpClient) {
    this.headers = new HttpHeaders({ 'Content-TYpe': 'application/json' });
  }


  public searchUserByEmail(email: string): Observable<User> {
    return this.httpClient.get<User>(this.usersURL + '/search-by-email/' + email, { headers: this.headers });
  }
  

}
