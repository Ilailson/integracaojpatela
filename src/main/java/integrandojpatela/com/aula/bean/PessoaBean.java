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


	/**
	 * Criar um novo objeto. 
	 */
	public String novo() {
		pessoa = new Pessoa();
		return "";//permanece na mesma página se for vazio ou nulo. 
	}
	
	public String salvar() {
		daoGeneric.salvar(pessoa);
		pessoa = new Pessoa();
		return ""; //vazio ou null retorna para a mesma página.
	}
	
	public String salvarMerge() {
		pessoa = daoGeneric.merge(pessoa); // salva ou atualizar e mostra o que foi salvo. Por este motivo o objeto
											// pessoa está recebendo o DAO. 
		return ""; //vazio ou null retorna para a mesma página.
	}
	
	
	public String remove() {
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();//chamado um nova pessoa depois que excluir. Limpa os dados da tela.
		return "";
	}
	
}

