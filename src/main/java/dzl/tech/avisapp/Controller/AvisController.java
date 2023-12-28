package dzl.tech.avisapp.Controller;

import dzl.tech.avisapp.Entities.Avis;
import dzl.tech.avisapp.Service.AvisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RequestMapping(path="avis")
@RestController
public class AvisController {
    private final AvisService avisService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Avis avis){
        this.avisService.creer(avis);
    }


    @GetMapping(path="{id}" , produces = APPLICATION_JSON_VALUE)
    public List<Avis> afficherAvis(@PathVariable int id){
        return this.avisService.afficherAvis(id);
    }
}
