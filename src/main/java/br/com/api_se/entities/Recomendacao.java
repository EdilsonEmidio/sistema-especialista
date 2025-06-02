package br.com.api_se.entities;

import java.sql.Date;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recomendacoes")
public class Recomendacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfil;
	@ManyToOne
	@JoinColumn(name = "rotina_esperada_id")
	private RotinaEsperada rotinaEsperada;
	@OneToOne
	@JoinColumn(name = "metodo_estudo_id")
	private MetodoEstudo metodoEstudo;
	private int fitness; //menos é melhor pq é menos erros
	private int totalDias;
	@CurrentTimestamp
	private Date dataCriada;
	
	public Recomendacao() {
		// TODO Auto-generated constructor stub
	}
	
	public Recomendacao(long id, Perfil perfil, int fitness,MetodoEstudo metodoEstudo, int totalDias, Date dataCriada) {
		this.id = id;
		this.perfil = perfil;
		this.fitness = fitness;
		this.metodoEstudo = metodoEstudo;
		this.totalDias = totalDias;
		this.dataCriada = dataCriada;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public MetodoEstudo getMetodoEstudo() {
		return metodoEstudo;
	}

	public void setMetodoEstudo(MetodoEstudo metodoEstudo) {
		this.metodoEstudo = metodoEstudo;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public int getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}

	public RotinaEsperada getRotinaEsperada() {
		return rotinaEsperada;
	}

	public void setRotinaEsperada(RotinaEsperada rotinaEsperada) {
		this.rotinaEsperada = rotinaEsperada;
	}

	public Date getDataCriada() {
		return dataCriada;
	}

	public void setDataCriada(Date dataCriada) {
		this.dataCriada = dataCriada;
	}
	
	
}
