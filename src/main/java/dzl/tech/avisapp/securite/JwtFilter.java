package dzl.tech.avisapp.securite;

import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Repository.UtilisateurRepository;
import dzl.tech.avisapp.Service.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class JwtFilter extends OncePerRequestFilter {

    private UtilisateurService utilisateurService ;
    private JwtService jwtService;
    private UtilisateurRepository utilisateurRepository;

    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null ;
        int userID = 0;
        Boolean isTokenExpired = true ;
        final String authorization = request.getHeader("Authorization");
        if(authorization!= null && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            isTokenExpired = jwtService.isTokenExpired(token);
            userID = Integer.parseInt(jwtService.getUserIdByToken(token));
            log.info("TOKEN_VALUE "+jwtService.getUserIdByToken(token));

        }
        //log.info("TOKEN_IS_EXPIRED "+isTokenExpired+" USERID "+userID+" TOKEN "+token);
        if(isTokenExpired && userID != 0  && SecurityContextHolder.getContext().getAuthentication() == null ){
            Optional<Utilisateur> utilisateur = this.utilisateurRepository.findById(userID);
            if(utilisateur.isPresent()){
                //log.info("PRESENT ---- ");
                String email = utilisateur.get().getEmail();
                Utilisateur userDetails  = (Utilisateur) utilisateurService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(userDetails , null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }

            filterChain.doFilter(request , response);
    }
}
