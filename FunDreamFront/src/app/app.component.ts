import { Component } from '@angular/core';
import { TokenService } from './services/token.service';
import { Router } from '@angular/router';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  menu: MenuItem[];

  isLogin = false;
  roles: string[];
  authority: string;
  
  constructor(private tokenService: TokenService, private router: Router) {

    this.menu = [    
          {
            label: 'Inicio',
            icon: "fa fa-home",
            command: (event) => this.router.navigate(['home'])
          },
          {separator:true},
          {label: 'Ideas',
          icon: "fa fa-lightbulb-o",
          items: [{ 
                  label: 'Nueva Idea',
                  command: (event) => this.router.navigate(['new-idea'])
                }]
        },
        {separator:true}      
      ];
   }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogin = true;
      this.roles = [];
      this.roles = this.tokenService.getAuthorities();
      this.roles.every(rol => {
        if (rol === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }

   
  }

  logout(): void {
    this.tokenService.logout();
    this.isLogin = false;
    this.authority = '';
    this.router.navigate(['home']);
  }
}
