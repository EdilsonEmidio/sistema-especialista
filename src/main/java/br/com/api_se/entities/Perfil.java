package br.com.api_se.entities;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfis")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToMany
	@JoinTable(name = "perfil_problemas",
			joinColumns = @JoinColumn(name = "perfil_id"),
			inverseJoinColumns = @JoinColumn(name = "problema_id"))
	private List<Problema> problemas =  new LinkedList<>();
	
	public Perfil() {
		
	}
	public Perfil(long id, Usuario usuario) {
		this.id = id;
		this.usuario = usuario;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Problema> getProblemas() {
		return problemas;
	}
	public void setProblemas(List<Problema> problemas) {
		this.problemas = problemas;
	}
	public void addProblema(Problema caracteristica) {
		this.problemas.add(caracteristica);
	}
	public void removeProblema(Problema caracteristica) {
		this.problemas.remove(caracteristica);
	}
	
}
