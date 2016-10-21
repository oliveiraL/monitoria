package dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil {
	
	@Id
	@Column(name="id_perfil", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private boolean ativo;
	
	@Column
	private String curso;
	
	@OneToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	@OneToOne
	@JoinColumn(name="papel_id")
	private Papel papel;
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="perfil_disciplina", joinColumns={@JoinColumn(name="perfil_id")}, 
    inverseJoinColumns={@JoinColumn(name="disciplina_id")})
    private List<Diciplina> diciplinas;
	
	public Perfil() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public List<Diciplina> getDiciplinas() {
		return diciplinas;
	}

	public void setDiciplinas(List<Diciplina> diciplinas) {
		this.diciplinas = diciplinas;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	
	
}
