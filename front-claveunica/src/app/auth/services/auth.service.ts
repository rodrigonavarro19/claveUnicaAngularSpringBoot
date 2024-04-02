import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { LoginClaveUnicaInterface } from '../interfaces/login-clave-unica.interface';
import { catchError, Observable, throwError } from 'rxjs';
import { LoginResponseInterface } from '../interfaces/login-response.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly baseUrl = environment.baseUrl;

  private http = inject( HttpClient );



  loginClaveUnica(loginClaveUnicaInterface: LoginClaveUnicaInterface): Observable<LoginResponseInterface>{

    const url = `${this.baseUrl}/auth/loginClaveUnica`;

    return this.http.post<LoginResponseInterface>( url, loginClaveUnicaInterface);
  }

}
