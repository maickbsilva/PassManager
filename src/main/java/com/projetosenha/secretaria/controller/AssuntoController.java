package com.projetosenha.secretaria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetosenha.secretaria.annotation.SecretariaAnnotation;
import com.projetosenha.secretaria.model.Assunto;
import com.projetosenha.secretaria.model.Curso;
import com.projetosenha.secretaria.repository.AssuntoRepository;

@Controller
public class AssuntoController {

	@Autowired
	private AssuntoRepository repository;

	@SecretariaAnnotation
	@RequestMapping("cadAssunto")
	public String pagAssunto() {
		return "cadAssunto";
	}

	@SecretariaAnnotation
	@RequestMapping(value = "salvarAssunto", method = RequestMethod.POST)
	public String salvarAssunto(@Valid Assunto ass) {
		repository.save(ass);
		System.out.println(ass);
		return "redirect:cadAssunto";
	}
	
	@SecretariaAnnotation
	@RequestMapping("listaAssunto")
	public String listaVisitantes(Model model) {
		model.addAttribute("assu", repository.findAll());
		return "listaAssunto";

	}
	
	@SecretariaAnnotation
	@RequestMapping("alterarA")
	public String alterar(Long id, Model model) {
		Assunto assu = repository.findById(id).get();
		model.addAttribute("a", assu);
		return "forward:cadAssunto";
	}

	@SecretariaAnnotation
	@RequestMapping("excluirA")
	public String excluir(Long id) {
		repository.deleteById(id);
		return "redirect:listaAssunto";
	}
	
	
}
