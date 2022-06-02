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
import com.projetosenha.secretaria.model.Visitante;
import com.projetosenha.secretaria.repository.PortariaRepository;
import com.projetosenha.secretaria.repository.VisitanteRepository;

@Controller
public class VisitanteController {

	@Autowired
	private VisitanteRepository repository;
	
	@Autowired
	private PortariaRepository repositoryP;
	
	@RequestMapping("cadVisitante")
	public String pagVisitante(Model model) {
		model.addAttribute("ports", repositoryP.findAll());
		return "cadVisitante";
	}
	
	@RequestMapping(value = "salvarVisitante", method = RequestMethod.POST)
	public String salvarVisitante(Visitante visit) {
		repository.save(visit);
		System.out.println(visit);
		return "redirect:cadVisitante";
		
	}
	
	@RequestMapping("listaVisit/{page}")
	public String listaPortaria(Model model, @PathVariable("page") int page) {

		PageRequest pageable = PageRequest.of(page - 1, 6, Sort.by(Sort.Direction.ASC, "id"));

		Page<Visitante> pagina = repository.findAll(pageable);

		model.addAttribute("visits", pagina.getContent());

		int totalPages = pagina.getTotalPages();

		List<Integer> numPaginas = new ArrayList<Integer>();

		for (int i = 1; i <= totalPages; i++) {
			numPaginas.add(i);
		}

		model.addAttribute("numPaginas", numPaginas);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("pagAtual", page);

		// retorna para a lista
		return "listaVisitante";

	}
	
	@RequestMapping("alterarV")
	public String alterar(Long id, Model model) {
		Visitante visit = repository.findById(id).get();
		model.addAttribute("v", visit);
		return "forward:cadVisitante";
	}
	
	@RequestMapping("excluirV")
	public String excluir(Long id) {
		repository.deleteById(id);
		return "redirect:listaVisit/1";
	}
	
	@RequestMapping("buscar")
	public String buscaPorRG(String rg, Model model) {
		model.addAttribute("visits", repository.findByRg(rg));
		return "listaVisitante";
	}
	
	
	
}
