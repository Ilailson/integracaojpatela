package integrandojpatela.com.aula.repository;

import java.util.List;

import integrandojpatela.com.aula.model.Lancamento;
/**
 * Interface de lançamentos. 
 * @author Ilailson Rocha
 *
 */
public interface IDaoLancamento {

	/**
	 * Responsável por consultar os lançamentos individuais de cada usuário. 
	 * @param codUser recebe o código do usuário que está logado. 
	 * @return todos os lançamentos do código do usuário. 
	 */
	List<Lancamento> consultar(Long codUser);
}
