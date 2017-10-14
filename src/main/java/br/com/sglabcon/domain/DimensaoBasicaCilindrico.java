package br.com.sglabcon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class DimensaoBasicaCilindrico extends GenericDomain{
	
	@Column(nullable = false, scale = 2)
	private Double dimensaoBasica;
	
	public Double getDimensaoBasica() {
		return dimensaoBasica;
	}
	
	public void setDimensaoBasica(Double dimensaoBasica) {
		this.dimensaoBasica = dimensaoBasica;
	}
}
