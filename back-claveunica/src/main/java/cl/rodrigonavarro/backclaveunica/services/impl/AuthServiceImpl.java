package cl.rodrigonavarro.backclaveunica.services.impl;

import cl.rodrigonavarro.backclaveunica.dto.LoginClaveUnicaRequestDto;
import cl.rodrigonavarro.backclaveunica.dto.TokenClaveUnicaDto;
import cl.rodrigonavarro.backclaveunica.dto.UserInfoClaveUnicaDto;
import cl.rodrigonavarro.backclaveunica.services.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${claveunica.client.secret}")
    String claveUnicaClientSecret;

    private final String CLAVEUNICABASEURLTOKEN = "https://accounts.claveunica.gob.cl/openid/token/";
    private final String CLAVEUNICABASEURLUSERINFO = "https://accounts.claveunica.gob.cl/openid/userinfo/";


    @Override
    public ResponseEntity<UserInfoClaveUnicaDto> loginClaveUnica(LoginClaveUnicaRequestDto loginClaveUnicaRequestDto)  throws Exception{

        TokenClaveUnicaDto tokenClaveUnica = this.getTokenClaveUnica(loginClaveUnicaRequestDto);
        UserInfoClaveUnicaDto claveUnicaUserInfo = this.getUserInfoClaveUnica(tokenClaveUnica.getAccess_token());

        return ResponseEntity.ok(claveUnicaUserInfo);
    }

    private TokenClaveUnicaDto getTokenClaveUnica(LoginClaveUnicaRequestDto loginClaveUnicaRequestDto) throws Exception{


        RestTemplate restTemplate = new RestTemplate();

        // Crea el objeto MultiValueMap para los datos del formulario
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", loginClaveUnicaRequestDto.getClientId());
        formData.add("client_secret", this.claveUnicaClientSecret);
        formData.add("redirect_uri", loginClaveUnicaRequestDto.getRedirectUri());
        formData.add("grant_type", "authorization_code");
        formData.add("code", loginClaveUnicaRequestDto.getCode());
        formData.add("state", loginClaveUnicaRequestDto.getState());

        // Crea los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Crea la entidad HttpEntity con los datos del formulario y los encabezados
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

        try{
        // Realiza la llamada POST y obtiene la respuesta
        ResponseEntity<String> response = restTemplate.exchange(this.CLAVEUNICABASEURLTOKEN, HttpMethod.POST, entity, String.class);
        // Obtiene el cuerpo de la respuesta
        ObjectMapper objectMapper = new ObjectMapper();
        TokenClaveUnicaDto tokenClaveUnicaDto  = objectMapper.readValue(response.getBody(), TokenClaveUnicaDto.class);

        return tokenClaveUnicaDto;

        } catch (HttpServerErrorException e) {
            // Manejo de errores del servidor HTTP
            throw new Exception("Error en el servidor al obtener el token de Clave Única: " + e.getMessage(), e);
        } catch (HttpClientErrorException e) {
            // Manejo de errores de cliente HTTP
            throw new Exception("Error de cliente al obtener el token de Clave Única: " + e.getMessage(), e);
        } catch (RestClientException e) {
            // Manejo de errores de comunicación REST
            throw new Exception("Error de comunicación REST al obtener el token de Clave Única: " + e.getMessage(), e);
        } catch (HttpMessageNotReadableException | JsonProcessingException e) {
            // Manejo de errores al deserializar la respuesta JSON
            throw new Exception("Error al procesar la respuesta JSON al obtener el token de Clave Única: " + e.getMessage(), e);
        } catch (Exception e) {
            // Captura cualquier otra excepción no anticipada
            throw new Exception("Error inesperado al obtener el token de Clave Única: " + e.getMessage(), e);
        }

    }

    private UserInfoClaveUnicaDto getUserInfoClaveUnica(String accessToken) throws Exception{

        RestTemplate restTemplate = new RestTemplate();

        // Crea el objeto MultiValueMap para los datos del formulario
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        // Crea los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        // Crea la entidad HttpEntity con los datos del formulario y los encabezados
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

        try {
            // Realiza la llamada POST y obtiene la respuesta
            ResponseEntity<String> response = restTemplate.exchange(this.CLAVEUNICABASEURLUSERINFO, HttpMethod.POST, entity, String.class);

            // Obtiene el cuerpo de la respuesta
            Gson gson = new Gson();
            UserInfoClaveUnicaDto userInfoClaveUnica = gson.fromJson(response.getBody(), UserInfoClaveUnicaDto.class);

            return userInfoClaveUnica;

        } catch (HttpServerErrorException e) {
            // Manejo de errores del servidor HTTP
            throw new Exception("Error en el servidor al obtener la información del usuario de Clave Única: " + e.getMessage(), e);
        } catch (HttpClientErrorException e) {
            // Manejo de errores de cliente HTTP
            throw new Exception("Error de cliente al obtener la información del usuario de Clave Única: " + e.getMessage(), e);
        } catch (RestClientException e) {
            // Manejo de errores de comunicación REST
            throw new Exception("Error de comunicación REST al obtener la información del usuario de Clave Única: " + e.getMessage(), e);
        } catch (Exception e) {
            // Captura cualquier otra excepción no anticipada
            throw new Exception("Error inesperado al obtener la información del usuario de Clave Única: " + e.getMessage(), e);
        }
    }
}
