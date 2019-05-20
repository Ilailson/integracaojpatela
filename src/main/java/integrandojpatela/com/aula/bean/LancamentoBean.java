package integrandojpatela.com.aula.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import integrandojpatela.com.aula.dao.DaoGeneric;
import integrandojpatela.com.aula.model.Lancamento;
import integrandojpatela.com.aula.model.Pessoa;
import integrandojpatela.com.aula.repository.IDaoLancamento;
import integrandojpatela.com.aula.repository.IDaoLancamentoImp;
/**
 * Responsável pelas transações da tela de Lançamentos.
 * @author Ilailson Rocha
 *
 *@see DaoGeneric
 *@see Pessoa
 *@see IDaoLancamento
 *@see IDaoLancamentoImp
 */
@ViewScoped
@ManagedBean
public class LancamentoBean {

	private Lancamento lancamento=new Lancamento();
	private DaoGeneric<Lancamento> daoGeneric = new DaoGeneric<Lancamento>();
	private List<Lancamento> todosLancamentos = new ArrayList<Lancamento>();//exibe todos os lançamentos. 
	private IDaoLancamento daoLancamento = new IDaoLancamentoImp();//interface recebe a implementação
	
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public DaoGeneric<Lancamento> getDaoGeneric() {
		return daoGeneric;
	}
	public void setDaoGeneric(DaoGeneric<Lancamento> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	public List<Lancamento> getTodosLancamentos() {
		return todosLancamentos;
	}
	public void setTodosLancamentos(List<Lancamento> todosLancamentos) {
		this.todosLancamentos = todosLancamentos;
	}
	
	/*
	 * ==============================OPERACIONAL====================================
	 */
	
	/**
	 * Responsával por salvar o lançamento do usuário logado. 
	 * @return
	 */
	public String salvar() {
		//recuperando o usuário que está logado na sessão usuarioLogado do filterAutenticacao. essas tres linhas
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		
		lancamento.setUsuario(pessoaUser);//atribuindo a pessoa através da chave estrangeira. 
		
		lancamento = daoGeneric.merge(lancamento);	
		
		carregarLancamentos();
		
		return "";
	}
	
	/**
	 * Responsável por exibir os lançamentos de cada usuário que esteja logado no sistema. 
	 */
	@PostConstruct
	private void carregarLancamentos() {
		//recuperando o usuário que está logado na sessão usuarioLogado do filterAutenticacao. essas tres linhas
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
				
		todosLancamentos = daoLancamento.consultar(pessoaUser.getId());
		
		
	}
	
}

