package br.com.sglabcon.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.CidadeDAO;
import br.com.sglabcon.dao.ClienteDAO;
import br.com.sglabcon.dao.EstadoDAO;
import br.com.sglabcon.domain.Cidade;
import br.com.sglabcon.domain.Cliente;
import br.com.sglabcon.domain.Estado;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private Cliente clienteLocal;

	private List<Cliente> clientes;
	private List<Cidade> cidades;

	private Estado estado;
	private List<Estado> estados;
	
	private String cnpjAntigo = "";

	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar banco.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {

			cliente = new Cliente();
			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar gerar novo cliente.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {

			// Se não existe nenhum registro com o mesmo CNPJ, ou
			// o CNPJ atual é igual ao CNPJ antigo (caso de edição), então pode cadastrar
			if (!clienteExists() || cliente.getCnpj().equals(cnpjAntigo)) {
				ClienteDAO clienteDAO = new ClienteDAO();
				clienteDAO.merge(cliente);

				estado = new Estado();

				novo();
				listar();

				Messages.addFlashGlobalInfo("Cliente salvo com sucesso");
			} else {
				Messages.addFlashGlobalError("Este CNPJ já existe no banco de dados.");
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao salvar cliente.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);

			Messages.addFlashGlobalInfo("Cliente excluído com sucesso");

			listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao exluir cliente.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			estado = cliente.getCidade().getEstado();
			
			cnpjAntigo = cliente.getCnpj();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();
			popular();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao selecionar cliente.");
			erro.printStackTrace();
		}
	}

	public void mostrarDetalhes(ActionEvent evento) {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
	}

	// método destinado a popular a lista de cidades a partir da seleção do estado
	// na lista.
	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao popular cidades.");
			erro.printStackTrace();
		}
	}

	// Método destinado a verificar se já existe um cliente com o mesmo cnpj cadastrado
	public boolean clienteExists() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteLocal = clienteDAO.clienteExists(cliente.getCnpj());

			if (clienteLocal != null) {
				return true;
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar banco de dados.");
			erro.printStackTrace();
			return true;
		}

		return false;
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

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	public String getCnpjAntigo() {
		return cnpjAntigo;
	}
	
	public void setCnpjAntigo(String cnpjAntigo) {
		this.cnpjAntigo = cnpjAntigo;
	}

}
