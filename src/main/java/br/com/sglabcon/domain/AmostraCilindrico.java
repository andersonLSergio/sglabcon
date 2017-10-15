package br.com.sglabcon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class AmostraCilindrico extends GenericDomain {

	@Column(length = 30)
	private String identificacao;

	@Column(nullable = false, scale = 2)
	private Double forcaAplicada;

	@Column(nullable = false, scale = 2)
	private Double conversaoFA;

	@Column(nullable = false)
	private Boolean status;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(nullable = false)
	private EnsaioCilindrico ensaioCilindrico;
	
	@Column(nullable = false, scale = 2)
	private Double relacaoHD;
	
	@Column(nullable = false, scale = 2)
	private Double alturaReal;
	
	@Column(nullable = false, scale = 2)
	private Double diametroReal;
	
	@ManyToOne
	private TipoRuptura tipoRuptura;
	
	@Column(nullable = false, scale = 2)
	private Double fatorCorrecao;


	// Método destinado a mostrar status formatado na tela
	@Transient
	public String getStatusFormatado() {
		String statusFormatado = "Reprovado";

		if (status == null) {
			statusFormatado = "Aguardando...";
		} else if (status) {
			statusFormatado = "Aprovado";
		}

		return statusFormatado;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public Double getForcaAplicada() {
		return forcaAplicada;
	}

	public void setForcaAplicada(Double forcaAplicada) {
		this.forcaAplicada = forcaAplicada;
	}

	public Double getConversaoFA() {
		return conversaoFA;
	}

	public void setConversaoFA(Double conversaoFA) {
		this.conversaoFA = conversaoFA;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EnsaioCilindrico getEnsaioCilindrico() {
		return ensaioCilindrico;
	}

	public void setEnsaioCilindrico(EnsaioCilindrico ensaioCilindrico) {
		this.ensaioCilindrico = ensaioCilindrico;
	}
	
	public Double getRelacaoHD() {
		return relacaoHD;
	}
	
	public void setRelacaoHD(Double relacaoHD) {
		this.relacaoHD = relacaoHD;
	}
	
	public Double getAlturaReal() {
		return alturaReal;
	}
	
	public void setAlturaReal(Double alturaReal) {
		this.alturaReal = alturaReal;
	}
	
	public Double getDiametroReal() {
		return diametroReal;
	}
	
	public void setDiametroReal(Double diametroReal) {
		this.diametroReal = diametroReal;
	}
	
	public TipoRuptura getTipoRuptura() {
		return tipoRuptura;
	}
	
	public void setTipoRuptura(TipoRuptura tipoRuptura) {
		this.tipoRuptura = tipoRuptura;
	}
	
	public Double getFatorCorrecao() {
		return fatorCorrecao;
	}
	
	public void setFatorCorrecao(Double fatorCorrecao) {
		this.fatorCorrecao = fatorCorrecao;
	}

}
