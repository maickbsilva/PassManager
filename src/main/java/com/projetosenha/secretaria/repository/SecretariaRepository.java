package com.projetosenha.secretaria.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.projetosenha.secretaria.model.Portaria;
import com.projetosenha.secretaria.model.Secretaria;

public interface SecretariaRepository extends PagingAndSortingRepository<Secretaria, Long>{

	public Secretaria findByMatriculaAndSenhaAndAtivo(String matricula, String senha, boolean ativo);
	
	//	public List<Portaria> findByNome(String nome);
	
	public List<Secretaria> findByNome(String nome);

}
