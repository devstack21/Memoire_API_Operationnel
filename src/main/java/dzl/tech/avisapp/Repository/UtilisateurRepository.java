package dzl.tech.avisapp.Repository;

import dzl.tech.avisapp.Entities.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByEmail(String email);

}
