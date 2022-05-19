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

import com.projetosenha.secretaria.model.Curso;
import com.projetosenha.secretaria.model.Portaria;
import com.projetosenha.secretaria.model.Visitante;
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
	
	@RequestMapping("listaCurso/{page}")
	public String listaPortaria(Model model, @PathVariable("page") int page) {

		PageRequest pageable = PageRequest.of(page - 1, 6, Sort.by(Sort.Direction.ASC, "id"));

		Page<Curso> pagina = repository.findAll(pageable);

		model.addAttribute("cursos", pagina.getContent());

		int totalPages = pagina.getTotalPages();

		List<Integer> numPaginas = new ArrayList<Integer>();

		for (int i = 1; i <= totalPages; i++) {
			numPaginas.add(i);
		}

		model.addAttribute("numPaginas", numPaginas);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("pagAtual", page);

		// retorna para a lista
		return "listaCurso";

	}
	
	@RequestMapping("alterarC")
	public String alterar(Long id, Model model) {
		Curso cursos = repository.findById(id).get();
		model.addAttribute("c", cursos);
		return "forward:cadCurso";
	}

	@RequestMapping("excluirC")
	public String excluir(Long id) {
		repository.deleteById(id);
		return "redirect:listaCurso/1";
	}
	
	
}
