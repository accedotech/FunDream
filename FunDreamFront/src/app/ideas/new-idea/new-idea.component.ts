import { Component, OnInit } from '@angular/core';
import { CategoriesService } from 'src/app/services/categories.service';
import { SelectItem } from 'primeng/api';
import { COUNTRIES } from 'src/app/countries';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-new-idea',
  templateUrl: './new-idea.component.html',
  styleUrls: ['./new-idea.component.css'],
  providers:[CategoriesService]
})
export class NewIdeaComponent implements OnInit {

  form: FormGroup;

  countries: SelectItem[];
  categories: SelectItem[] = [];
  
  constructor(private categoriesService: CategoriesService, 
              private formBuilder: FormBuilder,
              private router: Router) { 
   
  }

  ngOnInit() {
    this.countries =  COUNTRIES;
    this.fillSelectCategories();
    this.buildForm();
    
  }
  
  private buildForm() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      objective: [],
      explanation: ['', Validators.compose([Validators.required, Validators.maxLength(5000)])], 
      contact: ['', Validators.required],
      country: ['', Validators.required],
      entrepreneurs: [[], Validators.required],
      categories: [[], Validators.required]
    });
  }

  get validateErrorsForm(){ return this.form.controls};

  fillSelectCategories(){    
    this.categoriesService.getAllCategories().subscribe(response =>{               
      let length = response.length;
      for(let i = 0; i < length; i ++){
        this.categories.push({label: response[i].name, value: response[i]});
      }
      
    }, 
    error =>{

    });
  }

}
