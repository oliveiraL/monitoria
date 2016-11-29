package dto;


import java.util.List;


public class DuvidaDTO {
	
	private String titulo;
	
	private String nomeUsuario;
	
	private String descricao;
	
	private String dataCriacao;
	
	private String disciplina;
	
	private int totalCurtida;
	
	private int imagemUsuario;
	
	
	private boolean curtida;
	
	private List<RespostaDTO> respostas;
	
	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public int getTotalCurtida() {
		return totalCurtida;
	}

	public void setTotalCurtida(int totalCurtida) {
		this.totalCurtida = totalCurtida;
	}

	public boolean isCurtida() {
		return curtida;
	}

	public void setCurtida(boolean curtida) {
		this.curtida = curtida;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<RespostaDTO> respostas) {
		this.respostas = respostas;
	}

	public int getImagemUsuario() {
		return imagemUsuario;
	}

	public void setImagemUsuario(int imagemUsuario) {
		this.imagemUsuario = imagemUsuario;
	}
	
	

	
}
