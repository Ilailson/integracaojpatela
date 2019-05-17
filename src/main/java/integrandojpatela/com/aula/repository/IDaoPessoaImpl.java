package integrandojpatela.com.aula.repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import integrandojpatela.com.aula.jpaUtil.JPAUtil;
import integrandojpatela.com.aula.model.Pessoa;

/**
 * Responsável por implementar a interface IDaoPessoa.
 * @author Ilailson Rocha
 * 
 * @see PessoaBean
 *
 */
public class IDaoPessoaImpl implements IDaoPessoa {

	@Override
	public Pessoa consultarUsuario(String login, String senha) {
		Pessoa pessoa = null;

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		pessoa = (Pessoa) entityManager
				.createQuery("select p from Pessoa p where p.login = '" + login + "' and p.senha = '" + senha + "'")
				.getSingleResult();//retorna um resultado

		entityTransaction.commit();
		entityManager.close();

		return pessoa; //retorna as informações de login e senha da pessoa.
	}

}
