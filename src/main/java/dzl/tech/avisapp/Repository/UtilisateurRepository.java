package dzl.tech.avisapp.Repository;

import dzl.tech.avisapp.Entities.Utilisateur;
import jdk.jshell.execution.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur>findByUsername(String username);


}
