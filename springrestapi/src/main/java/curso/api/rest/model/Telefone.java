package curso.api.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Entity /*Para se tornar uma classe persistente que representa uma tabela no banco de dados */
public class Telefone { /*Regra de negocio é muitos telefone para um*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long id; /*Primary key*/
	
	private String numero;
	
	@ForeignKey(name = "usuario_id")
	@ManyToOne /*Muitos para um e depois temos que fazer o inversor na classe usuario*/
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}