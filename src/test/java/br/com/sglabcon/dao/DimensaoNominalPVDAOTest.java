package br.com.sglabcon.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.DimensaoNominalPV;

public class DimensaoNominalPVDAOTest {

	@Test
	@Ignore
	public void salvar() {
		DimensaoNominalPV dimensaoNominalPV = new DimensaoNominalPV();

		dimensaoNominalPV.setDimensaoNominal("200x200x60mm - TESTE");

		DimensaoNominalPVDAO dimensaoNominalPVDAO = new DimensaoNominalPVDAO();
		dimensaoNominalPVDAO.merge(dimensaoNominalPV);
	}

	@Test
	@Ignore
	public void listar() {
		DimensaoNominalPVDAO dimensaoNominalPVDAO = new DimensaoNominalPVDAO();
		List<DimensaoNominalPV> resultado = dimensaoNominalPVDAO.listar();

		for (DimensaoNominalPV dimensao : resultado) {
			System.out.println(dimensao.getCodigo());
			System.out.println(dimensao.getDimensaoNominal());
			System.out.println("------------");
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		DimensaoNominalPV dimensaoNominalPV = new DimensaoNominalPV();
		DimensaoNominalPVDAO dimensaoNominalPVDAO = new DimensaoNominalPVDAO();

		dimensaoNominalPV = dimensaoNominalPVDAO.buscar(codigo);

		System.out.println(dimensaoNominalPV.getDimensaoNominal());

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;

		DimensaoNominalPV dimensaoNominalPV = new DimensaoNominalPV();
		DimensaoNominalPVDAO dimensaoNominalPVDAO = new DimensaoNominalPVDAO();

		dimensaoNominalPV = dimensaoNominalPVDAO.buscar(codigo);
		
		dimensaoNominalPVDAO.excluir(dimensaoNominalPV);
		
		System.out.println("Excluido: "+ dimensaoNominalPV.getDimensaoNominal());
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;

		DimensaoNominalPV dimensaoNominalPV = new DimensaoNominalPV();
		DimensaoNominalPVDAO dimensaoNominalPVDAO = new DimensaoNominalPVDAO();

		dimensaoNominalPV = dimensaoNominalPVDAO.buscar(codigo);
		
		listar();
		
		dimensaoNominalPV.setDimensaoNominal("200x200x60mm");
		dimensaoNominalPVDAO.merge(dimensaoNominalPV);
		
		listar();
	}

}
