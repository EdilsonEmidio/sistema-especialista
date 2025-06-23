package br.com.api_se.algoritmos;

import java.util.List;

public class Dia {
	
	private String dia;
	private int avaliacaoTotal;
	private List<Hora> horas;
	
	public Dia() {
		// TODO Auto-generated constructor stub
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public int getAvaliacaoTotal() {
		return avaliacaoTotal;
	}
	public void setAvaliacaoTotal(int avaliacaoTotal) {
		this.avaliacaoTotal = avaliacaoTotal;
	}
	public List<Hora> getHoras() {
		return horas;
	}
	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}

	
	
}
