package br.com.sglabcon.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.TipoProdutoBC;

public class TipoProdutoBCDAOTest {

	@Test
	@Ignore
	public void salvar() {
		TipoProdutoBC tipoProduto = new TipoProdutoBC();

		tipoProduto.setDescricao("ApagaÇaPixorra");
		tipoProduto.setDimensaoNominal("220x110x80");

		TipoProdutoBCDAO tipoDAO = new TipoProdutoBCDAO();
		tipoDAO.merge(tipoProduto);
	}

	@Test
	public void listar() {
		TipoProdutoBCDAO tipoDAO = new TipoProdutoBCDAO();

		List<TipoProdutoBC> resultado = tipoDAO.listar("descricao");

		for (TipoProdutoBC tipoProdutoBC : resultado) {
			System.out.println(tipoProdutoBC.getCodigo());
			System.out.println(tipoProdutoBC.getDescricao());
			System.out.println(tipoProdutoBC.getDimensaoNominal());
			System.out.println("---------------");
		}

	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;

		TipoProdutoBC tipoProdutoBC = new TipoProdutoBC();

		TipoProdutoBCDAO tipoProdutoBCDAO = new TipoProdutoBCDAO();
		tipoProdutoBC = tipoProdutoBCDAO.buscar(codigo);

		System.out.println(tipoProdutoBC.getCodigo());
		System.out.println(tipoProdutoBC.getDescricao());
		System.out.println(tipoProdutoBC.getDimensaoNominal());
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;

		TipoProdutoBC tipoProdutoBC = new TipoProdutoBC();

		TipoProdutoBCDAO tipoProdutoBCDAO = new TipoProdutoBCDAO();
		tipoProdutoBC = tipoProdutoBCDAO.buscar(codigo);
		
		tipoProdutoBCDAO.excluir(tipoProdutoBC);
		
		System.out.println("Excluido: "+ tipoProdutoBC.getDescricao());
	}
	
	@Test
	@Ignore
	public void editar() {
		
		Long codigo = 2L;

		TipoProdutoBC tipoProdutoBC = new TipoProdutoBC();

		TipoProdutoBCDAO tipoProdutoBCDAO = new TipoProdutoBCDAO();
		tipoProdutoBC = tipoProdutoBCDAO.buscar(codigo);
		
		tipoProdutoBC.setDescricao("teje Editado");
		
		tipoProdutoBCDAO.merge(tipoProdutoBC);
		
	}

}
