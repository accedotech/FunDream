import { Component, OnInit } from '@angular/core';
import { TokenService } from '../services/token.service';
import { IdeaService } from '../services/idea.service';
import { Idea } from '../models/idea';
import { Router } from '@angular/router';
import { IdeaHomeDTO } from '../DTO/IdeaHomeDTO';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [IdeaService]
})
export class HomeComponent implements OnInit {

  
  ideas: IdeaHomeDTO[];

  constructor(private ideaSerices: IdeaService,
              private router: Router) { } 

  ngOnInit() {     
    
    this.getAllIdeas();
  }


  getAllIdeas(){
    
    this.ideaSerices.getAllIdeasForHome().subscribe(response =>{
      this.ideas =  response;   
      console.log(response);
       
      
    }, 
    error=>{

    });
  }  
 

}
