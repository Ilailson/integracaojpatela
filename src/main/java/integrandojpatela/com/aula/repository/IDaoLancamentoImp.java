package integrandojpatela.com.aula.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import integrandojpatela.com.aula.jpaUtil.JPAUtil;
import integrandojpatela.com.aula.model.Lancamento;

/**
 * Responsável por implementar a inteface de lançamento.  
 * @author Ilailson Rocha
 *
 *@see IDaoLancamento
 *@see JPAUtil
 */
public class IDaoLancamentoImp implements IDaoLancamento  {

	@Override
	public List<Lancamento> consultar(Long codUser) {
		List<Lancamento> lista=null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		lista = entityManager.createQuery("from Lancamento where usuario.id = "+codUser).getResultList();
		
		entityTransaction.commit();
		
		entityManager.close();
		
		return lista;
	}

}
