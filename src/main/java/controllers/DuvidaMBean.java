package controllers;


import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dominio.Disciplina;
import dominio.Duvida;
import dominio.Pessoa;
import service.DisciplinaService;
import service.DuvidaService;
@ManagedBean
@SessionScoped
public class DuvidaMBean extends GenericMBean<Duvida> {

	private int idDiciplina;
	
	private List<Duvida> listDuvidas;
	
	@EJB
	private DisciplinaService diciplinaService;
	
	@EJB
	private DuvidaService duvidaService;
	
	private boolean curtir;
	
	
	public DuvidaMBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String list(){
		listDuvidas = null;
		return "/pages/index.jsf";
	}
	
	@Override
	public String getDir() {
		// TODO Auto-generated method stub
		return "/pages/duvida";
	}
	
	public String novo(){
		obj = new Duvida();
		return getDir()+"/form.jsf";
	}
	
	@Override
	public String salvar(){
		Disciplina dis = diciplinaService.finByID(idDiciplina, Disciplina.class);
		obj.setAtivo(true);
		obj.setDisciplina(dis);
		obj.setDataCadastro(new Date());
		obj.setPessoa(getUsuarioLogado().getPessoa());
		super.salvar();
		listDuvidas = null;
		return "/pages/index.jsf";
	}
	
	public String detalhesDuvida(Duvida duvida){
		obj = duvida;
		return "/pages/duvida/view.jsf";
	}
	
	public String listDuvidasPorPessoa(Pessoa pessoa){
		listDuvidas = duvidaService.listDuvidasPorPessoa(pessoa);
		return "/pages/duvida/list.jsf";
	}
	
	public String listDuvidasPorDisciplina(Disciplina disciplina){
		listDuvidas = duvidaService.listDuvidasPorDisciplina(disciplina);
		return "/pages/duvida/list.jsf";
	}
	
	public String voltar(){
		return "/pages/index.jsf";
	}



	public int getIdDiciplina() {
		return idDiciplina;
	}


	public void setIdDiciplina(int idDiciplina) {
		this.idDiciplina = idDiciplina;
	}


	public List<Duvida> getListDuvidas() {
		if(listDuvidas == null){
			listDuvidas = duvidaService.listaPorData();
		}
		return listDuvidas;
	}


	public void setListDuvidas(List<Duvida> listDuvidas) {
		this.listDuvidas = listDuvidas;
	}
	
	public void curtida(){
		curtir = true;
	}

	/**
	 * @return the curtir
	 */
	public boolean isCurtir() {
		return curtir;
	}

	/**
	 * @param curtir the curtir to set
	 */
	public void setCurtir(boolean curtir) {
		this.curtir = curtir;
	}
	
}
