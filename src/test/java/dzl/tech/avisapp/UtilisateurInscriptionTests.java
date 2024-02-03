package dzl.tech.avisapp;

import dzl.tech.avisapp.Dto.InscriptionDTO;
import dzl.tech.avisapp.Entities.Role;
import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Enum.TypeDeRole;
import dzl.tech.avisapp.Repository.UtilisateurRepository;
import dzl.tech.avisapp.Service.UtilisateurService;
import dzl.tech.avisapp.Service.ValidationService;
import lombok.AllArgsConstructor;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.*;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.io.IOException;
import java.util.Optional;


@SpringBootTest
@WebMvcTest(controllers = UtilisateurService.class)
public class UtilisateurInscriptionTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void InscriptionTestSuccess() throws Exception , IOException {
//        Utilisateur user = Utilisateur.builder()
//                .active(true)
//                .username("devSecOpsPython")
//                //.mdp(passwordEncoder.encode("devSecOpsPython1234"))
//                .email("devSecOpsPython@gmail.com")
//                .role(
//                        Role.builder()
//                                .libelle(TypeDeRole.UTILISATEUR)
//                                .build()
//                )
//                .build();

//
        InscriptionDTO requestBody =  new InscriptionDTO("devopps" , "devopps@gmail.com","devopps1234");
        mockMvc.perform(post("api/user/inscription"));



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