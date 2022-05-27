package com.projetosenha.secretaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.projetosenha.secretaria.model.GeraAtendimento;

public interface GeraAtendimentoRepository extends PagingAndSortingRepository<GeraAtendimento, Long>{

	//select nome do visitante where horaAtendimento == now() and atendimento == false ... pref no começos 
	
	//@Query("SELECT ts.visitante, ts.horaAtendimento, ts.pref FROM GeraAtendimento ts INNER JOIN Visitante on ( )")
	//public List<GeraAtendimento> buscaPorDia();
	
	/**
	 * 	@Query("SELECT t FROM TipoRestaurante t WHERE t.palavrasChave LIKE %:p%")
	public List<TipoRestaurante> buscaPorPChave(@Param("p") String palavrasChave);
	
	SELECT visitante.nome, hora_atendimento, pref from gera_atendimento 
	inner join visitante on (gera_atendimento.visitante_id = visitante.id) 
	where date(hora_atendimento) = curdate() and atendimento = false order by pref = 0
	 
	 *METODO QUE COLOCA TRUE NO ATENDIMENTO
	 *update gera_atendimento set atendimento = false where id = 1
	 *
	 */
}
