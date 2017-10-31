package br.com.sglabcon.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.Cidade;
import br.com.sglabcon.domain.Estado;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Long codEstado = 1L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codEstado);

		Cidade cidade = new Cidade();
		cidade.setNome("CidadeTesteExclusão");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();

		List<Cidade> resultado = cidadeDAO.listar();

		for (Cidade cidade : resultado) {
			System.out.println("Código: " + cidade.getCodigo());
			System.out.println("Nome: " + cidade.getNome());
			System.out
					.println("Nome do Estado: " + cidade.getEstado().getNome() + " - " + cidade.getEstado().getSigla());
			System.out.println("-------------");
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codCidade = new Long("1");

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codCidade);

		System.out.println("Código: " + cidade.getCodigo());
		System.out.println("Nome: " + cidade.getNome());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome() + " - " + cidade.getEstado().getSigla());
	}
	

	@Test
	@Ignore
	public void cidadeExists() {
		String nomeCidade = "Irati";
		String estadoSigla = "SC";
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.cidadeExists(nomeCidade, estadoSigla);
		
		if(cidade != null) {
			System.out.println("Código: " + cidade.getCodigo());
			System.out.println("Nome: " + cidade.getNome());
			System.out.println("Nome do Estado: " + cidade.getEstado().getNome() + " - " + cidade.getEstado().getSigla());
		} else {
			System.out.println("Nenhum nenhuma cidade encontrada.");
		}
			
		}

	
	@Test
	@Ignore
	public void excluir() {
		Long codigoCidade = new Long("4");
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		System.out.println("Cidade Excluida:");
		System.out.println("Código: " + cidade.getCodigo());
		System.out.println("Nome: " + cidade.getNome());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome() + " - " + cidade.getEstado().getSigla());
		
		cidadeDAO.excluir(cidade);
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoCidade = 3L;
		Long codigoEstado = 1L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		System.out.println("Cidade Antes:");
		System.out.println("Código: " + cidade.getCodigo());
		System.out.println("Nome: " + cidade.getNome());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome() + " - " + cidade.getEstado().getSigla());
		System.out.println("-------------");
		
		cidade.setNome("Guarapuava");
		cidade.setEstado(estado);
		
		System.out.println("Cidade Depois:");
		System.out.println("Código: " + cidade.getCodigo());
		System.out.println("Nome: " + cidade.getNome());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome() + " - " + cidade.getEstado().getSigla());
		
		cidadeDAO.editar(cidade);
	}
	
	
	

}
