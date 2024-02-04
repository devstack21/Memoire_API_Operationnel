package dzl.tech.avisapp.mapper;

import dzl.tech.avisapp.Dto.InscriptionDTO;
import dzl.tech.avisapp.Dto.ResponseInscriptionDTO;
import dzl.tech.avisapp.Entities.Utilisateur;

import java.util.function.Function;

public class ResponseInscriptionDTOMapper implements Function<Utilisateur , ResponseInscriptionDTO> {

    public ResponseInscriptionDTO apply(Utilisateur utilisateur) {
        return new ResponseInscriptionDTO(
                utilisateur.getId(),
                utilisateur.getUsername(),
                "Inscription reussie avec succès",
                "AUTH001"
        );
    }
}
