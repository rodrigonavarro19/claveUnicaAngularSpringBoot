package cl.rodrigonavarro.backclaveunica.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginClaveUnicaRequestDto {

    private String clientId;
    private String redirectUri;
    private String code;
    private String state;


}
