package br.com.sglabcon.bean;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.AmostraCilindricoDAO;
import br.com.sglabcon.dao.ClienteDAO;
import br.com.sglabcon.dao.DimensaoBasicaCilindricoDAO;
import br.com.sglabcon.dao.EnsaioCilindricoDAO;
import br.com.sglabcon.dao.TipoRupturaDAO;
import br.com.sglabcon.domain.AmostraCilindrico;
import br.com.sglabcon.domain.Cliente;
import br.com.sglabcon.domain.DimensaoBasicaCilindrico;
import br.com.sglabcon.domain.EnsaioCilindrico;
import br.com.sglabcon.domain.TipoRuptura;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class EnsaioCilindricoBean extends GenericBean {
	private EnsaioCilindrico ensaioCilindrico;
	private EnsaioCilindrico ensaioDetalhe;
	private List<EnsaioCilindrico> ensaios;

	private DimensaoBasicaCilindrico dimensaoBasica;
	private List<DimensaoBasicaCilindrico> dimensoes;

	private Cliente cliente;
	private List<Cliente> clientes;

	private AmostraCilindrico amostra;
	private List<AmostraCilindrico> amostras;
	
	private TipoRuptura tipoRuptura;
	private List<TipoRuptura> tiposRuptura;
	
	DecimalFormat formatador = new DecimalFormat("#.00");

	@PostConstruct
	public void listar() {
		try {
			EnsaioCilindricoDAO ensaioCilindricoDAO = new EnsaioCilindricoDAO();
			ensaios = ensaioCilindricoDAO.listar();
			
			//para resolver o problema de nullpointer durante a renderização da página
			amostra = new AmostraCilindrico();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao fazer consulta no banco");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			ensaioCilindrico = new EnsaioCilindrico();

			DimensaoBasicaCilindricoDAO dimensaoBasicaCilindricoDAO = new DimensaoBasicaCilindricoDAO();
			dimensoes = dimensaoBasicaCilindricoDAO.listar("dimensaoBasica");

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("nomeFantasia");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao fazer gerar novo ensaio");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			// pega o atributo da sessão "autenticacaoBean"
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			ensaioCilindrico.setUsuario(autenticacaoBean.getUsuarioLogado());

			EnsaioCilindricoDAO ensaioCilindricoDAO = new EnsaioCilindricoDAO();
			ensaioCilindricoDAO.salvarComRetorno(ensaioCilindrico);

			Messages.addFlashGlobalInfo("Ensaio cadastrado com sucesso!");

			// abrirDetalhes(ensaioCilindrico);

			novo();
			listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao salvar ensaio");
			erro.printStackTrace();
		}
	}

	public void abrirDetalhes(EnsaioCilindrico ensaio) {
		try {
			ensaioDetalhe = ensaio;

			buscarAmostraPorEnsaio();

			Faces.redirect("./pages/ensaioCDetalhes.xhtml");

		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void calcularData() {
		Date data2 = new Date();
		long resultado = calcDiaData(ensaioCilindrico.getDataAddAgua(), data2);
		ensaioCilindrico.setIdade(new Short(resultado + ""));
	}

	// Métodos destinados às amostras na página hibrida contendo o objeto do ensaio
	// e das amostras

	public void novaAmostra() {
		try {
			amostra = new AmostraCilindrico();
			
			TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
			tiposRuptura = tipoRupturaDAO.listar("tipoRuptura");
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao gerar nova amostra");
			erro.printStackTrace();
		}
	}


	public void calcularAmostra() {
		//Se os campos altura real, diametro real estão preenchidos, os demais calculos poderão ser realizados
		if (amostra.getAlturaReal() != null && amostra.getDiametroReal() != null && amostra.getForcaAplicada() != null) {
			
			amostra.setRelacaoHD(amostra.getAlturaReal() / amostra.getDiametroReal());
			
			//De acordo com a norma 5739, se a relação h/d for menor que 1.94
			//a força máxima deverá ser multiplicada por um fator de correção
			//caso não necessite, o fator é 1 já que o produto da multiplicação
			//não altera o valor original da força f.
			if(amostra.getRelacaoHD() >= 1.94 ) {
				amostra.setFatorCorrecao(1.0);
			} else {
				amostra.setFatorCorrecao(obterInterpolacao(amostra.getRelacaoHD()));
			}
			
			//f: força máxima alcançada
			//d: diametro do corpo de prova
			//fórmula de acordo com a norma: fc = 4F/pi * D²
			Double f = amostra.getForcaAplicada() * amostra.getFatorCorrecao();
			Double pi = 3.14;
			Double d = amostra.getDiametroReal();
			Double resultado = (4*f)/(pi*(d*d));
			
			resultado = new Double(formatador.format(resultado));
			
			amostra.setConversaoFA(resultado);
			
			// testar se a amostra está dentro da resistência especificada no projeto
			if (amostra.getConversaoFA() >= ensaioDetalhe.getResistenciaProjeto()) {
				amostra.setStatus(true);
			} else {
				amostra.setStatus(false);
			}
		}
	}
	
	//De acordo com a norma 5739 da ABNT, deve se obter um fator de correção 
	//através de interpolação linear e multiplica-lo pela força aplicada
	public Double obterInterpolacao(Double x) {
		//de acordo com a tabela, 2.00 está para 1 assim como 1.00 está para 0.87
		//Ou seja:
		//1 -> 0.87
		//2.00 -> 1
		//x -> ?
		Double x1 = 1.0;
		Double x2 = 2.00;
		Double y1 = 0.87;
		Double y2 = 1.0;
		Double resultado = y1+(y2-y1)*(x-x1)/(x2-x1);
		
		resultado = new Double(formatador.format(resultado));
		
		return resultado;
	}

	public void buscarAmostraPorEnsaio() {
		try {
			AmostraCilindricoDAO amostraCilindricoDAO = new AmostraCilindricoDAO();
			amostras = amostraCilindricoDAO.buscarPorEnsaio(ensaioDetalhe.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar amostras no banco");
			erro.printStackTrace();
		}
	}

	public void salvarAmostra() {
		try {

			amostra.setData(new Date());

			// pega o atributo da sessão "autenticacaoBean"
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			amostra.setUsuario(autenticacaoBean.getUsuarioLogado());

			amostra.setEnsaioCilindrico(ensaioDetalhe);

			AmostraCilindricoDAO amostraCilindricoDAO = new AmostraCilindricoDAO();
			amostraCilindricoDAO.merge(amostra);

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

		if (amostra != null) {
			if (amostra.getStatusFormatado().equals("Aprovado")) {
				return "green";
			} else if (amostra.getStatusFormatado().equals("Reprovado")) {
				return "red";
			}
		}

		return "black";
	}

	// Sobrecarga do método para apresentar a cor formatada numa lista da view
	// onde a variavel da lista é passada por parametro para o bean através do EL
	public String alterarCorStatus(AmostraCilindrico amostra) {

		if (amostra.getStatus()) {
			return "green";
		} else {
			return "red";
		}
	}
	
	//Se o tipo de ruptura não foi definido (é opcional),
	//então não deve ser renderizado na tela
	public Boolean tipoRupturaIsDefined(AmostraCilindrico amostra) {
		if(amostra.getTipoRuptura() == null) {
			return false;
		}
		return true;
	}
	
	public Boolean tipoRupturaIsDefined() {
		if(amostra.getTipoRuptura() == null) {
			return false;
		}
		return true;
	}

	// setters e getters
	public EnsaioCilindrico getEnsaioCilindrico() {
		return ensaioCilindrico;
	}

	public void setEnsaioCilindrico(EnsaioCilindrico ensaioCilindrico) {
		this.ensaioCilindrico = ensaioCilindrico;
	}

	public EnsaioCilindrico getEnsaioDetalhe() {
		return ensaioDetalhe;
	}

	public void setEnsaioDetalhe(EnsaioCilindrico ensaioDetalhe) {
		this.ensaioDetalhe = ensaioDetalhe;
	}

	public List<EnsaioCilindrico> getEnsaios() {
		return ensaios;
	}

	public void setEnsaios(List<EnsaioCilindrico> ensaios) {
		this.ensaios = ensaios;
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public AmostraCilindrico getAmostra() {
		return amostra;
	}

	public void setAmostra(AmostraCilindrico amostra) {
		this.amostra = amostra;
	}

	public List<AmostraCilindrico> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<AmostraCilindrico> amostras) {
		this.amostras = amostras;
	}
	
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
