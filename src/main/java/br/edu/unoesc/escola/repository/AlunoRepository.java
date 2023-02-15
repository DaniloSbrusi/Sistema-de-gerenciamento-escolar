package br.edu.unoesc.escola.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.unoesc.escola.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long>{
	
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);	
	
	@Query("Select a from Aluno a where a.salario >= :filtro order by salario")
	public List<Aluno> porSalario(@Param("filtro") BigDecimal filtro);
	
	@Query("Select a from Aluno a where upper(a.nome) like upper(concat('%', :filtro, '%')) order by nome")
	public List<Aluno> findByParteNome(@Param("filtro") String filtro);
	
}
