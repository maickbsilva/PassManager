package com.projetosenha.secretaria.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.projetosenha.secretaria.model.Portaria;
import com.projetosenha.secretaria.model.Secretaria;

public interface PortariaRepository extends PagingAndSortingRepository<Portaria, Long>{

	public Portaria findByLoginAndSenhaAndAtivo(String login, String senha, boolean ativo);
	
	//acrescentar bool ativo
}
