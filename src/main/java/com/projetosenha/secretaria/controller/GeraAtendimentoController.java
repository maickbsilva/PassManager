package com.projetosenha.secretaria.controller;

import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetosenha.secretaria.model.Curso;
import com.projetosenha.secretaria.model.GeraAtendimento;
import com.projetosenha.secretaria.model.Visitante;
import com.projetosenha.secretaria.repository.AssuntoRepository;
import com.projetosenha.secretaria.repository.GeraAtendimentoRepository;
import com.projetosenha.secretaria.repository.VisitanteRepository;

@Controller
public class GeraAtendimentoController {

	@Autowired
	private GeraAtendimentoRepository repository;

	@Autowired
	private AssuntoRepository repositoryA;

	@Autowired
	private VisitanteRepository repositoryV;

	@RequestMapping("geraAtendimento")
	public String pagAtendimento(Model model, Long id) {

		model.addAttribute("assunto", repositoryA.findAll());
		model.addAttribute("v", repositoryV.findById(id));
		Visitante t = repositoryV.findById(id).get();
		model.addAttribute("v", t);

		return "geraAtendimento";
	}

	@RequestMapping("salvarGeraAt")
	public String salvarGeraAt(GeraAtendimento gera) {
		gera.setHoraAtendimento(Calendar.getInstance());
		repository.save(gera);
		return "redirect:listaVisit/1";
	}

	@RequestMapping("telaSenha")
	public String pagTela() {
		return "telaSenha";
	}

}
