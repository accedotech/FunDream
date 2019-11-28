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
import { Message } from 'primeng/components/common/api';
import { DomSanitizer } from '@angular/platform-browser';
import * as moment from 'moment';



@Component({
  selector: 'app-new-idea',
  templateUrl: './new-idea.component.html',
  styleUrls: ['./new-idea.component.css'],
  providers: [CategoriesService, UserService, IdeaService]
})
export class NewIdeaComponent implements OnInit {

  //Validate form
  form: FormGroup;
  searchUser: FormGroup;

  //managge of files 
  mainImage: File;
  showImage: any = '';
  uploadedFiles: File[] = [];
  files = Array<Files>();
  file: Files = new Files();

  //contact 
  disabledContact: boolean = true;

  //selects 
  countries: SelectItem[];
  categories: SelectItem[] = [];

  //objects
  idea: Idea = new Idea();
  entrepreneurs: User[] = [];
  mainEntrepreneurs: User = new User();

  //errors
  errorMessageSearch: Message[] = [];

  video: any;
  mostrarVideo: boolean = null;


  constructor(private ideaService: IdeaService,
    private categoriesService: CategoriesService,
    private userService: UserService,
    private tokenService: TokenService,
    private formBuilder: FormBuilder,
    private sanitizer: DomSanitizer,
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
      objective: [0],
      explanation: ['', Validators.compose([Validators.required, Validators.maxLength(15000)])],
      contact: ['', Validators.required],
      country: ['', Validators.required],
      video: [''],
      description: ['', Validators.maxLength(500)],
      categories: [[], Validators.required],
      campaignDays: [30, Validators.compose([Validators.min(20), Validators.max(120)])]
    });

    this.searchUser = this.formBuilder.group({
      emailUser: ['', Validators.email]
    })
  }

  get validateErrorsForm() { return this.form.controls };
  get validateSearchUser() { return this.searchUser.controls }

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

    
  }


  onRemoveFiles(event) {
    for (let i = 0; i < this.uploadedFiles.length; i++) {
      if (event.file.name == this.uploadedFiles[i].name) {
        this.uploadedFiles.splice(i, 1);
      }
    }
    
  }

  onClearFiles() {
    this.uploadedFiles = [];    
  }


  saveIdea() {

    if (this.errorsForm() == false) {

      this.fillFiles();
      this.fillidea();      

      this.ideaService.saveIdea(this.files, this.idea).subscribe(response => {        

        Swal.fire({
          title: 'Éxito!',
          text: response,
          type: 'success',
          confirmButtonColor: '#A3E2C6',
          confirmButtonText: 'Continuar'
        }).then((result) => {
          if (result.value) {
            return this.router.navigate(['home']);
          }
        });

      },
        error => {
          this.idea.entrepreneurs = [];
          Swal.fire({
            title: 'Error!',
            text: error.error,
            type: 'error',
            confirmButtonColor: '#A3E2C6',
            confirmButtonText: 'Continuar'
          });
        });

    } else {
      Swal.fire('Atención!', 'Aun hay campos incompletos o sin diligenciar.', 'warning');
    }
  }

  fillidea() {

    this.idea.name = this.form.value['name'];
    this.idea.objective = this.form.value['objective'];
    this.idea.explanation = this.form.value['explanation'];
    this.idea.country = this.form.value['country'];
    this.idea.contact = this.form.value['contact'];
    this.idea.categories = this.form.value['categories'];
    this.idea.description = this.form.value['description'];
    
    if(this.mostrarVideo == true){
      this.idea.video = this.form.value['video'];
    }else{
      this.idea.video = '';
    }
        
    this.idea.campaignFinishedDate =  moment(new Date()).add(this.form.value['campaignDays'], 'days').toDate();    

    let users: User[] = this.entrepreneurs;

    users.push(this.mainEntrepreneurs);

    this.idea.entrepreneurs = users;

  }


  fillFiles() {
    let filesTemp: Files[] = [];
    for (let i = 0; i < this.uploadedFiles.length; i++) {

      this.file.file = this.uploadedFiles[i];
      this.file.type = 'secondary';

      filesTemp.push(this.file);

      delete this.file;
      this.file = new Files();

    }

    this.file.file = this.mainImage;
    this.file.type = 'main';

    filesTemp.push(this.file);
    this.files = filesTemp;

    delete this.file;
    this.file = new Files();    
  }


  editContact() {
    this.form.get('contact').setValue('');
    this.disabledContact = false;
  }

  reloadContact() {
    this.form.get('contact').setValue(this.tokenService.getEmail());
    this.disabledContact = true;
  }

  searchMainEntrepreneursByEmail(email: string) {
    this.userService.searchUserByEmail(email).subscribe(response => {
      this.mainEntrepreneurs = response;
    },
      error => {
        Swal.fire('Atención!', 'No se pudo cargar emprendedor por defecto, así que debe buscarlo manualmente.', 'warning');
      });

  }

  searchEntrepreneursByEmail(email: string) {
    if (email != '') {
      this.userService.searchUserByEmail(email).subscribe(response => {
        this.entrepreneurs.push(response);
        this.searchUser.get('emailUser').setValue('');
      },
        error => {
          this.errorMessageSearch.push({ severity: 'error', summary: 'Error en la busqueda.', detail: error.error });
        });
    } else {
      this.errorMessageSearch.push({ severity: 'warn', summary: 'Error en la busqueda.', detail: 'Por favor ingrese un correo.' });

    }
  }

  removeentrepreneurs(email: string) {
    for (let i = 0; i < this.entrepreneurs.length; i++) {
      if (this.entrepreneurs[i].email == email) {
        this.entrepreneurs.splice(i, 1);
      }
    }
  }

  errorsForm() {

    if (this.form.invalid) {
      Swal.fire('Atención!', 'Hay información aun sin diligenciar.', 'warning');
      return true;
    }

    if (this.showImage == '') {
      Swal.fire('Atención!', 'Debe seleccionar una imagen principal para la idea.', 'warning');
      return true;
    }


    if (this.mainEntrepreneurs == null && this.entrepreneurs.length == 0) {
      Swal.fire('Atención!', 'Debe seleccionar emprendedores para la idea.', 'warning');
      return true;
    }

    return false;
  }

  omitNumbers(event) {
    if (event.charCode >= 48 && event.charCode <= 57) {
      return true;
    }
    return false;
  }

  omitKeys(event) {
    var pressedKey = event.charCode;
    return pressedKey != 32;  //value of the key space 
  }


  validateLinkYouTube(event: string) {
    if(event.indexOf('watch?v=') > 0){
      this.video = this.sanitizer.bypassSecurityTrustResourceUrl(event.replace('watch?v=', 'embed/'));
      this.mostrarVideo = true;
    }else{

      if(event.length > 0){
        this.mostrarVideo = false;        
      }else{
        this.mostrarVideo = null;
      }      
    }
    

  }
}
