package dzl.tech.avisapp.Service;

import dzl.tech.avisapp.Entities.Validation;
import dzl.tech.avisapp.Enum.TypeDeRole;
import org.springframework.transaction.annotation.Transactional;
import dzl.tech.avisapp.Entities.Role;
import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Repository.UtilisateurRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository ;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService ;


    public void inscription(Utilisateur utilisateur){

        if(!utilisateur.getEmail().contains("@") || !utilisateur.getEmail().contains(".")) throw new RuntimeException("Le mail est invalide");
        Optional<Utilisateur> utilisateurOptional = this.utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (utilisateurOptional.isPresent()) throw new RuntimeException("Cet email existe déja ");
        String mdpCrypt =  this.passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setMdp(mdpCrypt);
        Role roleUtilisateur = new Role();
        roleUtilisateur.setLibelle(TypeDeRole.UTILISATEUR);
        utilisateur.setRole(roleUtilisateur);
        utilisateur = this.utilisateurRepository.save(utilisateur);
        this.validationService.enregistrerValidation(utilisateur);

    }
    public void activation(@RequestBody Map< String , String> activation){
        Validation validation = this.validationService.lireEnFonctionDuCode(activation.get("code"));
        if(Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre code a expiré");
        }
        Utilisateur utilisateurActiver = this.utilisateurRepository.findById(validation.getUtilisateur().getId()).orElseThrow(
                ()-> new RuntimeException("Utilisateur inconnu")
        );
        utilisateurActiver.setActive(true);
        this.utilisateurRepository.save(utilisateurActiver);

    }
    public List<Utilisateur> listeUtilisateur(){
        return this.utilisateurRepository.findAll();
    }
    public Utilisateur lireOuCreer(Utilisateur utilisateurACreer) {
        Optional<Utilisateur> utilisateurBDDOptional = this.utilisateurRepository.findByEmail(utilisateurACreer.getEmail());

        return utilisateurBDDOptional.orElseGet(() -> this.utilisateurRepository.save(utilisateurACreer));
    }

}
