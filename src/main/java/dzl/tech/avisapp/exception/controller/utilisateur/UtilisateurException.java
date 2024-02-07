package dzl.tech.avisapp.exception.controller.utilisateur;

import lombok.Getter;

@Getter
public class UtilisateurException extends Exception {
    private String code ;
    public UtilisateurException() {
        super();
    }

    public UtilisateurException(String message , String code) {
        super(message);
        this.code = code ;
    }

    public UtilisateurException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilisateurException(Throwable cause) {
        super(cause);
    }
}