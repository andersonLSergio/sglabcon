package br.com.sglabcon.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.Cidade;
import br.com.sglabcon.domain.Cliente;

public class ClienteDAOTest {

	@Test
	@Ignore
	public void salvar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(5L);

		Cliente cliente = new Cliente();

		cliente.setCnpj("28.821.150/0001-85");
		cliente.setNomeFantasia("ConstruMais Comércio de Pré Moldados LTDA.");
		cliente.setEndereco("Rua Coronel Pinheiro, 1544");
		cliente.setBairro("Centro");
		cliente.setCep("83580-050");
		cliente.setTelefoneFixo("(43)3582-8091");
		cliente.setTelefoneMovel("(43)99825-7865");
		cliente.setEmail("financeiro@construmais.com.br");
		cliente.setCidade(cidade);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
	}

	@Test
	@Ignore
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar("nomeFantasia");

		for (Cliente cliente : resultado) {
			System.out.println("CNPJ: " + cliente.getCnpj());
			System.out.println("Nome Fantasia: " + cliente.getNomeFantasia());
			System.out.println("Endereço: " + cliente.getEndereco());
			System.out.println("Bairro: " + cliente.getBairro());
			System.out.println("CEP: " + cliente.getCep());
			System.out.println("Telefone Fixo: " + cliente.getTelefoneFixo());
			System.out.println("Telefone Móvel: " + cliente.getTelefoneMovel());
			System.out.println("Email: " + cliente.getEmail());
			System.out.println(
					"Cidade: " + cliente.getCidade().getNome() + " - " + cliente.getCidade().getEstado().getSigla());
			System.out.println("-----------------");
		}
	}

	@Test
	@Ignore
	public void buscar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);

		System.out.println("CNPJ: " + cliente.getCnpj());
		System.out.println("Nome Fantasia: " + cliente.getNomeFantasia());
		System.out.println("Endereço: " + cliente.getEndereco());
		System.out.println("Bairro: " + cliente.getBairro());
		System.out.println("CEP: " + cliente.getCep());
		System.out.println("Telefone Fixo: " + cliente.getTelefoneFixo());
		System.out.println("Telefone Móvel: " + cliente.getTelefoneMovel());
		System.out.println("Email: " + cliente.getEmail());
		System.out.println(
				"Cidade: " + cliente.getCidade().getNome() + " - " + cliente.getCidade().getEstado().getSigla());
	}
	
	@Test
	@Ignore
	public void excluir() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(3L);
		
		clienteDAO.excluir(cliente);
	}
	
	@Test
	@Ignore
	public void editar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(2L);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(2L);
		
		cliente.setCnpj("84.476.762/0001-59");
		cliente.setNomeFantasia("Concreto Rebouças LTDA");
		cliente.setEndereco("Rua 17 de Maio, 254");
		cliente.setBairro("Centro");
		cliente.setCep("84501-000");
		cliente.setTelefoneFixo("(42)3431-5652");
		cliente.setTelefoneMovel("(42)99995-2587");
		cliente.setEmail("concretoreboucas@gmail.com");
		cliente.setCidade(cidade);
		
		clienteDAO.editar(cliente);
	}
}
