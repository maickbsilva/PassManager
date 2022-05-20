package com.projetosenha.secretaria.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.projetosenha.secretaria.model.Secretaria;

public interface SecretariaRepository extends PagingAndSortingRepository<Secretaria, Long>{

	public Secretaria findByMatriculaAndSenha(String matricula, String senha);
}
