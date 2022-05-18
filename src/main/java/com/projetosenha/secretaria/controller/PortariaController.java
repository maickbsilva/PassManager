package com.projetosenha.secretaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetosenha.secretaria.model.Portaria;
import com.projetosenha.secretaria.repository.PortariaRepository;

@Controller
public class PortariaController {
	
	@Autowired
	private PortariaRepository repository;
	
	@RequestMapping("cadPortaria")
	public String pagPortaria() {
		return "cadPortaria";
	}
	
	@RequestMapping(value = "salvarPortaria", method = RequestMethod.POST)
	public String salvarPortaria(Portaria portaria) {
		repository.save(portaria);
		System.out.println(portaria);
		return "redirect:cadPortaria";
		
	}

}
