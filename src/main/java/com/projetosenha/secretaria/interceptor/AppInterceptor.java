package com.projetosenha.secretaria.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.projetosenha.secretaria.annotation.Publico;

@Component
public class AppInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// variavel para obter a URI
		String uri = request.getRequestURI();

		// variavel para a sessao
		HttpSession sessionS = request.getSession();
		HttpSession sessionP = request.getSession();

		if (uri.startsWith("/error")) {
			return true;
		}

		if (handler instanceof HandlerMethod) {
			// casting de Object para HandlerMethod
			HandlerMethod metodo = (HandlerMethod) handler;

			// verifica se este metodo é publico
			if (metodo.getMethodAnnotation(Publico.class) != null) {
				return true;
			}
			// verifica se existe um usuario logado
			if (sessionS.getAttribute("secLogado") != null) {
				return true;
			}
			if (sessionP.getAttribute("portLogado") != null) {
				return true;
			}
			// redireciona para a pagina inicial
			response.sendRedirect("/");
			return false;

		}
		return true;
	}
}
