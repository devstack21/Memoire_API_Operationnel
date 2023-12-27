package dzl.tech.avisapp.Service;

import dzl.tech.avisapp.Entities.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service

public class NotificationService {
    JavaMailSender javaMailSender;
    public void envoyer(Validation validation){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("no-reply@dzl.dev");
        mailMessage.setTo(validation.getUtilisateur().getEmail());
        mailMessage.setSubject("Votre code d'activation");
        String texte = String.format("Bonjour %s , <br/> votre code d'activation est %s; Merci ",
                validation.getUtilisateur().getNom(),
                validation.getCode()
                );
        mailMessage.setText(texte);
        javaMailSender.send(mailMessage);

    }
}
