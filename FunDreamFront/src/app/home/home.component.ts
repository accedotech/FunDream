import { Component, OnInit } from '@angular/core';
import { TokenService } from '../services/token.service';
import { IdeaService } from '../services/idea.service';
import { Idea } from '../models/idea';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [IdeaService]
})
export class HomeComponent implements OnInit {

  
  ideas: Idea[];

  constructor(private tokenService: TokenService, 
              private ideaSerices: IdeaService) { }

  ngOnInit() {      

  }


  getAllIdeas(){
    
    this.ideaSerices.getAllIdeas().subscribe(response =>{
      this.ideas =  response;
      console.log(response)
    }, 
    error=>{

    });
  }
 

}
