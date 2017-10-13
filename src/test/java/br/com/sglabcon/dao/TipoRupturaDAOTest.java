package br.com.sglabcon.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.TipoRuptura;

public class TipoRupturaDAOTest {

	@Test
	@Ignore
	public void salvar() {
		TipoRuptura tipoRuptura = new TipoRuptura();
		tipoRuptura.setTipoRuptura("Tipo A [EXCLUIR]");
		tipoRuptura.setDescricao("Cônica e cônica afastada 25 mm do capeamento [EXCLUIR]");
		tipoRuptura.setImgUrl("urlaqui [EXCLUIR]");
		
		TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
		tipoRupturaDAO.merge(tipoRuptura);
	}
	
	@Test
	@Ignore
	public void listar() {
		TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
		List<TipoRuptura> resultado = tipoRupturaDAO.listar("tipoRuptura");
		
		for(TipoRuptura tipoRuptura : resultado) {
			System.out.println(tipoRuptura.getCodigo());
			System.out.println(tipoRuptura.getTipoRuptura());
			System.out.println(tipoRuptura.getDescricao());
			System.out.println(tipoRuptura.getImgUrl());
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
		TipoRuptura tipoRuptura = tipoRupturaDAO.buscar(1L);
		
		System.out.println(tipoRuptura.getCodigo());
		System.out.println(tipoRuptura.getTipoRuptura());
		System.out.println(tipoRuptura.getDescricao());
		System.out.println(tipoRuptura.getImgUrl());
	}
	
	@Test
	@Ignore
	public void editar() {
		TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
		TipoRuptura tipoRuptura = tipoRupturaDAO.buscar(1L);
		
		tipoRuptura.setTipoRuptura("Tipo A [EDITADO]");
		tipoRuptura.setDescricao("Cônica e cônica afastada 25 mm do capeamento [EDITADO]");
		tipoRuptura.setImgUrl("urlaqui [EDITADO]");
		
		tipoRupturaDAO.merge(tipoRuptura);
		
		listar();
	}
	
	@Test
	@Ignore
	public void excluir() {
		salvar();
		listar();
		TipoRupturaDAO tipoRupturaDAO = new TipoRupturaDAO();
		TipoRuptura tipoRuptura = tipoRupturaDAO.buscar(2L);
		
		tipoRupturaDAO.excluir(tipoRuptura);
		
		listar();
	}
}
