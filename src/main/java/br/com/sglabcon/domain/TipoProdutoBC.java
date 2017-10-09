package br.com.sglabcon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class TipoProdutoBC extends GenericDomain {
	
	@Column(length = 25, nullable = false, unique = true)
	private String descricao;
	
	@Column(length = 25, nullable = false)
	private String dimensaoNominal;
	
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDimensaoNominal() {
		return dimensaoNominal;
	}
	
	public void setDimensaoNominal(String dimensaoNominal) {
		this.dimensaoNominal = dimensaoNominal;
	}

}
