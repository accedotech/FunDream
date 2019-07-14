import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';
import { TokenService } from '../services/token.service';


@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate {
    
  realRole: string;

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRol = route.data.expectedRol;
    const roles = this.tokenService.getAuthorities();
    this.realRole = 'user';
    roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.realRole = 'admin';
      }
    });
    if (!this.tokenService.getToken() || expectedRol.indexOf(this.realRole) === -1) {
      this.router.navigate(['']);
      return false;
    }
    return true;
  }

  constructor(private tokenService: TokenService, private router: Router) { }
}
