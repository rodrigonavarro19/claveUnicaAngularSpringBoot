package cl.rodrigonavarro.backclaveunica.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoClaveUnicaDto {

    private String sub;
    private ClaveUnicaRolUnico RolUnico;
    private ClaveUnicaName name;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClaveUnicaRolUnico {
        private Long numero;
        private String DV;
        private String tipo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClaveUnicaName {
        private List<String> apellidos;
        private List<String> nombres;

    }


}
