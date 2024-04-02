package cl.rodrigonavarro.backclaveunica.services;

import cl.rodrigonavarro.backclaveunica.dto.LoginClaveUnicaRequestDto;
import cl.rodrigonavarro.backclaveunica.dto.UserInfoClaveUnicaDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public ResponseEntity<UserInfoClaveUnicaDto> loginClaveUnica(LoginClaveUnicaRequestDto loginClaveUnicaRequestDto)  throws Exception;

}
