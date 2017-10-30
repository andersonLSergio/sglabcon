package br.com.sglabcon.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.DimensaoBasicaCilindricoDAO;
import br.com.sglabcon.domain.DimensaoBasicaCilindrico;

@SuppressWarnings("deprecation")
@ManagedBean(name = "dBasicaCilindrico")
@ViewScoped
public class DimensaoBasicaCilindricoBean {

	private DimensaoBasicaCilindrico dimensaoBasica;
	List<DimensaoBasicaCilindrico> dimensoes;

	@PostConstruct
	public void listar() {
		try {
			DimensaoBasicaCilindricoDAO dimensaoBasicaCilindricoDAO = new DimensaoBasicaCilindricoDAO();
			dimensoes = dimensaoBasicaCilindricoDAO.listar("dimensaoBasica");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar banco.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		dimensaoBasica = new DimensaoBasicaCilindrico();
	}

	public void salvar() {
		try {
			DimensaoBasicaCilindricoDAO dimensaoBasicaCilindricoDAO = new DimensaoBasicaCilindricoDAO();
			dimensaoBasicaCilindricoDAO.merge(dimensaoBasica);
			
			Messages.addGlobalInfo("Registro salvo com sucesso");
			
			novo();
			listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao salvar.");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			dimensaoBasica = (DimensaoBasicaCilindrico) evento.getComponent().getAttributes().get("dimensaoSelecionada");
			
			DimensaoBasicaCilindricoDAO dimensaoBasicaCilindricoDAO = new DimensaoBasicaCilindricoDAO();
			dimensaoBasicaCilindricoDAO.excluir(dimensaoBasica);
			
			Messages.addGlobalInfo("Registro excluído com sucesso");
			
			listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao excluir.");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		dimensaoBasica = (DimensaoBasicaCilindrico) evento.getComponent().getAttributes().get("dimensaoSelecionada");
	}

	// getters e setters

	public DimensaoBasicaCilindrico getDimensaoBasica() {
		return dimensaoBasica;
	}

	public void setDimensaoBasica(DimensaoBasicaCilindrico dimensaoBasica) {
		this.dimensaoBasica = dimensaoBasica;
	}

	public List<DimensaoBasicaCilindrico> getDimensoes() {
		return dimensoes;
	}

	public void setDimensoes(List<DimensaoBasicaCilindrico> dimensoes) {
		this.dimensoes = dimensoes;
	}
}
