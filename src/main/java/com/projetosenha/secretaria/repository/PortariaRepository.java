package com.projetosenha.secretaria.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.projetosenha.secretaria.model.Portaria;
import com.projetosenha.secretaria.model.Secretaria;
import com.projetosenha.secretaria.model.Visitante;

public interface PortariaRepository extends PagingAndSortingRepository<Portaria, Long>{

	public Portaria findByLoginAndSenhaAndAtivo(String login, String senha, boolean ativo);
		
	public List<Portaria> findByNome(String nome);

}
