package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="respostas")
public class Resposta {
	@Id
	@Column(name="id_resposta", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	

	@Column(columnDefinition="VARCHAR(2000)", nullable=true)
	private String descricao;

	@Column
	private boolean ativo;

	@OneToOne
	@JoinColumn(name="duvida_id")
	private Duvida duvida;
			
	@OneToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	public Resposta() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Duvida getDuvida() {
		return duvida;
	}

	public void setDuvida(Duvida duvida) {
		this.duvida = duvida;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
	
}
