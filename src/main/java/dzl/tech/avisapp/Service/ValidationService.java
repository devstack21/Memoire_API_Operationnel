package dzl.tech.avisapp.Service;

import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Entities.Validation;
import dzl.tech.avisapp.Repository.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;


@AllArgsConstructor
@Service

public class ValidationService {
    private final ValidationRepository validationRepository;
    private final NotificationService notificationService;
    public void enregistrerValidation(Utilisateur utilisateur){
        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);
        Instant creation =  Instant.now();
        validation.setCreation(creation);
        //expiration dans 10 min
        Instant expiration =  creation.plus(10, ChronoUnit.MINUTES );
        validation.setExpiration(expiration);
        Random random = new Random();
        // valeur random de 6 chiffres avec max 999999
        int randomCode = random.nextInt(999999);
        String code = String.format("%06d" , randomCode);
        validation.setCode(code);
        this.validationRepository.save(validation);
        this.notificationService.envoyer(validation);

    }

    public Validation lireEnFonctionDuCode(String code){
        return this.validationRepository.findByCode(code).orElseThrow(
                ()-> new RuntimeException("Votre code est invalide")
        );
    }
}
