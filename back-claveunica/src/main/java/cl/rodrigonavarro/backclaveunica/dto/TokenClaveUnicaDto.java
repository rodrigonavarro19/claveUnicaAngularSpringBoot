package cl.rodrigonavarro.backclaveunica.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenClaveUnicaDto {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private Long expires_in;
    private String id_token;

}
