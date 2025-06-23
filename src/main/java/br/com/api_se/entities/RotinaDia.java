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
@Table(name = "rotina_dia")
public class RotinaDia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private LocalDate dataDia;
	private int avaliacao; //0 a 10 como se fosse uma prova
	private int fitness = 0; //novo
	private int problemas; //novo
	
	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public int getProblemas() {
		return problemas;
	}

	public void setProblemas(int problemas) {
		this.problemas = problemas;
	}

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "rotina_esperada_id")
	private RotinaEsperada rotinaEsperada;
	
	public RotinaDia() {
	}

	public RotinaDia(long id, String nome, LocalDate dataDia, Usuario usuario, int avaliacao, RotinaEsperada rotinaEsperada) {
		this.id = id;
		this.nome = nome;
		this.dataDia = dataDia;
		this.avaliacao = avaliacao;
		this.usuario = usuario;
		this.rotinaEsperada = rotinaEsperada;
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

	public LocalDate getDataDia() {
		return dataDia;
	}

	public void setDataDia(LocalDate dataDia) {
		this.dataDia = dataDia;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public RotinaEsperada getRotinaEsperada() {
		return rotinaEsperada;
	}

	public void setRotinaEsperada(RotinaEsperada rotinaEsperada) {
		this.rotinaEsperada = rotinaEsperada;
	}
	
}
