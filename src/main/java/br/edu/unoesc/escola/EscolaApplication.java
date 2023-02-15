package br.edu.unoesc.escola;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.unoesc.escola.model.Aluno;
import br.edu.unoesc.escola.repository.AlunoRepository;

@SpringBootApplication
public class EscolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscolaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AlunoRepository repositorio) {
		return args -> {
			
			// Teste de armazenamento
			Aluno a = new Aluno(null, "Maria", new BigDecimal(1800), Date.valueOf("2003-11-18"));
			a = repositorio.save(a);
			System.out.println(a);

			System.out.println(repositorio.count());
			
			// Teste de deleção
			repositorio.delete(a);
			
			System.out.println(repositorio.count());
			
			// Teste de busca por ID
			Optional<Aluno> b = repositorio.findById(12L);
			if (b.isEmpty()) {
				System.out.println("Aluno não encontrado!");
			} else {
				System.out.println(b.get());
			}
			
			// Teste de busca geral
			System.out.println(repositorio.findAll());
			
			// Teste de busca por parte do nome
			System.out.println(repositorio.findByNomeContainingIgnoreCase("an"));
			
		};
	}

}
