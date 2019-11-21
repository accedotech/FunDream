import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Transaction } from '../models/Transaction';
import { Observable } from 'rxjs';
import { transition } from '@angular/animations';


@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  headers: HttpHeaders;
  ideaURL = 'api/transaction'

  constructor(private httpClient: HttpClient) { 
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  save(transaction: Transaction): Observable<string>{
  return this.httpClient.post<string>(this.ideaURL, transition);
  }


  getAllTransaction(): Observable<Transaction[]>{
    return this.httpClient.get<Transaction[]> (this.ideaURL, { headers: this.headers});
  }

  getAllTransactionByUser(idUser: string): Observable<Transaction[]>{
    return this.httpClient.get<Transaction[]> (this.ideaURL + '/get-all-by-user/' + idUser, { headers: this.headers});
  }

  getAllTransactionByIdea(idIdea: string): Observable<Transaction[]>{
    return this.httpClient.get<Transaction[]> (this.ideaURL + '/get-all-by-idea/' + idIdea, { headers: this.headers});
  }

  getTransactionById(id: string): Observable<Transaction[]>{
    return this.httpClient.get<Transaction[]> (this.ideaURL + '/get-all-by-id/' + id, { headers: this.headers});
  }

}
