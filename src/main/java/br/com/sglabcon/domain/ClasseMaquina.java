package br.com.sglabcon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class ClasseMaquina extends GenericDomain{
	
	@Column(nullable = false, length = 25)
	private String classe;
	
	public String getClasse() {
		return classe;
	}
	
	public void setClasse(String classe) {
		this.classe = classe;
	}
}
