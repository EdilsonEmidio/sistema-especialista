package br.com.api_se.entities;

import java.sql.Date;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@CurrentTimestamp
	private Date dataCriado;
	
	public Date getDataCriado() {
		return dataCriado;
	}
	public void setDataCriado(Date dataCriado) {
		this.dataCriado = dataCriado;
	}
	public Usuario() {
		
	}
	public Usuario(long id, String name, Date dataCriado) {
		this.id = id;
		this.name = name;
		this.dataCriado = dataCriado;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
