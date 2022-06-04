package com.projetosenha.secretaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetosenha.secretaria.annotation.SecretariaAnnotation;
import com.projetosenha.secretaria.model.Portaria;
import com.projetosenha.secretaria.model.TipoCurso;
import com.projetosenha.secretaria.repository.TipoCursoRepository;

@Controller
public class TipoCursoController {
	
	@Autowired
	private TipoCursoRepository repository;
	
	@SecretariaAnnotation
	@RequestMapping("tipoCurso")
	public String pagTipoCurso() {
		return "cadTipoCurso";
	}
	
	@SecretariaAnnotation
	@RequestMapping(value = "salvarTipoCurso", method = RequestMethod.POST)
	public String salvarTipoCurso(TipoCurso tcurso) {
		repository.save(tcurso);
		return "redirect:tipoCurso";
	}
	
	@SecretariaAnnotation
	@RequestMapping("listaTipoCurso")
	public String listaTipoCurso(Model model) {
		model.addAttribute("tipos", repository.findAll());
		return "listaTipoCurso";

	}
	
	@SecretariaAnnotation
	@RequestMapping("alterarTC")
	public String alterar(Long id, Model model) {
		TipoCurso tipos = repository.findById(id).get();
		model.addAttribute("tc", tipos);
		return "forward:tipoCurso";
	}

	@SecretariaAnnotation
	@RequestMapping("excluirTC")
	public String excluir(Long id) {
		repository.deleteById(id);
		return "redirect:listaTipoCurso";
	}
}
