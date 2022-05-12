package com.projetosenha.secretaria.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

public class Visitante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@NotEmpty
	private String rg;

	private String foto;

	private Boolean pref;

	private String email;

	private String telefone;
	
	private Portaria idportaria;


}
