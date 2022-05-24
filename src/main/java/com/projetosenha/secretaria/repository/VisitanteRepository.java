package com.projetosenha.secretaria.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.projetosenha.secretaria.model.Visitante;

public interface VisitanteRepository extends PagingAndSortingRepository<Visitante, Long>{

	//@Query("SELECT v FROM Visitante v WHERE v.rg LIKE %:p%")
	//public List<Visitante> findByRg(@Param("p") String rg);
	
	public List<Visitante> findByRg(String rg);

	//public Visitante findByRg(String rg);
	
	/**
 * 	@Query("SELECT t FROM TipoRestaurante t WHERE t.nome LIKE %:p%")
	public List<TipoRestaurante> buscaPorNome(@Param("p") String nome);
 * 
 * 
 * 	public Secretaria findByMatriculaAndSenha(String matricula, String senha);

 */
}
