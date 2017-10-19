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
public class EnsaioBConcreto extends GenericDomain {

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFabricacao;

	@Column(nullable = false, length = 30)
	private String lote;

	@Column(nullable = false, length = 30)
	private String classeMaquina;

	@Column(nullable = false)
	private Integer qtdAmostras;

	@Column(length = 800)
	private String observacoes;

	@Column(nullable = false, length = 60)
	private String TipoProdutoBC;

	@Column(length = 40, nullable = false)
	private Double fatorCorrecao;
	
	@Column(nullable = false)
	private Short idade;

	@ManyToOne
	private Cliente cliente;

	@Column(nullable = false, length = 30)
	private String classeResistencia;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

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

	public String getClasseMaquina() {
		return classeMaquina;
	}
	
	public void setClasseMaquina(String classeMaquina) {
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
	
	public String getTipoProdutoBC() {
		return TipoProdutoBC;
	}
	
	public void setTipoProdutoBC(String tipoProdutoBC) {
		TipoProdutoBC = tipoProdutoBC;
	}

	public Double getFatorCorrecao() {
		return fatorCorrecao;
	}

	public void setFatorCorrecao(Double fatorCorrecao) {
		this.fatorCorrecao = fatorCorrecao;
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

	public String getClasseResistencia() {
		return classeResistencia;
	}

	public void setClasseResistencia(String classeResistencia) {
		this.classeResistencia = classeResistencia;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
