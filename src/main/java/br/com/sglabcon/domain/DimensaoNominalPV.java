package br.com.sglabcon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class DimensaoNominalPV extends GenericDomain {

	@Column(nullable = false, length = 25)
	private String dimensaoNominal;
	
	public String getDimensaoNominal() {
		return dimensaoNominal;
	}
	
	public void setDimensaoNominal(String dimensaoNominal) {
		this.dimensaoNominal = dimensaoNominal;
	}
	
}
