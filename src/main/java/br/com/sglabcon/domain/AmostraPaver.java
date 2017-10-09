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
public class AmostraPaver extends GenericDomain {

	@Column(length = 30)
	private String identificacao;

	@Column(nullable = false, scale = 2)
	private Double forcaAplicada;

	@Column(nullable = false, scale = 2)
	private Double conversaoFA;

	@Column(nullable = false)
	private Boolean status;

	@Column(nullable = false, scale = 2)
	private Double alturaReal;

	@Column(nullable = false, scale = 2)
	private Double larguraReal;

	@Column(nullable = false, scale = 2)
	private Double comprimentoReal;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(nullable = false)
	private EnsaioPaver ensaioPaver;

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

	public Double getAlturaReal() {
		return alturaReal;
	}

	public void setAlturaReal(Double alturaReal) {
		this.alturaReal = alturaReal;
	}

	public Double getLarguraReal() {
		return larguraReal;
	}

	public void setLarguraReal(Double larguraReal) {
		this.larguraReal = larguraReal;
	}

	public Double getComprimentoReal() {
		return comprimentoReal;
	}

	public void setComprimentoReal(Double comprimentoReal) {
		this.comprimentoReal = comprimentoReal;
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

	public EnsaioPaver getEnsaioPaver() {
		return ensaioPaver;
	}

	public void setEnsaioPaver(EnsaioPaver ensaioPaver) {
		this.ensaioPaver = ensaioPaver;
	}

}
