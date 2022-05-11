package com.projetosenha.secretaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.projetosenha.secretaria.util.HashUtil;

import lombok.Data;
//cria os getters e setters
@Data
//mapeia a entidade para JPA
@Entity
public class Secretaria {
	// chave primária e auto-incremento
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique= true)
	private String nome;
	@Column(nullable = false, unique = true)
	@NotEmpty
	private String senha;
	
	//metodo set que aplica o hash na senha
		public void setSenha(String senha) {
			
			this.senha = HashUtil.hash(senha);
		}
		//método que "seta" o hash na senha
		public void setSenhaComHash(String hash) {
			this.senha = hash;
		}
		@Column(nullable = false, unique = true)
		@NotEmpty
	private String matricula;
	
}
