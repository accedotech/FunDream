import { Component, OnInit } from '@angular/core';
import { LoginUser } from '../models/login-user';
import { AuthService } from '../services/auth.service';
import { TokenService } from '../services/token.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {Message} from 'primeng/components/common/api';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup;

  inputPasswordType: string = 'password';  
  showPasswordButton: boolean = true;
  errorMessage: Message [] = [];

 
  
  isLogged = false;
  isLoginFail = false;
  roles: string[] = [];
  

  constructor(private authService: AuthService, 
              private tokenService: TokenService, 
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    if (this.tokenService.getToken()){
      return this.router.navigate(['home']);
    }
    this.buildForm();

    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
  }

  private buildForm(){
    this.formLogin = this.formBuilder.group({
        email: ['', Validators.compose([Validators.required, Validators.email])],
        password: ['', Validators.required]
    });
  }

  get validateErrorsLogin(){ return this.formLogin.controls;}

  login(): void {

    if(this.formLogin.invalid != true){

    let loginUser: LoginUser =  new LoginUser(this.formLogin.value ['email'], this.formLogin.value ['password']);
        
    this.authService.login(loginUser).subscribe(response => {
    this.tokenService.setToken(response.token);
    this.tokenService.setEmail(response.email);
    this.tokenService.setAuthorities(response.authorities);

    this.isLogged = true;
    this.isLoginFail = false;
    this.roles = this.tokenService.getAuthorities();  
    window.location.reload();            
    },
      (error: any) => {
        this.isLogged = false;
        this.isLoginFail = true;        
        this.errorMessage.push({severity:'error', summary:'Error al iniciar sesión.', detail: error.error.message});        
      }
    );           
    }else{
      this.errorMessage.push({severity:'error', summary:'Error al iniciar sesión.', detail:'Email o contraseña sin ingresar.'});
    }
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
