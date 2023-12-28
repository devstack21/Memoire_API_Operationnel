package dzl.tech.avisapp.Service;

import dzl.tech.avisapp.Entities.Avis;
import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Repository.AvisRepository;
import dzl.tech.avisapp.Repository.UtilisateurRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AvisService {
    private AvisRepository avisRepository;
    private UtilisateurService utilisateurService;
    private UtilisateurRepository utilisateurRepository;
    public void creer(Avis avis){
        Utilisateur utilisateur = utilisateurService.lireOuCreer(avis.getUtilisateur());
        avis.setUtilisateur(utilisateur);
        this.avisRepository.save(avis);
    }

    public List<Avis> afficherAvis(int id) {
        Utilisateur utilisateur = this.utilisateurRepository.findById(id)
                .orElseThrow(
                        ()-> new RuntimeException("Utilisateur inconnu")
                );
        return this.avisRepository.findAll();
    }
}
