package com.projetosenha.secretaria.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
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
import com.projetosenha.secretaria.annotation.PortariaAnnotation;
import com.projetosenha.secretaria.annotation.Publico;
import com.projetosenha.secretaria.annotation.SecretariaAnnotation;
import com.projetosenha.secretaria.model.Portaria;
import com.projetosenha.secretaria.model.Secretaria;
import com.projetosenha.secretaria.repository.PortariaRepository;

@Controller
public class PortariaController {

	@Autowired
	private PortariaRepository repository;
	
	@SecretariaAnnotation
	@RequestMapping("cadPortaria")
	public String pagPortaria() {
		return "cadPortaria";
	}
	@SecretariaAnnotation
	@RequestMapping(value = "salvarPortaria", method = RequestMethod.POST)
	public String salvarPortaria(Portaria portaria) {
		portaria.setAtivo(true);
		repository.save(portaria);
		System.out.println(portaria);
		return "redirect:cadPortaria";

	}
	@SecretariaAnnotation
	@RequestMapping("listaPort")
	public String listaVisitantes(Model model) {
		model.addAttribute("ports", repository.findAll());
		return "listaPortaria";

	}
	@SecretariaAnnotation
	@RequestMapping("alterarP")
	public String alterar(Long id, Model model) {
		Portaria ports = repository.findById(id).get();
		model.addAttribute("p", ports);
		return "forward:cadPortaria";
	}
	@SecretariaAnnotation
	@RequestMapping("excluirP")
	public String excluir(Long id, Model model) {
		Portaria ports = repository.findById(id).get();
		ports.setAtivo(false);
		model.addAttribute("p", ports);
		repository.save(ports);
		return "redirect:listaPort";
	}
	@SecretariaAnnotation
	@RequestMapping("buscarPorNomeP")
	public String buscarPorNomeP(String nome, Model model) {
		model.addAttribute("ports", repository.findByNome(nome));
		return "listaPortaria";
	}
	

	@RequestMapping("loginP")
	@Publico
	public String login(Portaria portLogin, RedirectAttributes attr, HttpSession session, Model model) {
		
		Portaria port = repository.findByLoginAndSenhaAndAtivo(portLogin.getLogin(), portLogin.getSenha(), true);
		if (port == null) {
			//attr.addAttribute("mensagemErro", "Login e/ou senha inválido(s)");
			attr.addFlashAttribute("message", "Login e/ou senha inválido(s)");
			return "redirect:acessoNegadoP";
		} else {
			session.setAttribute("portLogado", port);
			return "telaInicioPortaria";
		}
	}
	
	@RequestMapping("loginPort")
	@Publico
	public String loginPort() {
		return "loginPort";
	}
	
	@PortariaAnnotation
	@RequestMapping("telaInicioPortaria")
	public String telaInicialPortaria() {
		return "telaInicioPortaria";
	}
	
	@RequestMapping("logoutP")
	public String logoutP(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@Publico
	@RequestMapping("acessoNegadoP")
	public String acessoNegado(Model model) {
		model.addAttribute("sec", "2");
		return "acessoNegado";
	}
	
}
