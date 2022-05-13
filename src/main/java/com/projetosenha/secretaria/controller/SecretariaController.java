package com.projetosenha.secretaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.projetosenha.secretaria.model.Secretaria;
import com.projetosenha.secretaria.repository.SecretariaRepository;

@Controller
public class SecretariaController {

	@Autowired
	private SecretariaRepository repository;
	
	@RequestMapping("cadSec")
	public String acessoSec() {
		return "cadSecretaria";
	}

	@RequestMapping(value = "salvarSec", method = RequestMethod.POST)
	public String salvarSec(Secretaria sec) {
		repository.save(sec);
		System.out.println(sec);
		return "redirect: cadSecretaria";
		
	}

}