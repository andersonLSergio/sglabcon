package br.com.sglabcon.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.Estado;

public class EstadoDAOTest {

	@Test
	@Ignore
	public void salvar() {

		Estado estado = new Estado();

		estado.setNome("EStadoTesteExclusao");
		estado.setSigla("TT");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);

	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		for (Estado estado : resultado) {
			System.out.println("Código: " + estado.getCodigo());
			System.out.println("Nome: " + estado.getNome());
			System.out.println("Sigla: " + estado.getSigla());
			System.out.println("--------");
		}

	}

	@Test
	@Ignore
	public void buscar() {
		Long codEstado = 2L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codEstado);

		System.out.println("Código: " + estado.getCodigo());
		System.out.println("Nome: " + estado.getNome());
		System.out.println("Sigla: " + estado.getSigla());

	}

	@Test
	@Ignore
	public void excluir() {
		Long codEstado = 4L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codEstado);
		
		estadoDAO.excluir(estado);
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codEstado = 3L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codEstado);
		
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");
		
		estadoDAO.editar(estado);
	}

}
