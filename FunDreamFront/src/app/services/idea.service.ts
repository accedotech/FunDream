import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Files } from '../ideas/new-idea/File';
import { Idea } from '../models/idea';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IdeaService {

  headers: HttpHeaders;
  ideaURL = 'http://localhost:8080/api/idea'

  constructor(private httpClient: HttpClient) {
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  saveIdea(files: Files[], idea: Idea): Observable<string> {    
    let formData = new FormData();    
    for(let i = 0; i < files.length; i++){
      formData.append('file', files[i].file);
      formData.append('type', files[i].type);
    }    
    formData.append('idea', JSON.stringify(idea));
    return this.httpClient.post<string>(this.ideaURL, formData);
  }

  getAllIdeas(): Observable<Idea[]>{
    return this.httpClient.get<Idea[]>(this.ideaURL, {headers: this.headers});
  }

  getIdeaByName(nameIdea: string): Observable<Idea>{
    return this.httpClient.get<Idea>(this.ideaURL + '/show-idea/' + nameIdea, {headers: this.headers});
  }

  
}
