package com.projetosenha.secretaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.projetosenha.secretaria.util.HashUtil;

import lombok.Data;

@Data
@Entity
public class Portaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@NotEmpty
	@Column(nullable = false, unique = true)
	private String login;

	@NotEmpty
	private String senha;
	
	// metodo set que aplica o hash na senha
	public void setSenha(String senha) {
		this.senha = HashUtil.hash(senha);
	}
	//para comparar e atualizar
	public void setSenhaComHash(String hash) {
		this.senha = hash;
	}
	
	private boolean ativo = false;
} 