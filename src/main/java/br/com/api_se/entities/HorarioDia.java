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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "horario_dia")
public class HorarioDia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String hora;
	private String atividade;
	private int avaliacao;
	private float fitness; //novo
	private int problemasTotal;//novo
	@ManyToOne
	@JoinColumn(name = "rotina_dia")
	private RotinaDia rotinaDia;
	@ManyToMany
	@JoinTable(
			name = "horario_dia_problemas",
			joinColumns = @JoinColumn(name = "horario_dia_id"),
			inverseJoinColumns = @JoinColumn(name = "problema_id"))
	private List<Problema> problemas = new LinkedList<>();

	public HorarioDia() {
	}
	
	public HorarioDia(long id, String hora, String atividade, int avaliacao, RotinaDia rotinaDia) {
		this.id = id;
		this.hora = hora;
		this.atividade = atividade;
		this.avaliacao = avaliacao;
		this.rotinaDia = rotinaDia;
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
	public int getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	public RotinaDia getRotinaDia() {
		return rotinaDia;
	}
	public void setRotinaDia(RotinaDia rotinaDia) {
		this.rotinaDia = rotinaDia;
	}
	public List<Problema> getProblemas() {
		return problemas;
	}
	public void setProblemas(List<Problema> problemas) {
		this.problemas = problemas;
	}
	public void addProblema(Problema problema) {
		this.problemas.add(problema);
	}
	public void removeProblema(Problema problema) {
		this.problemas.remove(problema);
	}

	public float getFitness() {
		return fitness;
	}

	public void setFitness(float fitness) {
		this.fitness = fitness;
	}

	public int getProblemasTotal() {
		return problemasTotal;
	}

	public void setProblemasTotal(int problemasTotal) {
		this.problemasTotal = problemasTotal;
	}
	
}
