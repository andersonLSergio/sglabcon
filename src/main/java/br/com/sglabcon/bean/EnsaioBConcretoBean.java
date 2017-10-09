package br.com.sglabcon.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.AmostraBConcretoDAO;
import br.com.sglabcon.dao.ClasseResistenciaDAO;
import br.com.sglabcon.dao.ClienteDAO;
import br.com.sglabcon.dao.EnsaioBConcretoDAO;
import br.com.sglabcon.dao.TipoProdutoBCDAO;
import br.com.sglabcon.domain.AmostraBConcreto;
import br.com.sglabcon.domain.ClasseResistencia;
import br.com.sglabcon.domain.Cliente;
import br.com.sglabcon.domain.EnsaioBConcreto;
import br.com.sglabcon.domain.TipoProdutoBC;

@SuppressWarnings({ "deprecation", "serial" })
@ManagedBean
@SessionScoped
public class EnsaioBConcretoBean extends GenericBean implements Serializable {

	private EnsaioBConcreto ensaioBConcreto;
	private EnsaioBConcreto ensaioDetalhe;
	private AmostraBConcreto amostraBConcreto;

	private List<EnsaioBConcreto> ensaios;

	private List<AmostraBConcreto> amostras;

	private List<ClasseResistencia> classes;

	private List<Cliente> clientes;

	private List<TipoProdutoBC> tiposProduto;

	public void novo() {

		try {
			ensaioBConcreto = new EnsaioBConcreto();

			ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
			classes = classeResistenciaDAO.listar("nome");

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("nomeFantasia");

			TipoProdutoBCDAO tipoProdutoBCDAO = new TipoProdutoBCDAO();
			tiposProduto = tipoProdutoBCDAO.listar("descricao");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao gerar novo ensaio.");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			EnsaioBConcretoDAO ensaioBConcretoDAO = new EnsaioBConcretoDAO();
			ensaios = ensaioBConcretoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar banco");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			// pega o atributo da sessão "autenticacaoBean"
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			ensaioBConcreto.setUsuario(autenticacaoBean.getUsuarioLogado());

			EnsaioBConcretoDAO ensaioBConcretoDAO = new EnsaioBConcretoDAO();
			// Utilizando o salvar com retorno, o código do objeto é preenchido
			// após a persistência
			ensaioBConcretoDAO.salvarComRetorno(ensaioBConcreto);

			Messages.addFlashGlobalInfo("Ensaio cadastrado com sucesso!");

			abrirDetalhes(ensaioBConcreto);

			novo();
			listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao salvar ensaio");
			erro.printStackTrace();
		}
	}

	public void calcularData() {
		Date data2 = new Date();
		long resultado = calcDiaData(ensaioBConcreto.getDataFabricacao(), data2);
		ensaioBConcreto.setIdade(new Short(resultado + ""));
	}

	// public void mostrarDimensaoNominal() {
	//
	// }

	public void abrirDetalhes(EnsaioBConcreto ensaio) {
		try {
			ensaioDetalhe = ensaio;

			buscarAmostraPorEnsaio();

			Faces.redirect("./pages/ensaioBCDetalhes.xhtml");

		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	// Métodos destinados às amostras na página hibrida contendo o objeto do ensaio
	// e das amostras

	public void novaAmostra() {
		amostraBConcreto = new AmostraBConcreto();
	}

	public void buscarAmostraPorEnsaio() {
		try {
			AmostraBConcretoDAO amostraBConcretoDAO = new AmostraBConcretoDAO();
			amostras = amostraBConcretoDAO.buscarPorEnsaio(ensaioDetalhe.getCodigo());

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar amostras no banco");
			erro.printStackTrace();
		}
	}

	public void converterUnidades() {
		Double kn = amostraBConcreto.getForcaAplicada();
		Double resultado = converterKNemMpa(kn);
		System.out.println("Conversão: " + resultado);
		amostraBConcreto.setConversaoFA(resultado);

		// testar se a amostra está dentro da resistência caracteristica dos parâmetros
		if (amostraBConcreto.getConversaoFA() >= ensaioDetalhe.getClasseResistencia().getResistenciaCaracteristica()) {
			amostraBConcreto.setStatus(true);
		} else {
			amostraBConcreto.setStatus(false);
		}
	}

	public void salvarAmostra() {
		try {
			amostraBConcreto.setData(new Date());

			// pega o atributo da sessão "autenticacaoBean"
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			amostraBConcreto.setUsuario(autenticacaoBean.getUsuarioLogado());
			amostraBConcreto.setEnsaioBConcreto(ensaioDetalhe);

			AmostraBConcretoDAO amostraBConcretoDAO = new AmostraBConcretoDAO();
			amostraBConcretoDAO.merge(amostraBConcreto);

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

		if (amostraBConcreto != null) {
			if (amostraBConcreto.getStatusFormatado().equals("Aprovado")) {
				return "green";
			} else if (amostraBConcreto.getStatusFormatado().equals("Reprovado")) {
				return "red";
			}
		}

		return "black";
	}

	// Sobrecarga do método para apresentar a cor formatada numa lista da view
	// onde a variavel da lista é passada por parametro para o bean através do EL
	public String alterarCorStatus(AmostraBConcreto amostra) {

		if (amostra.getStatus()) {
			return "green";
		} else {
			return "red";
		}
	}

	// Setters e Getters

	public EnsaioBConcreto getEnsaioBConcreto() {
		return ensaioBConcreto;
	}

	public void setEnsaioBConcreto(EnsaioBConcreto ensaioBConcreto) {
		this.ensaioBConcreto = ensaioBConcreto;
	}

	public EnsaioBConcreto getEnsaioDetalhe() {
		return ensaioDetalhe;
	}

	public void setEnsaioDetalhe(EnsaioBConcreto ensaioDetalhe) {
		this.ensaioDetalhe = ensaioDetalhe;
	}

	public AmostraBConcreto getAmostraBConcreto() {
		return amostraBConcreto;
	}

	public void setAmostraBConcreto(AmostraBConcreto amostraBConcreto) {
		this.amostraBConcreto = amostraBConcreto;
	}

	public List<ClasseResistencia> getClasses() {
		return classes;
	}

	public void setClasses(List<ClasseResistencia> classes) {
		this.classes = classes;
	}

	public List<EnsaioBConcreto> getEnsaios() {
		return ensaios;
	}

	public void setEnsaios(List<EnsaioBConcreto> ensaios) {
		this.ensaios = ensaios;
	}

	public List<AmostraBConcreto> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<AmostraBConcreto> amostras) {
		this.amostras = amostras;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<TipoProdutoBC> getTiposProduto() {
		return tiposProduto;
	}

	public void setTiposProduto(List<TipoProdutoBC> tiposProduto) {
		this.tiposProduto = tiposProduto;
	}

}
