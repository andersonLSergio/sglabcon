package br.com.sglabcon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class TipoRuptura extends GenericDomain {

	@Column(nullable = false, length = 30)
	private String tipoRuptura;
	
	@Column(nullable = false, length = 150)
	private String descricao;
	
	@Column(nullable = false, length = 300)
	private String imgUrl;
	
	@Transient
	private String caminhoOrigem;
	
	public String getTipoRuptura() {
		return tipoRuptura;
	}
	
	public void setTipoRuptura(String tipoRuptura) {
		this.tipoRuptura = tipoRuptura;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}
	
	public void setCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}
	
}
