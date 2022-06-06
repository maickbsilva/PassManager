package com.projetosenha.secretaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetosenha.secretaria.annotation.SecretariaAnnotation;
import com.projetosenha.secretaria.model.PeriodoCurso;
import com.projetosenha.secretaria.repository.PeriodoCursoRepository;

@Controller
public class PeriodoCursoController {
	
	@Autowired
	private PeriodoCursoRepository repository;
	
	@SecretariaAnnotation
	@RequestMapping("cadPeriodoCurso")
	public String pagCadPCurso() {
		return "cadPeriodoCurso";
	}
	
	@SecretariaAnnotation
	@RequestMapping(value = "salvarPC", method = RequestMethod.POST)
	public String salvarPC(PeriodoCurso periodocurso) {
		repository.save(periodocurso);
		return "redirect:cadPeriodoCurso";
	}
	
	@SecretariaAnnotation
	@RequestMapping("listaPc")
	public String listaPC(Model model){
		model.addAttribute("periodocurso", repository.findAll());
		return "listaPc";
	}
	
	@SecretariaAnnotation
	@RequestMapping("alterarPc")
	public String alterar(Long id, Model model) {
		PeriodoCurso periodocurso = repository.findById(id).get();
		model.addAttribute("pc", periodocurso);
		return "forward:cadPeriodoCurso";
	}

	@SecretariaAnnotation
	@RequestMapping("excluirPc")
	public String excluir(Long id) {
		repository.deleteById(id);
		return "redirect:listaPc";
	}
	
}
