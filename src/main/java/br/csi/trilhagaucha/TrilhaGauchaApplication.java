package br.csi.trilhagaucha;

import br.csi.trilhagaucha.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrilhaGauchaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrilhaGauchaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UsuarioRepository repository) {
		return args -> {
//			Usuario usuario = new Usuario("Bruno Muniz","bruno@gmail.com","123456 ");
//			repository.save(usuario);
		};

	}
}
