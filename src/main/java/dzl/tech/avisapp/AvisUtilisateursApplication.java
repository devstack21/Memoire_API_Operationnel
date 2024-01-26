package dzl.tech.avisapp;

import dzl.tech.avisapp.Entities.Role;
import dzl.tech.avisapp.Entities.Utilisateur;
import dzl.tech.avisapp.Enum.TypeDeRole;
import dzl.tech.avisapp.Repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@SpringBootApplication
public class AvisUtilisateursApplication implements CommandLineRunner {
	UtilisateurRepository utilisateurRepository;
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(AvisUtilisateursApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {

		Utilisateur admin = Utilisateur.builder()
				.active(true)
				.username("admin")
				.mdp(passwordEncoder.encode("admin"))
				.email("admindjob50@gmail.com")
				.role(
						Role.builder()
								.libelle(TypeDeRole.ADMINISTRATEUR)
								.build()
				)
				.build();
		this.utilisateurRepository.findByEmail("admindjob50@gmail.com")
				.orElseGet( ()->this.utilisateurRepository.save(admin));

		Utilisateur exec = Utilisateur.builder()
				.active(true)
				.username("manager_exec")
				.mdp(passwordEncoder.encode("manager_exec"))
				.email("managerdjob502@gmail.com")
				.role(
						Role.builder()
								.libelle(TypeDeRole.MANAGEMENT_EXEC)
								.build()
				)
				.build();
		this.utilisateurRepository.findByEmail("managerdjob502@gmail.com")
				.orElseGet( ()->this.utilisateurRepository.save(exec));


	}
}
