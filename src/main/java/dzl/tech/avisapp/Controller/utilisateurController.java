package dzl.tech.avisapp.Controller;

import dzl.tech.avisapp.Dto.AuthenticationDTO;
import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Entities.Validation;
import dzl.tech.avisapp.Service.UtilisateurService;
import dzl.tech.avisapp.Service.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path="user")
public class utilisateurController {
    private final UtilisateurService utilisateurService;
    private final ValidationService validationService;

    @PostMapping(path="inscription")
    public void inscription(@RequestBody Utilisateur utilisateur){

    log.info("Inscription");
        this.utilisateurService.inscription(utilisateur);
    }
    @PostMapping(path="activation")
    public void activation(@RequestBody Map< String , String> activation){

        log.info("Validation");
       this.utilisateurService.activation(activation);

    }
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Utilisateur> listeUtilisateur(@RequestParam(required=false) Integer id){

        return this.utilisateurService.listeUtilisateur(id);
    }


}
