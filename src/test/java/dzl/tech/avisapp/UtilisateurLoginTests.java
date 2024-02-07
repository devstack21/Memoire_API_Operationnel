package dzl.tech.avisapp;

import dzl.tech.avisapp.Dto.AuthenticationDTO;
import dzl.tech.avisapp.Dto.ResponseInscriptionDTO;
import dzl.tech.avisapp.Entities.Utilisateur;
import lombok.AllArgsConstructor;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootTest

public class UtilisateurLoginTests{

    @LocalServerPort
    private final static int port = 8001;


    private static String baseUrlAPI = "http://localhost".concat(":").concat(port+"").concat("/api");


    private RestTemplate restTemplate = new RestTemplate();
    private MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);


    private HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    public void ConnexionTestSuccess(){

        //Setup
        baseUrlAPI = baseUrlAPI.concat("/user/connexion");
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Ajout des convertisseurs de messages Jackson et String
        restTemplate.getMessageConverters().add( new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add( new StringHttpMessageConverter());

        // Simuler la réponse attendue du serveur lorsque l'endpoint est appelé
        mockServer.expect(requestTo(baseUrlAPI))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK));

    }
    @Test
    public void ConnexionTestInvalidEmail(){

    }
    @Test
    public void ConnexionTestTokenIsReturned(){

    }
}