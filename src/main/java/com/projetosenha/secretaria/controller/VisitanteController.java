package com.projetosenha.secretaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.projetosenha.secretaria.model.Portaria;
import com.projetosenha.secretaria.model.Visitante;
import com.projetosenha.secretaria.repository.VisitanteRepository;

@Controller
public class VisitanteController {

	@Autowired
	private VisitanteRepository repository;
	
	@RequestMapping("cadVisitante")
	public String pagVisitante() {
		return "cadVisitante";
	}
	
	@RequestMapping(value = "salvarVisitante", method = RequestMethod.POST)
	public String salvarVisitante(Visitante visit) {
		repository.save(visit);
		System.out.println(visit);
		return "redirect:cadVisitante";
		
	}
}
