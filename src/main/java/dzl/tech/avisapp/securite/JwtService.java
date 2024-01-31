package dzl.tech.avisapp.securite;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Service.UtilisateurService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class JwtService {
    private final String TOKEN_DECODE = "5159de98b3a4e67f592e9bb29ab3885bb1f850bddf2aa0ee1cb527f585b34050";
    private final UtilisateurService utilisateurService ;
    public Map<String , String> generate(String username){
        Utilisateur utilisateur = (Utilisateur) this.utilisateurService.loadUserByUsername(username);

        return this.generateJwt(utilisateur) ;
    }
    public Map<String , String> generateJwt(Utilisateur utilisateur){
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 30 * 60 * 1000 * 4;
        final Map<String , Object> claims = Map.of(
                "id" , utilisateur.getId(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT ,utilisateur.getEmail()
        );
        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(utilisateur.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return  Map.of("bearer" , bearer) ;
    }
    private Key getKey(){
        final byte[] decoder= Decoders.BASE64.decode(TOKEN_DECODE);
        return Keys.hmacShaKeyFor(decoder);
    }

    public String getUserIdByToken(String token) {

        return this.getClaim(token , Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToke(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToke(String token) {
        return this.getClaim(token ,Claims::getExpiration );
    }
    private<T> T getClaim(String token , Function<Claims, T> function ){

        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }
    private Claims getAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
}
