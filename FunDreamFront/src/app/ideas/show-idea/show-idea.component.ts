import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IdeaService } from 'src/app/services/idea.service';
import { Idea } from 'src/app/models/idea';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/User';
import { Categories } from 'src/app/models/Categories';

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

            console.log(response)
        }, 
        error => {

        });
      }
    });
  }

  getEntrepreneurs(){

  }
}
