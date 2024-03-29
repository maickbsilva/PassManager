package com.projetosenha.secretaria.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false, unique = true)
	private String nome;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String matriculaInicio;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String matriculaExpiracao;
	
	@ManyToOne
	private TipoCurso tpCurso;
	
	@ManyToOne
	private PeriodoCurso pCurso;
	
	private int idadeMinima;
	
	private String duracao;
	
	//private String periodo;
	
	//private int idadeMinina;
	
	//private String tipocurso
	
	//private String duracao;

}
