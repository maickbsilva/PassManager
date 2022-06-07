package com.projetosenha.secretaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.projetosenha.secretaria.model.Curso;

public interface CursoRepository extends PagingAndSortingRepository<Curso, Long>{

	//@Query("SELECT t FROM TipoRestaurante t WHERE t.palavrasChave LIKE %:p%")
	//public List<TipoRestaurante> buscaPorPChave(@Param("p") String palavrasChave);
	
	@Query("SELECT c FROM Curso c ORDER BY c.tpCurso.tpCurso ASC")
	public List<Curso> buscaPorTpCurso(Long tpCurso);
	
	@Query("SELECT c FROM Curso c WHERE c.nome LIKE %:p%")
	public List<Curso> findByNome(@Param("p") String nome);
}
