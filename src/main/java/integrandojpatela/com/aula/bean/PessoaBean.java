package integrandojpatela.com.aula.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import integrandojpatela.com.aula.dao.DaoGeneric;
import integrandojpatela.com.aula.model.Pessoa;

@ViewScoped
@ManagedBean(name="pessoaBean")
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();//informando a classe que será realizada o crud de forma genérica.
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	
	//********************SET E GET******************************
	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	/**
	 * Metodo get é obrigatorio para poder exibir as pessoas da lista. 
	 * 
	 * Tem que ser chamado toda vez que tiver alteração no banco de dados. 
	 * Quando: remove, atualiza ou edita. 
	 */
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}
	
	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
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
		carregarPessoas();
		return ""; //vazio ou null retorna para a mesma página.
	}
	
	
	public String remove() {
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();//chamado um nova pessoa depois que excluir. Limpa os dados da tela.
		carregarPessoas();
		return "";
	}
	
	/**
	 * Responsável por listar as pessoas através do método generico getListEntity
	 * que pode listar qualquer entidade do banco. <p>
	 * PostConstruct: quando  abrir a tela a lista vem carregada. 
	 */
	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.getListEntity(Pessoa.class);
	}
	
}

