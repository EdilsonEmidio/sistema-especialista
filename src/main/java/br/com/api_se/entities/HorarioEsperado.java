package br.com.api_se.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "horario_esperado")
public class HorarioEsperado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String hora;
	private String atividade;
	@ManyToOne
	@JoinColumn(name = "rotina_dia")
	private RotinaEsperada rotinaEsperada;
	
	public HorarioEsperado() {
		
	}
	public HorarioEsperado(long id, String hora, String atividade, RotinaEsperada rotinaEsperada) {
		this.id = id;
		this.hora = hora;
		this.atividade = atividade;
		this.rotinaEsperada = rotinaEsperada;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public RotinaEsperada getRotinaEsperada() {
		return rotinaEsperada;
	}
	public void setRotinaEsperada(RotinaEsperada rotinaEsperada) {
		this.rotinaEsperada = rotinaEsperada;
	}
	
	
}
