package br.com.sglabcon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class EnsaioPaver extends GenericDomain{

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFabricacao;
	
	@Column(nullable = false, length = 30)
	private String lote;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private ClasseMaquina classeMaquina;
	
	@Column(nullable = false)
	private Integer qtdAmostras;
	
	@Column(length = 800)
	private String observacoes;
	
	@Column(length = 25 , nullable = false)
	private String tipoPeca;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private DimensaoNominalPV dimensaoNominal;
	
	@Column(length = 40 , nullable = false)
	private Double fatorCorrecao;
	
	@Column(nullable = false, scale = 2)
	private Double resistenciaCaracteristica;
	
	@Column(nullable = false)
	private Short idade;
	
	@Column(nullable = false, scale = 2)
	private Double areaCarregamento;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	//getters e setters

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public ClasseMaquina getClasseMaquina() {
		return classeMaquina;
	}
	
	public void setClasseMaquina(ClasseMaquina classeMaquina) {
		this.classeMaquina = classeMaquina;
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

	public String getTipoPeca() {
		return tipoPeca;
	}

	public void setTipoPeca(String tipoPeca) {
		this.tipoPeca = tipoPeca;
	}

	public DimensaoNominalPV getDimensaoNominal() {
		return dimensaoNominal;
	}

	public void setDimensaoNominal(DimensaoNominalPV dimensaoNominal) {
		this.dimensaoNominal = dimensaoNominal;
	}

	public Double getFatorCorrecao() {
		return fatorCorrecao;
	}

	public void setFatorCorrecao(Double fatorCorrecao) {
		this.fatorCorrecao = fatorCorrecao;
	}

	public Double getResistenciaCaracteristica() {
		return resistenciaCaracteristica;
	}

	public void setResistenciaCaracteristica(Double resistenciaCaracteristica) {
		this.resistenciaCaracteristica = resistenciaCaracteristica;
	}
	
	public Short getIdade() {
		return idade;
	}

	public void setIdade(Short idade) {
		this.idade = idade;
	}
	
	public Double getAreaCarregamento() {
		return areaCarregamento;
	}
	
	public void setAreaCarregamento(Double areaCarregamento) {
		this.areaCarregamento = areaCarregamento;
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
