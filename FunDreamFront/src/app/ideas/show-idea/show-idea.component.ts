import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IdeaService } from 'src/app/services/idea.service';
import { Idea } from 'src/app/models/idea';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/User';
import { Categories } from 'src/app/models/Categories';
import { Documents } from 'src/app/models/Documents';

@Component({
  selector: 'app-show-idea',
  templateUrl: './show-idea.component.html',
  styleUrls: ['./show-idea.component.css'],
  providers: [IdeaService]
})
export class ShowIdeaComponent implements OnInit {

  idea: Idea;
  entrepreneurs: User[] = [];
  categories: Categories[] = [];

  images: any[];

  constructor(private activateRoute: ActivatedRoute,              
              private ideaService: IdeaService
              ) { 
                this.idea = new Idea();
              }

  ngOnInit() {
    this.getIdea();    
  }

  getIdea(){
    this.activateRoute.params.subscribe(param =>{
      let nameIdea = param['nameIdea'];
      if(nameIdea){
        this.ideaService.getIdeaByName(nameIdea).subscribe(
          response => {
            this.idea =  response;     
            this.entrepreneurs = response.entrepreneurs;
            this.categories =  response.categories;
            this.fillGallery(response.documents);
            console.log(response)
        }, 
        error => {

        });
      }
    });
  }

  fillGallery(documents: Documents[]){
    this.images = [];
    
    for(let document of documents){
      this.images.push({source:'http://localhost:8080/api/document/show-image/'+document.nameFile, title:this.idea.name});
    }
  }
}
