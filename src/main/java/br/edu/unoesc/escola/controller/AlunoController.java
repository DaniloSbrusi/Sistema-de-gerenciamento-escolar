package br.edu.unoesc.escola.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.escola.model.Aluno;
import br.edu.unoesc.escola.repository.AlunoRepository;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository repositorio;
	
	@GetMapping("/filtro")
	List<Aluno> listarComFiltro(@RequestParam("filtro") String filtro) {
		return repositorio.findByNomeContainingIgnoreCase(filtro);
	}
	
	@GetMapping
	public Iterable<Aluno> listarTudo() {
		return repositorio.findAll();
	}
	
	@GetMapping("/salario")
	List<Aluno> listarPorSalario(@RequestParam("filtro") String filtro) {
		return repositorio.porSalario(new BigDecimal(filtro));
	}
	
	@PostMapping
	public Aluno salvarAluno(@RequestBody Aluno aluno) {
		repositorio.save(aluno);
		return aluno;
	}
	
	@PutMapping
	public Aluno atualizarAluno(@RequestBody Aluno aluno) {
//		Optional<Aluno> a = repositorio.findById(aluno.getId());
		
//		if (a.isEmpty()) {
//			System.out.println("Aluno não encontrado!");
//		} else {
			Aluno b = repositorio.findById(aluno.getId()).get();
			b.setNome(aluno.getNome());
			b.setSalario(aluno.getSalario());
			b.setNascimento(aluno.getNascimento());
			return b;
//		}
	}
	
	@DeleteMapping("/{id}")
	public void deletarAluno(@PathVariable Long id) {
		Aluno a = repositorio.findById(id).get();
		repositorio.delete(a);
		System.out.println("Excluído: " + a.toString());
	}

	@GetMapping("/{id}")
	public Aluno listarAluno(@PathVariable Long id) {
		Aluno a = repositorio.findById(id).get();
		return a;
	}

}
