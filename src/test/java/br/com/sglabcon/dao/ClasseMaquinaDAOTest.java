package br.com.sglabcon.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.ClasseMaquina;

public class ClasseMaquinaDAOTest {

	@Test
	@Ignore
	public void salvar() {
		ClasseMaquina classeMaquina = new ClasseMaquina();

		classeMaquina.setClasse("Classe 0,5");

		ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
		classeMaquinaDAO.merge(classeMaquina);
	}

	@Test
	@Ignore
	public void listar() {
		ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
		List<ClasseMaquina> resultado = classeMaquinaDAO.listar("classe");

		for (ClasseMaquina classe : resultado) {
			System.out.println(classe.getCodigo());
			System.out.println(classe.getClasse());
			System.out.println("--------------");
		}
	}

	@Test
	@Ignore
	public void buscar() {
		ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
		ClasseMaquina classeMaquina = classeMaquinaDAO.buscar(1L);

		System.out.println(classeMaquina.getCodigo());
		System.out.println(classeMaquina.getClasse());
	}

	@Test
	@Ignore
	public void excluir() {
		salvar();
		listar();
		ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
		ClasseMaquina classeMaquina = classeMaquinaDAO.buscar(2L);

		classeMaquinaDAO.excluir(classeMaquina);
		listar();
	}

	@Test
	public void editar() {
		listar();
		ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
		ClasseMaquina classeMaquina = classeMaquinaDAO.buscar(1L);

		classeMaquina.setClasse("Classe 2");

		classeMaquinaDAO.merge(classeMaquina);
		listar();
	}
}
