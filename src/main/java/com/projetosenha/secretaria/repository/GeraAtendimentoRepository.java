package com.projetosenha.secretaria.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.projetosenha.secretaria.model.GeraAtendimento;

public interface GeraAtendimentoRepository extends PagingAndSortingRepository<GeraAtendimento, Long>{

	//select nome do visitante where horaAtendimento == now() and atendimento == false ... pref no começos 
}
