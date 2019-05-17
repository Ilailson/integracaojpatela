package integrandojpatela.com.aula.repository;

import integrandojpatela.com.aula.model.Pessoa;

/**
 * Responsável por checar se o usuário está logado
 * @author Ilailson Rocha
 *
 */
public interface IDaoPessoa {

	Pessoa consultarUsuario(String login, String senha);
	
}
