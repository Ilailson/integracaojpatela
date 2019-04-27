package integrandojpatela.com.aula.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import integrandojpatela.com.aula.dao.DaoGeneric;
import integrandojpatela.com.aula.model.Pessoa;

@ViewScoped
@ManagedBean(name="pessoaBean")
public class PessoaBean {

	Pessoa pessoa = new Pessoa();
	
	DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();//informando a classe que será realizada o crud de forma genérica.
	
	
	
	//********************SET E GET******************************
	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	//**************************OPERACIONAL***************************************


	public String salvar() {
		daoGeneric.salvar(pessoa);
		pessoa = new Pessoa();
		return ""; //vazio ou null retorna para a mesma página.
	}
	
}

