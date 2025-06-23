package br.com.api_se.entities;

import java.time.LocalDate;

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
	private String descricao;
	
	private LocalDate dataObjetivo;
	private int meta;
	private int progresso;
	private boolean concluido;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public Objetivo() {
		
	}
	public Objetivo(long id, String nome, LocalDate dataObjetivo, int progresso, Usuario usuario) {
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
	public LocalDate getDataObjetivo() {
		return dataObjetivo;
	}
	public void setDataObjetivo(LocalDate dataObjetivo) {
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getMeta() {
		return meta;
	}
	public void setMeta(int meta) {
		this.meta = meta;
	}
	public boolean isConcluido() {
		return concluido;
	}
	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
}
