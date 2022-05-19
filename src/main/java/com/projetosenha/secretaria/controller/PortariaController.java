package com.projetosenha.secretaria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("listaPort")
	public String listaVisitantes(Model model) {
		model.addAttribute("ports", repository.findAll());
		return "listaPortaria";

	}

	@RequestMapping("alterarP")
	public String alterar(Long id, Model model) {
		Portaria ports = repository.findById(id).get();
		model.addAttribute("p", ports);
		return "forward:cadPortaria";
	}

	@RequestMapping("excluirP")
	public String excluir(Long id) {
		repository.deleteById(id);
		return "redirect:listaPort/1";
	}

}
