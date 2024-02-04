package dzl.tech.avisapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dzl.tech.avisapp.Controller.CompteController;
import dzl.tech.avisapp.Dto.InscriptionDTO;
import dzl.tech.avisapp.Entities.Role;
import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Enum.TypeDeRole;
import dzl.tech.avisapp.Repository.UtilisateurRepository;
import dzl.tech.avisapp.Service.UtilisateurService;
import dzl.tech.avisapp.mapper.RequestBodyInscriptionDTOMapper;
import dzl.tech.avisapp.mapper.ResponseInscriptionDTOMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CompteController.class)
public class UtilisateurInscriptionTests {

    @Autowired
    private MockMvc mockMvc;


    @Mock
    private UtilisateurRepository utilisateurRepository;

   

    @Mock
    private UtilisateurService utilisateurService;

       @Before
       public void setup(){
           WebApplicationContext webApplicationContext = null;
           mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
           
       }
    @Test
    public void InscriptionTestSuccess() throws Exception {
        // Setup
        InscriptionDTO requestBody = new InscriptionDTO("devopps", "devopps@gmail.com","devopps1234");
        Utilisateur utilisateur = new Utilisateur();
        Role roleUtilisateur = new Role();
        roleUtilisateur.setLibelle(TypeDeRole.UTILISATEUR);
        utilisateur.setRole(roleUtilisateur);
        utilisateur.setUsername("devPlug");
        utilisateur.setEmail("devp@gmail.com");
        utilisateur.setMdp("devop123adla");

        // Execute
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/inscription")
               
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new RequestBodyInscriptionDTOMapper().apply(utilisateur))))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
        
                  ;
                //.andExpect(jsonPath("$.username").value("devopps")) ;

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