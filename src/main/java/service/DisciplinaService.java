package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.DisciplinaDao;
import dominio.Disciplina;
import exception.NegocioException;
@Stateless
public class DisciplinaService extends CrudService<Disciplina> {
	
	@Inject
	private DisciplinaDao disciplinaDao;
	
	public List<Disciplina> getDisciplinas(String json) throws NegocioException{
		JSONArray ja = new JSONArray(json);
		ArrayList<Disciplina> dis = new ArrayList<>();
		for(int i = 0; i<= ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            
            Disciplina d = disciplinaDao.getCodigo(jo.getString("codigoComponente"));
            if( d != null){
            	dis.add(d);
            	continue;
            }
            d = new Disciplina();
            d.setCodigo(jo.getString("codigoComponente"));
            d.setDescricao(jo.getString("nomeComponente"));
            salvar(d);
            dis.add(d);
		}
		return dis;
	}
}
