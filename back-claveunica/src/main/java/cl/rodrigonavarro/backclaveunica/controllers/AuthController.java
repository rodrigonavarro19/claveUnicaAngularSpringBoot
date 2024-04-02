package cl.rodrigonavarro.backclaveunica.controllers;

import cl.rodrigonavarro.backclaveunica.dto.LoginClaveUnicaRequestDto;
import cl.rodrigonavarro.backclaveunica.dto.UserInfoClaveUnicaDto;
import cl.rodrigonavarro.backclaveunica.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/loginClaveUnica")
    public ResponseEntity<UserInfoClaveUnicaDto> loginClaveUnica(@RequestBody LoginClaveUnicaRequestDto loginClaveUnicaRequestDto) throws Exception{

        return authService.loginClaveUnica(loginClaveUnicaRequestDto);

    }

}
