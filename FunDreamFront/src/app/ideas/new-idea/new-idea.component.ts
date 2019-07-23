import { Component, OnInit } from '@angular/core';
import { CategoriesService } from 'src/app/services/categories.service';
import { SelectItem } from 'primeng/api';
import { COUNTRIES } from 'src/app/countries';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Files } from './File';
import { TokenService } from 'src/app/services/token.service';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/User';
import { Idea } from 'src/app/models/idea';
import Swal from 'sweetalert2';
import { IdeaService } from 'src/app/services/idea.service';


@Component({
  selector: 'app-new-idea',
  templateUrl: './new-idea.component.html',
  styleUrls: ['./new-idea.component.css'],
  providers: [CategoriesService, UserService, IdeaService]
})
export class NewIdeaComponent implements OnInit {

  //Validate form
  form: FormGroup;

  //managge of files 
  mainImage: File;
  showImage: any = '';
  uploadedFiles: File[] = [];
  files = Array<Files>();
  file: Files =  new Files();

  //contact 
  disabledContact: boolean = true;

  //selects 
  countries: SelectItem[];
  categories: SelectItem[] = [];

  idea: Idea =  new Idea();
  entrepreneurs: User[] = [];
  mainEntrepreneurs: User = new User();
  emailUser: string;

  constructor(private ideaService: IdeaService,
              private categoriesService: CategoriesService,
              private userService: UserService, 
              private tokenService: TokenService,
              private formBuilder: FormBuilder,
              private router: Router) {
                
  }

  ngOnInit() {
    this.countries = COUNTRIES;
    this.fillSelectCategories();
    this.buildForm();

    this.form.get('contact').setValue(this.tokenService.getEmail());
    
    this.searchMainEntrepreneursByEmail(this.tokenService.getEmail());    
  }

  private buildForm() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      objective: [],
      explanation: ['', Validators.compose([Validators.required, Validators.maxLength(5000)])],
      contact: ['', Validators.required],
      country: ['', Validators.required],      
      categories: [[], Validators.required],      
    });
  }

  get validateErrorsForm() { return this.form.controls };

  fillSelectCategories() {
    this.categoriesService.getAllCategories().subscribe(response => {
      let length = response.length;
      for (let i = 0; i < length; i++) {
        this.categories.push({ label: response[i].name, value: response[i] });
      }

    },
      error => {

      });
  }


  selcetImage(event) {
    this.mainImage = event.target.files[0];

    var reader = new FileReader();
    reader.readAsDataURL(this.mainImage);
    reader.onloadend = (_event) => {
      this.showImage = reader.result;
    }
    console.log(this.mainImage)
  }

  onSelectFiles(event) {
    let intput = [];
    let selectedFiles = [];
    let flag: boolean = false;

    for (let key in event.files) {
      intput.push(event.files[key]);
    }

    for (let i = 0; i < intput.length - 2; i++) {
      selectedFiles.push(intput[i]);
    }

    for (let file of selectedFiles) {
      for (let i = 0; i < this.uploadedFiles.length; i++) {
        if (file.name == this.uploadedFiles[i].name) {
          flag = true;
        }
      }
      if (flag == false) {
        this.uploadedFiles.push(file);
      } else {
        flag = false;
      }
    }

    console.log(this.uploadedFiles);
  }

  
  onRemoveFiles(event) {           
    for (let i = 0; i < this.uploadedFiles.length; i ++){
      if(event.file.name ==  this.uploadedFiles[i].name){ 
        this.uploadedFiles.splice(i, 1);
      }
    }
    console.log(this.uploadedFiles);
  }

  onClearFiles() {
    this.uploadedFiles = [];    
    console.log(this.uploadedFiles);
  }


  saveIdea() {
    
    if(this.errorsForm() == false){

      this.fillFiles();
      this.fillidea();

     this.ideaService.saveIdea(this.files, this.idea).subscribe(response => {
      console.log(response);
     }, 
     error =>{
      console.log(error.error);
     });

    }else{

    }
  }

  fillidea(){
            
    this.idea.name = this.form.value ['name'];
    this.idea.objective = this.form.value ['objective'];
    this.idea.explanation = this.form.value ['explanation'];
    this.idea.country = this.form.value ['country'];
    this.idea.contact = this.form.value ['contact'];
    this.idea.categories = this.form.value ['categories'];    
    this.idea.entrepreneurs = this.entrepreneurs;    

    } 


  fillFiles(){    
    
    for(let i = 0; i < this.uploadedFiles.length; i++){
      this.file.file =  this.uploadedFiles[i];
      this.file.type = 'secondary';
      this.files.push(this.file);
      delete this.file;
      this.file =  new Files();
    }

    this.file.file = this.mainImage;
    this.file.type = 'main';
    this.files.push(this.file);

    console.log(this.files);         
  }


  editContact(){
    this.form.get('contact').setValue('');   
    this.disabledContact =  false;
  }

  reloadContact(){
    this.form.get('contact').setValue(this.tokenService.getEmail()); 
    this.disabledContact =  true;
  }

  searchMainEntrepreneursByEmail(email: string){
    this.userService.searchUserByEmail(email).subscribe(response => {
      this.mainEntrepreneurs= response;         
    }, 
    error => {

    });

  }  

  searchEntrepreneursByEmail(email: string){
    this.userService.searchUserByEmail(email).subscribe(response => {
      this.entrepreneurs.push(response);          
    }, 
    error => {

    });

  }

  removeentrepreneurs(email: string){    
    for(let i = 0; i < this.entrepreneurs.length; i++){
      if(this.entrepreneurs[i].email == email){
        this.entrepreneurs.splice(i, 1);
      }
    }
  }

  errorsForm(){

    if(this.form.invalid){
      Swal.fire('Atención!', 'Hay información aun sin diligenciar.', 'warning');
      return true;
    }

    if(this.showImage == ''){
      Swal.fire('Atención!', 'Debe seleccionar una imagen principal para la idea.', 'warning');
      return true;
    }
   
    return false;
  }
}
