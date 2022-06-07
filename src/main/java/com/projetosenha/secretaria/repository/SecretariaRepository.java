package com.projetosenha.secretaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.projetosenha.secretaria.model.Secretaria;

public interface SecretariaRepository extends PagingAndSortingRepository<Secretaria, Long>{

	public Secretaria findByMatriculaAndSenhaAndAtivo(String matricula, String senha, boolean ativo);
	
	@Query("SELECT s FROM Secretaria s WHERE s.nome LIKE %:p%")
	public List<Secretaria> findByNome(@Param("p") String nome);

}
