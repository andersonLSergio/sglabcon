package br.com.sglabcon.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.DimensaoBasicaCilindrico;

public class DimensaoBasicaCilindricoDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		DimensaoBasicaCilindrico dimensaoBasica = new DimensaoBasicaCilindrico();
		dimensaoBasica.setDimensaoBasica(200.0);
		
		DimensaoBasicaCilindricoDAO dimensaoBasicaCilindricoDAO = new DimensaoBasicaCilindricoDAO();
		dimensaoBasicaCilindricoDAO.merge(dimensaoBasica);
	}
	
	@Test
	@Ignore
	public void listar() {
		DimensaoBasicaCilindricoDAO dimensaoBasicaCilindricoDAO = new DimensaoBasicaCilindricoDAO();
		List<DimensaoBasicaCilindrico> resultado = dimensaoBasicaCilindricoDAO.listar("dimensaoBasica");
		
		for(DimensaoBasicaCilindrico dimensao : resultado) {
			System.out.println(dimensao.getCodigo());
			System.out.println(dimensao.getDimensaoBasica());
			System.out.println("------------");
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		DimensaoBasicaCilindricoDAO dimensaoBasicaCilindricoDAO = new DimensaoBasicaCilindricoDAO();
		DimensaoBasicaCilindrico dimensaoBasica = dimensaoBasicaCilindricoDAO.buscar(2L);
		
		System.out.println(dimensaoBasica.getCodigo());
		System.out.println(dimensaoBasica.getDimensaoBasica());
	}
	
	@Test
	@Ignore
	public void excluir() {
		listar();
		DimensaoBasicaCilindricoDAO dimensaoBasicaCilindricoDAO = new DimensaoBasicaCilindricoDAO();
		DimensaoBasicaCilindrico dimensaoBasica = dimensaoBasicaCilindricoDAO.buscar(2L);
		
		dimensaoBasicaCilindricoDAO.excluir(dimensaoBasica);
		
		listar();
	}
	
	@Test
	@Ignore
	public void editar() {
		listar();
		DimensaoBasicaCilindricoDAO dimensaoBasicaCilindricoDAO = new DimensaoBasicaCilindricoDAO();
		DimensaoBasicaCilindrico dimensaoBasica = dimensaoBasicaCilindricoDAO.buscar(1L);
		
		dimensaoBasica.setDimensaoBasica(125.35);
		
		dimensaoBasicaCilindricoDAO.merge(dimensaoBasica);
		listar();
	}
	
	
}
