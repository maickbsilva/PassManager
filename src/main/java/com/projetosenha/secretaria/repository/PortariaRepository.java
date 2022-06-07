package com.projetosenha.secretaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.projetosenha.secretaria.model.Portaria;

public interface PortariaRepository extends PagingAndSortingRepository<Portaria, Long>{

	public Portaria findByLoginAndSenhaAndAtivo(String login, String senha, boolean ativo);
	
	@Query("SELECT p FROM Portaria p WHERE p.nome LIKE %:p%")
	public List<Portaria> findByNome(@Param("p") String nome);

}
