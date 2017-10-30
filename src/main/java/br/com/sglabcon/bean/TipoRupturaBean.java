package br.com.sglabcon.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.TipoRupturaDAO;
import br.com.sglabcon.domain.TipoRuptura;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean
public class TipoRupturaBean extends GenericBean{
	
	private TipoRuptura tipoRuptura;
	private List<TipoRuptura> tiposRuptura;
	
	@PostConstruct
	public void listar() {
		try {
			TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
			tiposRuptura = tipoRupturaDAO.listar("tipoRuptura");
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar o banco");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		tipoRuptura = new TipoRuptura();
	}
	
	public void salvar() {
		try {
			TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
			tipoRupturaDAO.merge(tipoRuptura);
			
			Messages.addGlobalInfo("Tipo de Ruptura cadastrado com sucesso");
			
			novo();
			listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao salvar registro");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			tipoRuptura = (TipoRuptura) evento.getComponent().getAttributes().get("tipoRSelecionada");
			TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
			tipoRupturaDAO.excluir(tipoRuptura);
			
			Messages.addGlobalInfo("Tipo de Ruptura excluído com sucesso");

			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir registro");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		tipoRuptura = (TipoRuptura) evento.getComponent().getAttributes().get("tipoRSelecionada");
	}
	
	//setters e getters
	
	public TipoRuptura getTipoRuptura() {
		return tipoRuptura;
	}
	
	public void setTipoRuptura(TipoRuptura tipoRuptura) {
		this.tipoRuptura = tipoRuptura;
	}
	
	public List<TipoRuptura> getTiposRuptura() {
		return tiposRuptura;
	}
	
	public void setTiposRuptura(List<TipoRuptura> tiposRuptura) {
		this.tiposRuptura = tiposRuptura;
	}

}
