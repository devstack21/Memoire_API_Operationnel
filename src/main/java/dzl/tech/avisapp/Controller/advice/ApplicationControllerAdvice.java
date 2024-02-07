package dzl.tech.avisapp.Controller.advice;


import dzl.tech.avisapp.Dto.Error.Dto.ErrorEntity;
import dzl.tech.avisapp.Dto.Error.Dto.ErrorValidation;
import dzl.tech.avisapp.exception.controller.utilisateur.UtilisateurException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ApplicationControllerAdvice {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class})
    public @ResponseBody ErrorEntity handleException(EntityNotFoundException exception ){
        return new ErrorEntity(exception.getMessage(), null);
    }
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({UtilisateurException.class})
    public @ResponseBody ErrorEntity handleException(UtilisateurException exception ){
        return new ErrorEntity(exception.getMessage() , exception.getCode());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public @ResponseBody ErrorValidation handleException(MethodArgumentNotValidException exception ){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage(); // Récupérer le message d'erreur personnalisé à partir du fichier de propriétés
            errors.put(fieldName, errorMessage);
        });
        return new ErrorValidation(errors , "CON001");
    }

}
