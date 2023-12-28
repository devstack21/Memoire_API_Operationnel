package dzl.tech.avisapp.securite;

import dzl.tech.avisapp.Service.UtilisateurService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.AbstractSecurityBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class ConfigurationSecuriteApplication {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return
                httpSecurity
                        .csrf(AbstractHttpConfigurer::disable)
                        // autoriser des requetes spécifiques
                        .authorizeHttpRequests(
                             authorize ->
                                authorize.requestMatchers(POST, "/inscription").permitAll()
                                        .requestMatchers(POST , "/activation").permitAll()
                                        .requestMatchers(POST , "/connexion").permitAll()
                                        .requestMatchers(POST , "/avis").permitAll()
                                        .requestMatchers(GET , "/avis").permitAll()
                                        .requestMatchers(GET , "/utilisateur").permitAll()

                                        .anyRequest().authenticated()
                        ).build();

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
