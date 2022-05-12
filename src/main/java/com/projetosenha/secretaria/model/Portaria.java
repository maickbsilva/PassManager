package com.projetosenha.secretaria.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Portaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String login;
	
	private String senha;
	
	
	/*
	 * 
	 * //metodo set que aplica o hash na senha
		public void setSenha(String senha) {
			
			this.senha = HashUtil.hash(senha);
		}
		//método que "seta" o hash na senha
		public void setSenhaComHash(String hash) {
			this.senha = hash;
		}
	 * 
	 */
} 
