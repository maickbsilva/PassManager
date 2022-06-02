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
import com.projetosenha.secretaria.repository.PeriodoCursoRepository;
import com.projetosenha.secretaria.repository.TipoCursoRepository;

@Controller
public class CursoController {

	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private TipoCursoRepository repositoryTC;
	
	@Autowired
	private PeriodoCursoRepository repositoryPC;
	
	@RequestMapping("cadCurso")
	public String acessoSec(Model model) {
		model.addAttribute("tipos",repositoryTC.findAll());
		model.addAttribute("periodocurso", repositoryPC.findAll());
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
	
	@RequestMapping("buscarPorNome")
	public String buscaPorNome(String nome, Model model){
		model.addAttribute("cursos", repository.findByNome(nome));
		return "listaCurso";
	}
	
	@RequestMapping("buscarPorTpCurso")
	public String buscaPorTpCurso(Long tpCurso, Model model){
		model.addAttribute("cursos", repository.buscaPorTpCurso(tpCurso));
		return "listaCurso";
	}
	
	
	
/**
 * 
 * 	@RequestMapping("buscar")
	public String buscaPorRG(String rg, Model model) {
		model.addAttribute("visits", repository.findByRg(rg));
		return "listaVisitante";
	}
	
	
	
	
 * 	@RequestMapping("busca")
	public String busca(String escolha, String palavra, Model model) {

		if (escolha.equals("nome")) {
			model.addAttribute("rests", repository.buscaPorNome(palavra));
		} else if (escolha.equals("descricao")) {
			model.addAttribute("rests", repository.buscaPorDescricao(palavra));
		} else {

			model.addAttribute("rests", repository.buscaPorPChave(palavra));
		}

		return "listaRest";
	}
 */
	
}
