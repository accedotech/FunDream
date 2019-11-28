import { Component, OnInit } from '@angular/core';
import { IdeaService } from '../services/idea.service';
import { ActivatedRoute } from '@angular/router';
import { Idea } from '../models/idea';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css'],
  providers: [IdeaService]
})
export class TransactionComponent implements OnInit {

  idea: Idea;

  constructor(private ideaService: IdeaService,
    private activateRoute: ActivatedRoute) { }

  ngOnInit() {
    this.getIdea();
  }

  getIdea() {
    this.activateRoute.params.subscribe(param => {
      let id = param['id'];
      if (id) {
        this.ideaService.getIdeaById(id).subscribe(
          response => {
            this.idea = response;
          },
          error => {

          });
      }
    });
  }
}
