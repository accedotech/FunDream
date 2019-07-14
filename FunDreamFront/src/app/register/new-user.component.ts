import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { NewUser } from '../models/new-user';
import { COUNTRIES } from './countries'
import { SelectItem } from 'primeng/api';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import {Message} from 'primeng/components/common/api';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'



@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  form: FormGroup;

  countries: SelectItem[];
  spanishCalendar: any;

  inputPasswordType: string = 'password';  
  showPasswordButton: boolean = true;
  errorMessage: Message [] = [];    

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.countries =  COUNTRIES;
    
    this.spanishCalendar = {
      firstDayOfWeek: 1,
      dayNames: [ "domingo","lunes","martes","miércoles","jueves","viernes","sábado" ],
      dayNamesShort: [ "dom","lun","mar","mié","jue","vie","sáb" ],
      dayNamesMin: [ "D","L","M","X","J","V","S" ],
      monthNames: [ "enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre" ],
      monthNamesShort: [ "ene","feb","mar","abr","may","jun","jul","ago","sep","oct","nov","dic" ],
      today: 'Hoy',
      clear: 'Borrar'
      
  }
  this.buildForm();
  }


  private buildForm() {

    this.form = this.formBuilder.group({
      firstName: ['', Validators.compose([Validators.required, Validators.pattern('^[0-9A-Za-zñÑáéíóúÁÉÍÓÚ]+$')])],
      secondName: ['', Validators.pattern('^[0-9A-Za-zñÑáéíóúÁÉÍÓÚ]+$')],
      firstLastName: ['', Validators.compose([Validators.required, Validators.pattern('^[0-9A-Za-zñÑáéíóúÁÉÍÓÚ]+$')])],
      secondLastName: ['', Validators.pattern('^[0-9A-Za-zñÑáéíóúÁÉÍÓÚ]+$')],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.required],
      country: ['', Validators.required],
      birthdate: ['', Validators.required]      
    });
  }

  get validateErrorsForm(){ return this.form.controls}

  saveUser() {    

    if(this.form.invalid != true){

      let newUser: NewUser = this.fillUser();

      console.log(newUser);
    
    this.authService.saveUser(newUser).subscribe(response => {
            
      Swal.fire({
        title: 'Éxito',
        text: response,
        type: 'success',      
        confirmButtonColor: '#A3E2C6',      
        confirmButtonText: 'Continuar'
      }).then((result) => {
        if (result.value) {
          return this.router.navigate(['login']);
        }
      });   
    },
      error => {                
        this.errorMessage.push({severity:'error', summary:'Error al crear usuario.', detail: error.error});            
      }
    );
    }else{
      this.errorMessage.push({severity:'error', summary:'Error en el formulario.', detail:'Aun hay campos sin llenar.'});      
    }
  }
  

  fillUser(){
    let newUser: NewUser =  new NewUser();
    let firstName: string;
    let lastName: string;

    if(this.form.value['secondName'] != ''){
      firstName = this.form.value ['firstName'] + ' ' + this.form.value ['secondName'];
    }else{
      firstName = this.form.value ['firstName'];
    }

    if(this.form.value['secondLastName'] != ''){
      lastName = this.form.value ['firstLastName'] + ' ' + this.form.value ['secondLastName'];
    }else{
      lastName = this.form.value ['firstLastName'];
    }

    newUser.firstName =  firstName;
    newUser.lastName =  lastName;
    newUser.email =  this.form.value ['email'];
    newUser.password =  this.form.value ['password'];
    newUser.country =  this.form.value ['country'];
    newUser.birthdate =  this.form.value ['birthdate'];
    newUser.roles = ['user'];

    return newUser;
  }
  
  omitKeys(event){   
    var pressedKey = event.charCode; 
    return pressedKey != 32;  //value of the key space 
  }

  showPassword(){
  
    if(this.inputPasswordType == "password"){
      this.inputPasswordType = "text";
      this.showPasswordButton =  false;
    }else{
      this.inputPasswordType = "password";
      this.showPasswordButton =  true;
    }
  }

}
