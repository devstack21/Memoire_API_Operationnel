package dzl.tech.avisapp.Controller;

import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Service.UtilisateurService;
import dzl.tech.avisapp.Service.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path="account")
public class CompteController {
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


}
