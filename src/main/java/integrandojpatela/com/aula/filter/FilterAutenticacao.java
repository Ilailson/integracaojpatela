package integrandojpatela.com.aula.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import integrandojpatela.com.aula.jpaUtil.JPAUtil;
import integrandojpatela.com.aula.model.Pessoa;
/**
 * 
 * @author Ilailson Rocha
 * 
 * Classe que ira controlar todas as páginas acessadas. 
 * Verifica se o usuário tá logado. 
 * Se não estiver logado irá fazer o redirecionamento para a tela de login. <p>
 * 
 * 
 *
 *WebFilter (urlPatterns="/*"): define que irá interceptar todas as páginas jsf.
 *E fará a verificação se o usuário esta logado. 
 *
 *@see PessoaBean
 *@see Pessoa
 */
@WebFilter(urlPatterns="/*")
public class FilterAutenticacao implements Filter{

	@Override
	public void destroy() {
	}

	/**
	 * Será executado em todas as requisições
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//pega a requisição e a sessão do usuário logado. 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");
		
		String url = req.getServletPath();//endereço do sistema na url para saber o que está sendo acessado.
		
		
		/*Se a url for diferente de index e usuarioLogado for nulo (usuário não está logado)
		 * Ou seja o usuário não esta logando e está tentando acessar uma pagina restrita
		 * redireciona para o index e executa a requisição request e response
		 * Return "", para parar a execução do java
		 * 
		 * Se não ele executado o sistema normalmente.
		 */
		if (!url.equalsIgnoreCase("index.jsf") && usuarioLogado == null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");//redireciona para o index para fazer o login
			dispatcher.forward(request, response);
			return ;
		}else {
			// executa as ações do request e do response
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * É executado quando está subindo o servidor <p>
	 * JPAUtil.getEntityManager(): inicializa levantando a conexão com o banco de dados
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		JPAUtil.getEntityManager();
	}

	
/*Objetivo dessa classe:
 *  Controlar todas as páginas acessadas.	
 *  
 *  Verificar se o usuário logado está correto. Se não direciona para a 
 *  tela de login.
 */
	
/*Interceptar todas as páginas
 * 	(urlPatterns="/*")
 */
	
}
