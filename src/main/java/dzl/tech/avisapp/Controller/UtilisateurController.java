package dzl.tech.avisapp.Controller;

import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Service.UtilisateurService;
import dzl.tech.avisapp.Service.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAnyAuthority;
import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAnyRole;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path="user")
public class UtilisateurController {
    private UtilisateurService utilisateurService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATEUR','MANAGEMENT_EXEC')")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Utilisateur> listeUtilisateur(@RequestParam(required=false) Integer id){

        return this.utilisateurService.listeUtilisateur(id);
    }


}
