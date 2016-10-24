package controllers;


import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dominio.Disciplina;
import dominio.Duvida;
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
	
	
	public DuvidaMBean() {
		// TODO Auto-generated constructor stub
		obj = new Duvida();
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
	
	
	
}
