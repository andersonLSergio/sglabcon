package br.com.sglabcon.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.ClasseResistencia;

public class ClasseResistenciaDAOTest {

	@Test
	@Ignore
	public void salvar() {
		ClasseResistencia classeResistencia = new ClasseResistencia();
		
		classeResistencia.setNome("T");
		classeResistencia.setResistenciaCaracteristica(30.5);
		
		ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
		classeResistenciaDAO.salvar(classeResistencia);
	}
	
	@Test
	@Ignore
	public void listar() {
		ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
		List<ClasseResistencia> resultado = classeResistenciaDAO.listar("nome");
		
		for(ClasseResistencia classeResistencia : resultado) {
			System.out.println("Código: "+ classeResistencia.getCodigo());
			System.out.println("Nome: "+ classeResistencia.getNome());
			System.out.println("RC: "+ classeResistencia.getResistenciaCaracteristica());
			System.out.println("---------");
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
		ClasseResistencia classeResistencia = classeResistenciaDAO.buscar(2L);
		
		System.out.println("Código: "+ classeResistencia.getCodigo());
		System.out.println("Nome: "+ classeResistencia.getNome());
		System.out.println("RC: "+ classeResistencia.getResistenciaCaracteristica());
	}
	
	@Test
	@Ignore
	public void excluir() {
		ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
		ClasseResistencia classeResistencia = classeResistenciaDAO.buscar(4L);
		
		classeResistenciaDAO.excluir(classeResistencia);
	}
	
	@Test
	@Ignore
	public void editar() {
		ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
		ClasseResistencia classeResistencia = classeResistenciaDAO.buscar(3L);
		
		classeResistencia.setNome("C");
		classeResistencia.setResistenciaCaracteristica(31.7);
		
		classeResistenciaDAO.editar(classeResistencia);
	}
}
