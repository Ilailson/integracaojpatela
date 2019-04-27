package integrandojpatela.com.aula.jpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Ilailson Rocha
 *
 */
public class JPAUtil {

	private static EntityManagerFactory factory = null;

	static {
		if (factory == null) {
			factory = Persistence
					.createEntityManagerFactory("integratela");
		}
		
		/*static (Garanti que a instalancia seja criada apenas uma vez (Padrão singleton))
		 * 
		 * se a factory não existir (null) então cria a fabrica e atualiza o projeto. 
		 * */
	}

	/**
	 * Responsável por realizar as transações do banco de dados através do EntityManager. 
	 * Crud e pesquisa. 
	 * 
	 */
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
		
		/*Metodo que será chamado na aplicação para fazer a criação, atualização, delete etc.... */
	}
	
	
	
	/**Responsável por identificar o id(primary key) do banco de dados
	 * 
	 * Retoranará um objeto (Integer, String, Double, Long etc.....) retoranará o ID do 
	 * banco de dados
	 */
	public static Object getPrimaryKey(Object entity){
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

}
