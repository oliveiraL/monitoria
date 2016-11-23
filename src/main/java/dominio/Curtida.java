package dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="curtidas")
public class Curtida {
	
	@Id
	@Column(name="id_curtida", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
	@OneToOne
	@JoinColumn(name="duvida_id")
	private Duvida duvida;
	
	@OneToOne
	@JoinColumn(name="resposta_id")
	private Resposta resposta;
	
	
	@OneToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	@Column
	private Date data;
	
	/**
	 * Construtor padr�o da classe
	 */
	public Curtida(){
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the duvida
	 */
	public Duvida getDuvida() {
		return duvida;
	}

	/**
	 * @param duvida the duvida to set
	 */
	public void setDuvida(Duvida duvida) {
		this.duvida = duvida;
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	
	
	
}
