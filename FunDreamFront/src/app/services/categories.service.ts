import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categories } from '../models/Categories';

 

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  headers: HttpHeaders;
  categoriesURL = 'http://localhost:8080/api/categories'

  constructor(private httpClient: HttpClient) { 
    this.headers = new HttpHeaders({'Content-TYpe': 'application/json'});
  }

  public getAllCategories(): Observable<Categories[]>{    
    return this.httpClient.get<Categories[]>(this.categoriesURL, {headers : this.headers});
  }
}
