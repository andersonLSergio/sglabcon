package br.com.sglabcon.bean;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.AmostraPaverDAO;
import br.com.sglabcon.dao.ClienteDAO;
import br.com.sglabcon.dao.DimensaoNominalPVDAO;
import br.com.sglabcon.dao.EnsaioPaverDAO;
import br.com.sglabcon.domain.AmostraPaver;
import br.com.sglabcon.domain.Cliente;
import br.com.sglabcon.domain.DimensaoNominalPV;
import br.com.sglabcon.domain.EnsaioPaver;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class EnsaioPaverBean extends GenericBean {

	private EnsaioPaver ensaioPaver;
	private EnsaioPaver ensaioDetalhe;
	private AmostraPaver amostraPaver;

	private List<EnsaioPaver> ensaios;
	private List<AmostraPaver> amostras;
	private List<Cliente> clientes;
	private List<DimensaoNominalPV> dimensoes;

	@PostConstruct
	public void listar() {
		try {
			EnsaioPaverDAO ensaioPaverDAO = new EnsaioPaverDAO();
			ensaios = ensaioPaverDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar banco.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			ensaioPaver = new EnsaioPaver();
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("nomeFantasia");
			
			DimensaoNominalPVDAO dimensaoNominalPVDAO = new DimensaoNominalPVDAO();
			dimensoes = dimensaoNominalPVDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao gerar novo ensaio.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			// pega o atributo da sessão "autenticacaoBean"
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			ensaioPaver.setUsuario(autenticacaoBean.getUsuarioLogado());

			// Utilizando o salvar com retorno, o código do objeto é preenchido
			// após a persistência
			EnsaioPaverDAO ensaioPaverDAO = new EnsaioPaverDAO();
			ensaioPaverDAO.salvarComRetorno(ensaioPaver);

			Messages.addFlashGlobalInfo("Ensaio cadastrado com sucesso!");

			abrirDetalhes(ensaioPaver);

			novo();
			listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao salvar ensaio");
			erro.printStackTrace();
		}
	}

	public void calcularData() {
		Date data2 = new Date();
		long resultado = calcDiaData(ensaioPaver.getDataFabricacao(), data2);
		ensaioPaver.setIdade(new Short(resultado + ""));
	}

	public void abrirDetalhes(EnsaioPaver ensaio) {
		try {
			ensaioDetalhe = ensaio;

			buscarAmostraPorEnsaio();

			Faces.redirect("./pages/ensaioPaverDetalhes.xhtml");

		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	// Métodos destinados às amostras na página hibrida contendo o objeto do ensaio
	// e das amostras

	public void novaAmostra() {
		amostraPaver = new AmostraPaver();
	}

	public void buscarAmostraPorEnsaio() {
		try {
			AmostraPaverDAO amostraPaverDAO = new AmostraPaverDAO();
			amostras = amostraPaverDAO.buscarPorEnsaio(ensaioDetalhe.getCodigo());

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar amostras no banco");
			erro.printStackTrace();
		}
	}

	public void converterUnidades() {
		Double kn = amostraPaver.getForcaAplicada();
		Double resultado = converterKNemMpa(kn);
		System.out.println("Conversão: " + resultado);
		amostraPaver.setConversaoFA(resultado);

		// testar se a amostra está dentro da resistência caracteristica dos parâmetros
		if (amostraPaver.getConversaoFA() >= ensaioDetalhe.getResistenciaCaracteristica()) {
			amostraPaver.setStatus(true);
		} else {
			amostraPaver.setStatus(false);
		}
	}

	public void salvarAmostra() {
		try {

			amostraPaver.setData(new Date());

			// pega o atributo da sessão "autenticacaoBean"
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			amostraPaver.setUsuario(autenticacaoBean.getUsuarioLogado());

			amostraPaver.setEnsaioPaver(ensaioDetalhe);

			AmostraPaverDAO amostraPaverDAO = new AmostraPaverDAO();
			amostraPaverDAO.merge(amostraPaver);

			Messages.addFlashGlobalInfo("A amostra foi registrada");

			novaAmostra();
			buscarAmostraPorEnsaio();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao registrar amostra");
			erro.printStackTrace();
		}
	}

	// Método usado para alterar a cor do status durante a realização do ensaio
	// atualizado em tempo real
	public String alterarCorStatus() {

		if (amostraPaver != null) {
			if (amostraPaver.getStatusFormatado().equals("Aprovado")) {
				return "green";
			} else if (amostraPaver.getStatusFormatado().equals("Reprovado")) {
				return "red";
			}
		}

		return "black";
	}

	// Sobrecarga do método para apresentar a cor formatada numa lista da view
	// onde a variavel da lista é passada por parametro para o bean através do EL
	public String alterarCorStatus(AmostraPaver amostra) {

		if (amostra.getStatus()) {
			return "green";
		} else {
			return "red";
		}
	}

	// getters e setters
	public EnsaioPaver getEnsaioPaver() {
		return ensaioPaver;
	}

	public void setEnsaioPaver(EnsaioPaver ensaioPaver) {
		this.ensaioPaver = ensaioPaver;
	}

	public List<EnsaioPaver> getEnsaios() {
		return ensaios;
	}

	public void setEnsaios(List<EnsaioPaver> ensaios) {
		this.ensaios = ensaios;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<DimensaoNominalPV> getDimensoes() {
		return dimensoes;
	}

	public void setDimensoes(List<DimensaoNominalPV> dimensoes) {
		this.dimensoes = dimensoes;
	}

	public EnsaioPaver getEnsaioDetalhe() {
		return ensaioDetalhe;
	}

	public void setEnsaioDetalhe(EnsaioPaver ensaioDetalhe) {
		this.ensaioDetalhe = ensaioDetalhe;
	}

	public List<AmostraPaver> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<AmostraPaver> amostras) {
		this.amostras = amostras;
	}

	public AmostraPaver getAmostraPaver() {
		return amostraPaver;
	}

	public void setAmostraPaver(AmostraPaver amostraPaver) {
		this.amostraPaver = amostraPaver;
	}

}
