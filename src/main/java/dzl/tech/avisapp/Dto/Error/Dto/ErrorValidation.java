package dzl.tech.avisapp.Dto.Error.Dto;

import java.util.Map;

public record ErrorValidation(
        Map<String , String> errors,
        String code
) {
}
