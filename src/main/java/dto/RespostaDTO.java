package dto;

public class RespostaDTO {
	private String nomeUsuario;
    private int fotoUsuario;
    private String data;
    private String descricao;
    private boolean melhorResposta;
    
    
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public int getFotoUsuario() {
		return fotoUsuario;
	}
	public void setFotoUsuario(int fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isMelhorResposta() {
		return melhorResposta;
	}
	public void setMelhorResposta(boolean melhorResposta) {
		this.melhorResposta = melhorResposta;
	}
    
    
}
