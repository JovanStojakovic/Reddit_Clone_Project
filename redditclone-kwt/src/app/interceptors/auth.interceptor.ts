import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../shared/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

 

  constructor(private authService : AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.authService.isLoggedIn()) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${localStorage.getItem('JWT')}` 
        }
      });
    }
    return next.handle(request);
  }
}
