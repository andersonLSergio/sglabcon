package br.com.sglabcon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SuppressWarnings("serial")
public class EnsaioCilindrico extends GenericDomain {

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAddAgua;
	
	@Column(nullable = false, length = 30)
	private String lote;
	
	@Column(length = 25 , nullable = false)
	private String tipoMaquina;
	
	@Column(nullable = false)
	private Integer qtdAmostras;
	
	@Column(length = 800)
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private DimensaoBasicaCilindrico dimensaoBasica;
	
	@Column(length = 40 , nullable = false)
	private String capeamento;
	
	@Column(nullable = false, scale = 2)
	private Double resistenciaProjeto;
	
	@Column(nullable = false)
	private Short idade;

	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	//getters e setters

	public Date getDataAddAgua() {
		return dataAddAgua;
	}

	public void setDataAddAgua(Date dataAddAgua) {
		this.dataAddAgua = dataAddAgua;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getTipoMaquina() {
		return tipoMaquina;
	}

	public void setTipoMaquina(String tipoMaquina) {
		this.tipoMaquina = tipoMaquina;
	}

	public Integer getQtdAmostras() {
		return qtdAmostras;
	}

	public void setQtdAmostras(Integer qtdAmostras) {
		this.qtdAmostras = qtdAmostras;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public DimensaoBasicaCilindrico getDimensaoBasica() {
		return dimensaoBasica;
	}

	public void setDimensaoBasica(DimensaoBasicaCilindrico dimensaoBasica) {
		this.dimensaoBasica = dimensaoBasica;
	}

	public String getCapeamento() {
		return capeamento;
	}

	public void setCapeamento(String capeamento) {
		this.capeamento = capeamento;
	}

	public Double getResistenciaProjeto() {
		return resistenciaProjeto;
	}

	public void setResistenciaProjeto(Double resistenciaProjeto) {
		this.resistenciaProjeto = resistenciaProjeto;
	}
	
	public Short getIdade() {
		return idade;
	}

	public void setIdade(Short idade) {
		this.idade = idade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
