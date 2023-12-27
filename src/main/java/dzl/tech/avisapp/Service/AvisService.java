package dzl.tech.avisapp.Service;

import dzl.tech.avisapp.Entities.Avis;
import dzl.tech.avisapp.Repository.AvisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AvisService {
    private final AvisRepository avisRepository;
    public void creer(Avis avis){
        this.avisRepository.save(avis);
    }

}
