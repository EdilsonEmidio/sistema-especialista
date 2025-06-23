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
@Table(name = "rotina_esperada")
public class RotinaEsperada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	
	
	public RotinaEsperada() {
	}

	public RotinaEsperada(long id, String nome, Usuario usuario, LocalDate dataInicial, LocalDate dataFinal, String descricao) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
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

	public Usuario getUsuarioId() {
		return usuario;
	}
	public void setUsuarioId(Usuario usuario) {
		this.usuario = usuario;
	}
	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
	
}
