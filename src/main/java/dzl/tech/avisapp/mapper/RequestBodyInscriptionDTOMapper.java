package dzl.tech.avisapp.mapper;

import dzl.tech.avisapp.Dto.InscriptionDTO;
import dzl.tech.avisapp.Entities.Utilisateur;

import java.util.function.Function;

public class RequestBodyInscriptionDTOMapper implements Function<Utilisateur, InscriptionDTO> {
    /**
     * Applies this function to the given argument.
     *
     * @param utilisateur the function argument
     * @return the function result
     */
    @Override
    public InscriptionDTO apply(Utilisateur utilisateur) {
        return new InscriptionDTO(
                utilisateur.getUsername(),
                utilisateur.getEmail(),
                utilisateur.getMdp()
        );
    }
}
