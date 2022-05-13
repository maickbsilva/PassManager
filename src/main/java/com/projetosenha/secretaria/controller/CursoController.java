package com.projetosenha.secretaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetosenha.secretaria.model.Curso;
import com.projetosenha.secretaria.repository.CursoRepository;

@Controller
public class CursoController {

	@Autowired
	private CursoRepository repository;
	
	@RequestMapping("cadCurso")
	public String acessoSec() {
		return "cadCurso";
	}
	
	@RequestMapping(value = "salvarCurso", method = RequestMethod.POST)
	public String salvarCurso(Curso curso) {
		repository.save(curso);
		System.out.println(curso);
		return "redirect:cadCurso";
		
	}
}
