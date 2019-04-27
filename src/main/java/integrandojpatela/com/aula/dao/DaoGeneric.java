package integrandojpatela.com.aula.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import integrandojpatela.com.aula.jpaUtil.JPAUtil;

/**
 * Responsável por realizar o crud e pesquisa de forma genérica. Servirá para reutilizar código
 * envitando muitas repetições. 
 * @author Ilailson Rocha
 *
 * @param <E> pode ser qualquer classe mapeada. 
 */
public class DaoGeneric<E> {

	public void salvar(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();//pegando o entityManager que está na  JPAUTIL
		EntityTransaction entityTransaction = entityManager.getTransaction();//transação recebendo o entitymanager
		entityTransaction.begin(); //iniciando a transação. 
		
		entityManager.persist(entidade); //sicronizando para salvar sem retorna para a tela. 
		
		entityTransaction.commit(); //salvando no banco. 
		entityManager.close();//fechando a conexão com o banco. 
	}
	
}