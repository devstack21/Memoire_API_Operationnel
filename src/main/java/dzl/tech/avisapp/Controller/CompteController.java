package dzl.tech.avisapp.Controller;

import dzl.tech.avisapp.Dto.AuthenticationDTO;
import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Service.UtilisateurService;
import dzl.tech.avisapp.Service.ValidationService;
import dzl.tech.avisapp.securite.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path="user", consumes = APPLICATION_JSON_VALUE)
public class CompteController {
    private final UtilisateurService utilisateurService;
    private final ValidationService validationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping(path="inscription")
    public void inscription(@RequestBody Utilisateur utilisateur){
        System.out.println("INSCRIPTION");
        log.info("Inscription");
        this.utilisateurService.inscription(utilisateur);
    }
    @PostMapping(path="activation")
    public void activation(@RequestBody Map< String , String> activation){

        log.info("Validation");
       this.utilisateurService.activation(activation);

    }

    @PostMapping(path="connexion")
    public Map<String , String> connexion(@RequestBody AuthenticationDTO authenticationDTO) {

        final Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.mdp())
        );

        if(authenticate.isAuthenticated()) {
            return this.jwtService.generate(authenticationDTO.username());
        }

        return null;
    }

}
