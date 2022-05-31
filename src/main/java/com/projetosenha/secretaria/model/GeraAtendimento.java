package com.projetosenha.secretaria.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class GeraAtendimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Assunto assunto;
	
	@ManyToOne
	private Visitante visitante;
	
	private Date horaAtendimento;
	
	private Boolean pref = false;
	
	private Boolean atendimento = false;
}
