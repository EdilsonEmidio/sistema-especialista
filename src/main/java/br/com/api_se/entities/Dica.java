package br.com.api_se.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dicas")
public class Dica {

	@Id
	@GeneratedValue
	private long id;
	private String dica;
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "problema_id")
	private Problema problema;
	
	public Dica() {
	}
	
	public Dica(long id, String dica, String descricao, Problema problema) {
		this.id = id;
		this.dica = dica;
		this.descricao = descricao;
		this.problema = problema;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Problema getProblema() {
		return problema;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}
	
}
