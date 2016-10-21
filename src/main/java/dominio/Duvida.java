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
@Table(name="duvidas")
public class Duvida {
	@Id
	@Column(name="id_duvida", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String assunto;

	@Column
	private String descricao;

	@Column
	private boolean ativo;

	@OneToOne
	@JoinColumn(name="diciplina_id")
	private Diciplina diciplina;
}
