import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IdeaService } from 'src/app/services/idea.service';
import { User } from 'src/app/models/User';
import { Categories } from 'src/app/models/Categories';
import { Documents } from 'src/app/models/Documents';
import { DomSanitizer } from '@angular/platform-browser';
import { CompleteIdeaDTO } from 'src/app/DTO/CompleteIdeaDTO';

@Component({
  selector: 'app-show-idea',
  templateUrl: './show-idea.component.html',
  styleUrls: ['./show-idea.component.css'],
  providers: [IdeaService]
})
export class ShowIdeaComponent implements OnInit {

  idea: CompleteIdeaDTO;
  entrepreneurs: User[] = [];
  categories: Categories[] = [];

  video;

  images: any[];

  constructor(private activateRoute: ActivatedRoute,              
              private ideaService: IdeaService,
              private sanitizer: DomSanitizer
              ) { 
                this.idea = new CompleteIdeaDTO();
                
              }

  ngOnInit() {
    this.getIdea();    
  }

  getIdea(){
    this.activateRoute.params.subscribe(param =>{
      let id = param['id'];
      if(id){
        this.ideaService.getCompleteIdeaById(id).subscribe(
          response => {
            this.idea =  response;                 
            this.entrepreneurs = response.entrepreneurs;
            this.categories =  response.categories;
            this.fillGallery(response.documents);
            this.video = this.sanitizer.bypassSecurityTrustResourceUrl( response.video);
            console.log(response)
            console.log(this.video);
            
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
