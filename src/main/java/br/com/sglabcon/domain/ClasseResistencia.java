package br.com.sglabcon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class ClasseResistencia extends GenericDomain{
	
	@Column(length = 30, nullable = false)
	private String nome;
	
	@Column(nullable = false, scale = 2)
	private Double resistenciaCaracteristica;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getResistenciaCaracteristica() {
		return resistenciaCaracteristica;
	}

	public void setResistenciaCaracteristica(Double resistenciaCaracteristica) {
		this.resistenciaCaracteristica = resistenciaCaracteristica;
	}

	
}
