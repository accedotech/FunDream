import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Files } from '../ideas/new-idea/File';
import { Idea } from '../models/idea';
import { Observable } from 'rxjs';
import { IdeaHomeDTO } from '../DTO/IdeaHomeDTO';
import { CompleteIdeaDTO } from '../DTO/CompleteIdeaDTO';

@Injectable({
  providedIn: 'root'
})
export class IdeaService {

  headers: HttpHeaders;
  ideaURL = 'api/idea'

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

  getAllIdeasForHome(): Observable<IdeaHomeDTO[]>{
    return this.httpClient.get<IdeaHomeDTO[]>(this.ideaURL, {headers: this.headers});
  }

  getCompleteIdeaById(id: number): Observable<CompleteIdeaDTO>{
    return this.httpClient.get<CompleteIdeaDTO>(this.ideaURL + '/get-idea-by-id/' + id, {headers: this.headers});
  }

  getIdeaByName(nameIdea: string): Observable<Idea>{
    return this.httpClient.get<Idea>(this.ideaURL + '/show-idea/' + nameIdea, {headers: this.headers});
  }

  
}
