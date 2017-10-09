package br.com.sglabcon.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.DimensaoNominalPVDAO;
import br.com.sglabcon.domain.DimensaoNominalPV;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class DimensaoNominalPVBean {

	private DimensaoNominalPV dimensaoNominalPV;
	private List<DimensaoNominalPV> dimensoes;
	
	@PostConstruct
	public void listar(){
		try {
			DimensaoNominalPVDAO dimensaoNominalPVDAO = new DimensaoNominalPVDAO();
			dimensoes = dimensaoNominalPVDAO.listar();			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao fazer consulta no banco");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		dimensaoNominalPV = new DimensaoNominalPV();
	}
	
	public void salvar() {
		try {
			DimensaoNominalPVDAO dimensaoNominalPVDAO = new DimensaoNominalPVDAO();
			dimensaoNominalPVDAO.merge(dimensaoNominalPV);
			
			Messages.addGlobalInfo("Registro " + dimensaoNominalPV.getDimensaoNominal() + " foi salvo com sucesso");
			novo();
			listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao salvar");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			dimensaoNominalPV = (DimensaoNominalPV) evento.getComponent().getAttributes().get("dimensaoPVSelecionada");
			DimensaoNominalPVDAO dimensaoNominalPVDAO = new DimensaoNominalPVDAO();
			
			dimensaoNominalPVDAO.excluir(dimensaoNominalPV);
			
			Messages.addGlobalInfo("Registro " + dimensaoNominalPV.getDimensaoNominal() + " foi excluído com sucesso");
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		dimensaoNominalPV = (DimensaoNominalPV) evento.getComponent().getAttributes().get("dimensaoPVSelecionada");
	}
	
	
	//getters e setters
	public DimensaoNominalPV getDimensaoNominalPV() {
		return dimensaoNominalPV;
	}
	
	public void setDimensaoNominalPV(DimensaoNominalPV dimensaoNominalPV) {
		this.dimensaoNominalPV = dimensaoNominalPV;
	}
	
	public List<DimensaoNominalPV> getDimensoes() {
		return dimensoes;
	}
	
	public void setDimensoes(List<DimensaoNominalPV> dimensoes) {
		this.dimensoes = dimensoes;
	}
	
}
