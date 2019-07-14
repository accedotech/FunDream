import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categories } from '../models/Categories';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  categoriesURL = 'http://localhost:8080/api/categories/'

  constructor(private httpClient: HttpClient) { }

  public getAllCategories(): Observable<Categories[]>{
    return this.httpClient.get<Categories[]>(this.categoriesURL, cabecera);
  }
}
