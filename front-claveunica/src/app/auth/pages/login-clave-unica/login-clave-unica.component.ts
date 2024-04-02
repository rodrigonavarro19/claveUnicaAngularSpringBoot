import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { environment } from '../../../../environments/environment';
import * as uuid from 'uuid';

@Component({
  selector: 'app-login-clave-unica',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './login-clave-unica.component.html',
  styleUrl: './login-clave-unica.component.scss'
})
export default class LoginClaveUnicaComponent {

  clientId = environment.clientIdClaveUnica;
  redirectUri = environment.redirecUriClaveUnica;
   


  loginClaveUnica(){

    const encodedUrl = encodeURIComponent(this.redirectUri);
    const state = uuid.v4();

    const url = `https://accounts.claveunica.gob.cl/openid/authorize/?`;
    const params = `client_id=${this.clientId}&response_type=code&scope=openid run name&redirect_uri=${encodedUrl}&state=${state}`;

    window.location.href =  url + params;

  }

}
