package dzl.tech.avisapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dzl.tech.avisapp.Controller.CompteController;
import dzl.tech.avisapp.Dto.InscriptionDTO;
import dzl.tech.avisapp.Dto.ResponseInscriptionDTO;
import dzl.tech.avisapp.Entities.Avis;
import dzl.tech.avisapp.Entities.Role;
import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Enum.TypeDeRole;
import dzl.tech.avisapp.Repository.UtilisateurRepository;
import dzl.tech.avisapp.Service.UtilisateurService;
import dzl.tech.avisapp.mapper.RequestBodyInscriptionDTOMapper;
import dzl.tech.avisapp.mapper.ResponseInscriptionDTOMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
public class UtilisateurInscriptionTests{

    @LocalServerPort
    private final static int port = 8001;


    private static String baseUrlAPI = "http://localhost".concat(":").concat(port+"").concat("/api");


    private RestTemplate restTemplate = new RestTemplate();
    private MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);


    private HttpHeaders httpHeaders = new HttpHeaders();
    @Test
    public void InscriptionTestSuccess() throws Exception {

        // mock user
        Utilisateur utilisateur =  Utilisateur.builder()
                .username("devDjob")
                .mdp("devDjob1234")
                .email("devDjob@gmail.com")
                .build();
        Role roleUtilisateur = new Role();
        roleUtilisateur.setLibelle(TypeDeRole.UTILISATEUR);
        utilisateur.setRole(roleUtilisateur);

        //Setup
        baseUrlAPI = baseUrlAPI.concat("/user/inscription");
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        this.httpHeaders.set("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZXZzZWNvcHNAZ21haWwuY29tIiwiaWQiOjEzLCJleHAiOjE3MDcyNTgzMzQsImp0aSI6IjEzIn0.O7OA3rDUEHSlANnjRoTJGkFU_ARo_AYwFWZBlh2tXoA");

        // Ajout des convertisseurs de messages Jackson et String
        restTemplate.getMessageConverters().add( new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add( new StringHttpMessageConverter());

        // Simuler la réponse attendue du serveur lorsque l'endpoint est appelé
        mockServer.expect(requestTo(baseUrlAPI))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK));

        HttpEntity<Utilisateur> request = new HttpEntity<>(utilisateur , this.httpHeaders);
       ResponseEntity<ResponseInscriptionDTO> response=  restTemplate.postForEntity(baseUrlAPI ,request, ResponseInscriptionDTO.class);

        // Vérifier que le statut de la réponse est OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Vérifier que toutes les attentes ont été satisfaites
        mockServer.verify();
    }


    @Test
    public void InscriptionTestInvalidMail() {
        Utilisateur user = Utilisateur.builder()
                .active(true)
                .username("devSecOpsPython")
                //.mdp(passwordEncoder.encode("devSecOpsPython1234"))
                .email("devSecOpsPythongmail.com")
                .role(
                        Role.builder()
                                .libelle(TypeDeRole.UTILISATEUR)
                                .build()
                )
                .build();


    }




























































}