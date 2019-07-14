import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../services/token.service';



@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let autReq = request;
    const token = this.tokenService.getToken();
    if (token != null) {
      autReq = request.clone({ headers: request.headers.set('AuthorizationBearer ', token) });
    }
    return next.handle(autReq);
  }
  constructor(private tokenService: TokenService) { }
}

export const interceptorProvider = [{provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true}];