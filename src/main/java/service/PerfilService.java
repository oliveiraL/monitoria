package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONObject;
import dao.PerfilDao;
import dominio.Disciplina;
import dominio.Papel;
import dominio.Perfil;
import dominio.Pessoa;

import exception.NegocioException;

@Stateless
public class PerfilService extends GenericService<Perfil>{
	
	@Inject
	private ApiService apiService;
	
	@Inject
	private PerfilDao perfilDao;
	
	@Inject
	private DisciplinaService disciplinaService;
	
	public Perfil findByPessoa(Pessoa pessoa){
		return perfilDao.findByPessoa(pessoa.getId());
	}
	
	public void cadastrarPerfil(String json, Pessoa pessoa) throws NegocioException{
		JSONObject jsonObject = new JSONObject(json);
		jsonObject = jsonObject.getJSONObject("listaVinculosUsuario");
		JSONArray discentes = jsonObject.getJSONArray("discentes");
		cadastroAluno(discentes, pessoa);
	}
	
	private void cadastroAluno(JSONArray dados, Pessoa pessoa) throws NegocioException{
		for(int i = 0; i < dados.length(); i++){
			JSONObject jsonObject = dados.getJSONObject(i);	
			Papel papel = new Papel();
			papel.setId(Papel.ROLE_ALUNO);
			Perfil perfil = new Perfil();
			perfil.setPessoa(pessoa);
			perfil.setAtivo(true);
			perfil.setId_sigaa(jsonObject.getInt("id"));
			perfil.setCurso(jsonObject.getString("curso").replace("Curso: ", ""));
			perfil.setMatricula(jsonObject.getString("matricula"));
			perfil.setPapel(papel);
			String json = apiService.turmasDiscente(perfil.getId_sigaa().toString());
			List<Disciplina> dis = disciplinaService.getDisciplinas(json);
			perfil.setDisciplinas(dis);
			salvar(perfil);
		}
	}
	
}
