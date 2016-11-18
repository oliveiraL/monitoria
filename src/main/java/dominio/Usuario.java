package dominio;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="usuarios")
public class Usuario {
	/**
	 * Atributo identificador da classe Usuario 
	 */
	@Id
	@Column(name="id_usuario", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="id_usuario_sigaa", unique=true)
	private Integer idSigaa;
	
	/**
	 * Atributo que guarda o login do Usuario
	 */
	@Column(unique=true)
	private String login;
	
	/**
	 * Atributo que guarda a senha do Usuario
	 */
	@Column
	private String senha;
	
	@Column
	private Boolean ativo;
	
	@OneToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	@Transient
	private String accessToken;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getIdSigaa() {
		return idSigaa;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setIdSigaa(Integer idSigaa) {
		this.idSigaa = idSigaa;
	}
	
	
	
}
