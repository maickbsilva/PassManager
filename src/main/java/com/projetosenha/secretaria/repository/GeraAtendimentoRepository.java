package com.projetosenha.secretaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.projetosenha.secretaria.model.GeraAtendimento;

public interface GeraAtendimentoRepository extends PagingAndSortingRepository<GeraAtendimento, Long>{
	
	@Query("SELECT ga FROM GeraAtendimento ga WHERE ga.atendimento = false AND ga.horaAtendimento = CURRENT_DATE() ORDER BY ga.pref DESC")
	public List<GeraAtendimento> buscaPorDia();
	
	/**
	@Query("SELECT ga FROM GeraAtendimento ga WHERE ga.atendimento = false AND ga.horaAtendimento = CURRENT_DATE() ORDER BY ga.pref DESC LIMIT 1")
	public List<GeraAtendimento> buscaSomenteUm();
	 *
	 */
}
