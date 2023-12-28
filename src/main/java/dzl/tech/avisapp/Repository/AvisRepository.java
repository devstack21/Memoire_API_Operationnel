package dzl.tech.avisapp.Repository;

import dzl.tech.avisapp.Entities.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AvisRepository extends JpaRepository<Avis, Integer> {
}
