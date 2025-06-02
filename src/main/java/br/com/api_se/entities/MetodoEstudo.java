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
import jakarta.persistence.Table;

@Entity
@Table(name = "metodos_estudo")
public class MetodoEstudo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String descricao;
	@ManyToMany
	@JoinTable(name = "metodo_estudo_problemas",
				joinColumns = @JoinColumn(name = "metodo_estudo_id"),
				inverseJoinColumns = @JoinColumn(name = "problema_id"))
	private List<Problema> problemas = new LinkedList<>();
	
	public MetodoEstudo() {
		
	}
	public MetodoEstudo(long id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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