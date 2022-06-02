package com.projetosenha.secretaria.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.projetosenha.secretaria.annotation.Publico;
import com.projetosenha.secretaria.model.Secretaria;
import com.projetosenha.secretaria.repository.SecretariaRepository;

@Controller
public class SecretariaController {

	@Autowired
	private SecretariaRepository repository;

	@RequestMapping("cadSec")
	public String acessoSec() {
		return "cadSecretaria";
	}

	@RequestMapping(value = "salvarSec", method = RequestMethod.POST)
	public String salvarSec(Secretaria sec) {
		sec.setAtivo(true);
		repository.save(sec);
		System.out.println(sec);
		return "cadSecretaria";

	}

	// request que leva para a lista
	@RequestMapping("listaSec/{page}")
	public String listaSecretaria(Model model, @PathVariable("page") int page) {

		// cria um pageable informando os parametros da pagina
		PageRequest pageable = PageRequest.of(page - 1, 6, Sort.by(Sort.Direction.ASC, "id"));

		// cria um page de adm atraves dos parametros passados ao repository
		Page<Secretaria> pagina = repository.findAll(pageable);

		// adiciona à model a lista com os admins
		model.addAttribute("secs", pagina.getContent());

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

		// retorna para a lista
		return "listaSecretaria";

	}

	@RequestMapping("alterar")
	public String alterar(Long id, Model model) {
		Secretaria sec = repository.findById(id).get();
		model.addAttribute("s", sec);
		return "forward:cadSec";
	}

	@RequestMapping("excluir")
	public String excluir(Long id) {
		repository.deleteById(id);
		return "redirect:listaSec/1";
	}

	@RequestMapping("login")
	@Publico
	public String login(Secretaria secLogin, RedirectAttributes attr, HttpSession session) {
		Secretaria sec = repository.findByMatriculaAndSenhaAndAtivo(secLogin.getMatricula(), secLogin.getSenha(), true);

		if (sec == null) {
			attr.addAttribute("mensagemErro", "Login e/ou senha inválido(s)");
			return "redirect:/";
		} else {
			session.setAttribute("secLogado", sec);
			return "redirect:/telaInicioSec";
		}
	}
	
	@RequestMapping("telaInicioSec")
	public String pagIniciaSecl() {
		return "telaInicioSecretaria";
	}

	@RequestMapping("loginSec")
	@Publico
	public String loginSec() {
		return "loginSec";
	}

}