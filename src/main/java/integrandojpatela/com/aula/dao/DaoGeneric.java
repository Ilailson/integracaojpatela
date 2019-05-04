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

	/**
	 * Responsável por salvar sem nenhum retorno. 
	 * @param entidade
	 */
	public void salvar(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();//pegando o entityManager que está na  JPAUTIL
		EntityTransaction entityTransaction = entityManager.getTransaction();//transação recebendo o entitymanager
		entityTransaction.begin(); //iniciando a transação. 
		
		entityManager.persist(entidade); //sicronizando para salvar sem retorna para a tela. 
		
		entityTransaction.commit(); //salvando no banco. 
		entityManager.close();//fechando a conexão com o banco. 
	}
	
	/**
	 * Salva, atualiza e ainda pode retornar o objeto. Ou utilizar o método que salva 
	 * sem retorna  ou utilizar o merge que retorna  o objeto.<p>
	 * @param entidade retorna a entidade com os objetos. 
	 * 
	 * Quando utilizar o merge tem que criar um botão NOVO para poder instanciar um novo objeto. 
	 * 
	 */
	public E merge(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();//pegando o entityManager que está na  JPAUTIL
		EntityTransaction entityTransaction = entityManager.getTransaction();//transação recebendo o entitymanager
		entityTransaction.begin(); //iniciando a transação. 
		
		E retorno = entityManager.merge(entidade); //sicronizando para salvar/atualizar retorna na tela os dados do objeto. 
		
		
		entityTransaction.commit(); //salvando no banco. 
		entityManager.close();//fechando a conexão com o banco. 
		
		return retorno; //retorna o objeto com todas as informações. 
	}
	
	
	public void deletePorId(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();//pegando o entityManager que está na  JPAUTIL
		EntityTransaction entityTransaction = entityManager.getTransaction();//transação recebendo o entitymanager
		entityTransaction.begin(); //iniciando a transação. 
		
		Object id = JPAUtil.getPrimaryKey(entidade);
		
		//entidade.getClass(): pega o  nome da classe de forma genérica		
		entityManager.createQuery("delete from "+entidade.getClass().getCanonicalName()+ " where id = "+id).executeUpdate();  
		
		entityTransaction.commit(); //salvando no banco. 
		entityManager.close();//fechando a conexão com o banco. 
	}
	
}