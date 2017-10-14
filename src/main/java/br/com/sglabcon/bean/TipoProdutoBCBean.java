package br.com.sglabcon.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.TipoProdutoBCDAO;
import br.com.sglabcon.domain.TipoProdutoBC;


@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class TipoProdutoBCBean {

	private TipoProdutoBC tipoProdutoBC;
	private List<TipoProdutoBC> tipos;

	@PostConstruct
	public void listar() {
		try {
			TipoProdutoBCDAO tipoProdutoBCDAO = new TipoProdutoBCDAO();
			tipos = tipoProdutoBCDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao fazer consulta no banco");
			erro.printStackTrace();
		}
	}

	public void novo() {
		tipoProdutoBC = new TipoProdutoBC();
	}

	public void salvar() {
		try {
			TipoProdutoBCDAO tipoProdutoBCDAO = new TipoProdutoBCDAO();
			tipoProdutoBCDAO.merge(tipoProdutoBC);
			Messages.addGlobalInfo("Registro " + tipoProdutoBC.getDescricao() + " foi salvo com sucesso");
			novo();
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao salvar");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			tipoProdutoBC = (TipoProdutoBC) evento.getComponent().getAttributes().get("tipoSelecionado");
			TipoProdutoBCDAO tipoProdutoBCDAO = new TipoProdutoBCDAO();
			tipoProdutoBCDAO.excluir(tipoProdutoBC);

			Messages.addGlobalInfo("Registro " + tipoProdutoBC.getDescricao() + " excluído com sucesso");
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao excluir");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		tipoProdutoBC = (TipoProdutoBC) evento.getComponent().getAttributes().get("tipoSelecionado");
	}
	
	public TipoProdutoBC getTipoProdutoBC() {
		return tipoProdutoBC;
	}
	
	public void setTipoProdutoBC(TipoProdutoBC tipoProdutoBC) {
		this.tipoProdutoBC = tipoProdutoBC;
	}
	
	public List<TipoProdutoBC> getTipos() {
		return tipos;
	}
	
	public void setTipos(List<TipoProdutoBC> tipos) {
		this.tipos = tipos;
	}

}
