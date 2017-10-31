package br.com.sglabcon.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.CidadeDAO;
import br.com.sglabcon.dao.EstadoDAO;
import br.com.sglabcon.domain.Cidade;
import br.com.sglabcon.domain.Estado;

@SuppressWarnings({ "deprecation", "serial" })
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	private Cidade cidade;
	private Cidade localCidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct
	public void listar() {
		try {

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao consultar o banco");
			erro.printStackTrace();
		}
	}

	public void novo() {

		try {
			cidade = new Cidade();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova cidade");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if (!cidadeExists()) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidadeDAO.merge(cidade);

				novo();
				listar();
				Messages.addGlobalInfo("Cidade salva com sucesso");
			} else {
				Messages.addFlashGlobalError("Cidade já existente no banco de dados.");
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova cidade");
			erro.printStackTrace();
		}
	}

	// Método destinado a verificar se já existe uma cidade no mesmo estado no BD
	// Ex.: É perfeitamente normal duas cidades com mesmo nome, porém em estado
	// diferentes:
	// Irati - PR e Irati - SC
	// Mas não podem existir duas cidades com o mesmo nome no mesmo estado.
	public boolean cidadeExists() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			localCidade = cidadeDAO.cidadeExists(cidade.getNome(), cidade.getEstado().getSigla());

			if (localCidade != null) {
				if (localCidade.getEstado().getSigla().equals(cidade.getEstado().getSigla())) {
					return true;
				}
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao consultar banco de dados");
			erro.printStackTrace();
		}
		return false;
	}

	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);

			Messages.addGlobalInfo("Registro |" + cidade.getNome() + "| excluído com sucesso");
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir cidade");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao selecionar cidade");
			erro.printStackTrace();
		}
	}

}
