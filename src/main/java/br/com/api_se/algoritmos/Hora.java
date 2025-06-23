package br.com.api_se.algoritmos;

public class Hora {

	private String hora;
	private int Avaliacao;
	private int problemas;
	private float fitness;
	
	public Hora() {
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getAvaliacao() {
		return Avaliacao;
	}
	public void setAvaliacao(int avaliacao) {
		Avaliacao = avaliacao;
	}
	public int getProblemas() {
		return problemas;
	}
	public void setProblemas(int problemas) {
		this.problemas = problemas;
	}
	public float getFitness() {
		return fitness;
	}
	public void setFitness(float fitness) {
		this.fitness = fitness;
	}
	
}
