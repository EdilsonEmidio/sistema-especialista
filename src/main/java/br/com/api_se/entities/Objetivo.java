package br.com.api_se.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "objetivos")
public class Objetivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private Date dataObjetivo;
	private int progresso;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public Objetivo() {
		
	}
	public Objetivo(long id, String nome, Date dataObjetivo, int progresso, Usuario usuario) {
		this.id = id;
		this.nome = nome;
		this.dataObjetivo = dataObjetivo;
		this.progresso = progresso;
		this.usuario = usuario;
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
	public Date getDataObjetivo() {
		return dataObjetivo;
	}
	public void setDataObjetivo(Date dataObjetivo) {
		this.dataObjetivo = dataObjetivo;
	}
	public int getProgresso() {
		return progresso;
	}
	public void setProgresso(int progresso) {
		this.progresso = progresso;
	}
	public Usuario getUsuarioId() {
		return usuario;
	}
	public void setUsuarioId(Usuario usuario) {
		this.usuario = usuario;
	}
}
