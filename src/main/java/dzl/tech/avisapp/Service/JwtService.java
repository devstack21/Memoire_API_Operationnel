package dzl.tech.avisapp.Service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    public String generateToken(String username){
        Map<String , Object> instance = new HashMap<>();
        return createToken(instance , username);

    }
    private String createToken(Map<String , Object> instance , String username){
        return " ";
    }
}
