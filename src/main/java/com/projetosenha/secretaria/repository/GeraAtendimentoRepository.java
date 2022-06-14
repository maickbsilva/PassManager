package com.projetosenha.secretaria.repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.projetosenha.secretaria.model.GeraAtendimento;


public interface GeraAtendimentoRepository extends PagingAndSortingRepository<GeraAtendimento, Long> {

@Query("SELECT ga FROM GeraAtendimento ga WHERE ga.atendimento = false AND ga.horaAtendimento = CURRENT_DATE() ORDER BY ga.pref DESC")
public List<GeraAtendimento> buscaPorDia();

@Query("SELECT ga FROM GeraAtendimento ga WHERE ga.atendimento = true AND ga.horaAtendimento = CURRENT_DATE() ORDER BY ga.horaFimAtendimento DESC")
public List<GeraAtendimento> buscaUltimos();

@Query("SELECT ga FROM GeraAtendimento ga WHERE ga.atendimento = true AND ga.horaAtendimento = CURRENT_DATE() ORDER BY ga.horaFimAtendimento DESC")
public Page<GeraAtendimento> buscaUltimosAt(PageRequest pageable);

@Query("SELECT ga FROM GeraAtendimento ga WHERE ga.pref = true AND ga.atendimento = false")
public List<GeraAtendimento> preferencial();

@Query("SELECT ga FROM GeraAtendimento ga WHERE ga.pref = false AND ga.atendimento = false")
public List<GeraAtendimento> comum();
}



/*
 * package com.projetosenha.secretaria.repository;
 * 
 * import java.util.List;
 * 
 * import
 * org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.
 * Pageable; import org.springframework.data.domain.Page; import
 * org.springframework.data.domain.PageRequest; import
 * org.springframework.data.jpa.repository.Query; import
 * org.springframework.data.repository.PagingAndSortingRepository; import
 * org.springframework.data.repository.query.Param; import
 * com.projetosenha.secretaria.model.GeraAtendimento;
 * 
 * public interface GeraAtendimentoRepository extends
 * PagingAndSortingRepository<GeraAtendimento, Long> {
 * 
 * @Query("SELECT ga FROM GeraAtendimento ga WHERE ga.atendimento = false AND ga.horaAtendimento = CURRENT_DATE() ORDER BY ga.pref DESC"
 * ) public List<GeraAtendimento> buscaPorDia();
 * 
 * @Query("SELECT ga FROM GeraAtendimento ga WHERE ga.atendimento = true AND ga.horaAtendimento = CURRENT_DATE() ORDER BY ga.horaFimAtendimento DESC"
 * ) public List<GeraAtendimento> buscaUltimos();
 * 
 * @Query("SELECT ga FROM GeraAtendimento ga WHERE ga.atendimento = true AND ga.horaAtendimento = CURRENT_DATE() ORDER BY ga.horaFimAtendimento DESC"
 * ) public Page<GeraAtendimento> buscaUltimosAt(PageRequest pageable);
 * 
 * }
 */