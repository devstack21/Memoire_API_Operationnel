package dzl.tech.avisapp.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @Email(message = "L'email doit être valide")
        @NotEmpty(message = "L'email ne doit pas être vide")
        @NotNull(message = "l'email ne doit pas etre nulle")
     String username ,
     @NotEmpty(message = "Le mot de passe ne doit pas etre vide") String mdp
) {
}
