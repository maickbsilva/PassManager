package com.projetosenha.secretaria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetosenha.secretaria.model.Assunto;
import com.projetosenha.secretaria.repository.AssuntoRepository;

@Controller
public class AssuntoController {

	@Autowired
	private AssuntoRepository repository;
	
	@RequestMapping("cadAssunto")
	public String pagAssunto() {
		return "cadAssunto";
	}
	
	@RequestMapping(value = "salvarAssunto", method = RequestMethod.POST)
	public String salvarAssunto(@Valid Assunto ass) {
		repository.save(ass);
		System.out.println(ass);
		return "redirect:cadAssunto";
		
	}
}
