package dzl.tech.avisapp;
import dzl.tech.avisapp.Entities.Utilisateur;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(value = Suite.class)
@Suite.SuiteClasses({
							UtilisateurInscriptionTests.class,
							UtilisateurLoginTests.class,
							ValidationEmailTests.class,
							AvisApplicationTests.class
})
public class AvisUtilisateursApplicationTests{

}




