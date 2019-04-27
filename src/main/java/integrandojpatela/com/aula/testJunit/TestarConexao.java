package integrandojpatela.com.aula.testJunit;

import javax.persistence.Persistence;

import org.junit.Test;

public class TestarConexao {

	@Test
	public void testandoConexao() {
		Persistence.createEntityManagerFactory("integratela");
	}
	
}
