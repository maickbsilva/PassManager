package com.projetosenha.secretaria.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetosenha.secretaria.annotation.PortariaAnnotation;
import com.projetosenha.secretaria.annotation.SecretariaAnnotation;
import com.projetosenha.secretaria.model.Curso;
import com.projetosenha.secretaria.model.GeraAtendimento;
import com.projetosenha.secretaria.model.Visitante;
import com.projetosenha.secretaria.repository.AssuntoRepository;
import com.projetosenha.secretaria.repository.GeraAtendimentoRepository;
import com.projetosenha.secretaria.repository.VisitanteRepository;

@Controller
public class GeraAtendimentoController {

	GeraAtendimento tira1;

	@Autowired
	private GeraAtendimentoRepository repository;

	@Autowired
	private AssuntoRepository repositoryA;

	@Autowired
	private VisitanteRepository repositoryV;

	@SecretariaAnnotation
	@PortariaAnnotation
	@RequestMapping("geraAtendimento")
	public String pagAtendimento(Model model, Long id) {
		model.addAttribute("assunto", repositoryA.findAll());
		model.addAttribute("v", repositoryV.findById(id));
		Visitante t = repositoryV.findById(id).get();
		model.addAttribute("v", t);

		return "geraAtendimento";
	}

	@SecretariaAnnotation
	@PortariaAnnotation
	@RequestMapping("salvarGeraAt")
	public String salvarGeraAt(GeraAtendimento gera) {
		Date now = new Date(System.currentTimeMillis());
		gera.setHoraAtendimento(now);
		repository.save(gera);
		return "redirect:listaVisit/1";
	}

	@SecretariaAnnotation
	@RequestMapping("telaSenha")
	public String pagTela() {
		return "telaSenha";
	}

	@SecretariaAnnotation
	@RequestMapping("painelSenha")
	public String telaSenha(Model model) {
		// passsa somente o primeiro indice da lista
		model.addAttribute("primeiro", repository.buscaPorDia().get(0));
		// começa a lista a partir do segundo
		model.addAttribute("telasenha", repository.buscaPorDia().listIterator(1));
		// lista de ultimos chamados
		model.addAttribute("ultimos", repository.buscaUltimos());
		return "painelSenha";
	}

	@SecretariaAnnotation
	@RequestMapping("painelAtendimento/{page}")
	public String painelAtendimento(Model model, @PathVariable("page") int page) {

		// cria um pageable informando os parametros da pagina
		PageRequest pageable = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.DESC, "id"));

		// cria um page de adm atraves dos parametros passados ao repository
		Page<GeraAtendimento> pagina = repository.buscaUltimosAt(pageable);

		// adiciona à model a lista com os admins
		model.addAttribute("ultimos", pagina.getContent());

		// variavel para o total de paginas
		int totalPages = pagina.getTotalPages();

		// cria um List de inteiros para armazenar os numeros das paginas
		List<Integer> numPaginas = new ArrayList<Integer>();

		// preencher o List com as paginas
		for (int i = 1; i <= totalPages; i++) {
			// adidonar a pagina ao list
			numPaginas.add(i);
		}

		// adiciona os valores a model
		model.addAttribute("numPaginas", numPaginas);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("pagAtual", page);
		
		// passsa somente o primeiro indice da lista
		model.addAttribute("primeiro", repository.buscaPorDia().get(0));
		// começa a lista a partir do segundo
		model.addAttribute("telasenha", repository.buscaPorDia().listIterator(1));

		// retorna para a lista
		return "painelAtendimento";

	}

	@SecretariaAnnotation
	@RequestMapping("atualizaSenha")
	public String atualizaSenha(Long id, Model model) {
		GeraAtendimento gera = repository.findById(id).get();
		gera.setAtendimento(true);
		Time now = new Time(System.currentTimeMillis());
		gera.setHoraFimAtendimento(now);
		model.addAttribute("telasenha", gera);
		repository.save(gera);
		return "forward:painelSenha";
	}
	
	@SecretariaAnnotation
	@RequestMapping("atualizaAtendimento")
	public String atualizaAtendimento(Long id, Model model) {
		GeraAtendimento gera = repository.findById(id).get();
		gera.setAtendimento(true);
		Time now = new Time(System.currentTimeMillis());
		gera.setHoraFimAtendimento(now);
		model.addAttribute("telasenha", gera);
		repository.save(gera);
		return "forward:painelAtendimento/1";
	}
}
