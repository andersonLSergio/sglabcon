package br.com.sglabcon.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.ClasseResistencia;
import br.com.sglabcon.domain.Cliente;
import br.com.sglabcon.domain.EnsaioBConcreto;

public class EnsaioBConcretoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(2L);

		ClasseResistenciaDAO classeResistenciaDAO = new ClasseResistenciaDAO();
		ClasseResistencia classeResistencia = classeResistenciaDAO.buscar(1L);

		EnsaioBConcreto ensaioBConcreto = new EnsaioBConcreto();

		ensaioBConcreto.setDataFabricacao(new Date());
		ensaioBConcreto.setLote("13ADD2");
		ensaioBConcreto.setTipoMaquina("MCT-1388");
		ensaioBConcreto.setQtdAmostras(5);
		ensaioBConcreto.setObservacoes("Sem observações");
		ensaioBConcreto.setFatorCorrecao(1.5);
		ensaioBConcreto.setIdade(new Short("10"));
		ensaioBConcreto.setCliente(cliente);
		ensaioBConcreto.setClasseResistencia(classeResistencia);

		EnsaioBConcretoDAO ensaioBConcretoDAO = new EnsaioBConcretoDAO();
		ensaioBConcretoDAO.salvar(ensaioBConcreto);
	}

	@Test
	@Ignore
	public void listar() {
		EnsaioBConcretoDAO ensaioBConcretoDAO = new EnsaioBConcretoDAO();
		List<EnsaioBConcreto> resultado = ensaioBConcretoDAO.listar();

		for (EnsaioBConcreto ensaioBConcreto : resultado) {
			System.out.println("Código: " + ensaioBConcreto.getCodigo());
			System.out.println("Lote: " + ensaioBConcreto.getLote());
			System.out.println("Tipo de Máquina: " + ensaioBConcreto.getTipoMaquina());
			System.out.println("Qtd de Amostras: " + ensaioBConcreto.getQtdAmostras());
			System.out.println("Observações: " + ensaioBConcreto.getObservacoes());
			System.out.println("Fator de Correção: " + ensaioBConcreto.getFatorCorrecao());
			System.out.println("Idade: " + ensaioBConcreto.getIdade());
			System.out.println("Cliente: " + ensaioBConcreto.getCliente().getNomeFantasia());
			System.out.println("Classe de Resistência: " + ensaioBConcreto.getClasseResistencia().getNome());
			System.out.println("Resistencia Característica: "
					+ ensaioBConcreto.getClasseResistencia().getResistenciaCaracteristica());
			System.out.println("--------------------");
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		EnsaioBConcretoDAO ensaioBConcretoDAO = new EnsaioBConcretoDAO();
		EnsaioBConcreto ensaioBConcreto = ensaioBConcretoDAO.buscar(3L);
		
		System.out.println("Código: " + ensaioBConcreto.getCodigo());
		System.out.println("Lote: " + ensaioBConcreto.getLote());
		System.out.println("Tipo de Máquina: " + ensaioBConcreto.getTipoMaquina());
		System.out.println("Qtd de Amostras: " + ensaioBConcreto.getQtdAmostras());
		System.out.println("Observações: " + ensaioBConcreto.getObservacoes());
		System.out.println("Fator de Correção: " + ensaioBConcreto.getFatorCorrecao());
		System.out.println("Cliente: " + ensaioBConcreto.getCliente().getNomeFantasia());
		System.out.println("Classe de Resistência: " + ensaioBConcreto.getClasseResistencia().getNome());
		System.out.println("Resistencia Característica: "
				+ ensaioBConcreto.getClasseResistencia().getResistenciaCaracteristica());
	}
	
	@Test
	@Ignore
	public void excluir() {
		EnsaioBConcretoDAO ensaioBConcretoDAO = new EnsaioBConcretoDAO();
		EnsaioBConcreto ensaioBConcreto = ensaioBConcretoDAO.buscar(3L);
		
		ensaioBConcretoDAO.excluir(ensaioBConcreto);
	}
	
	@Test
	@Ignore
	public void editar() {
		EnsaioBConcretoDAO ensaioBConcretoDAO = new EnsaioBConcretoDAO();
		EnsaioBConcreto ensaioBConcreto = ensaioBConcretoDAO.buscar(2L);
		
		ensaioBConcreto.setObservacoes("Tá testado a edição");
		
		ensaioBConcretoDAO.editar(ensaioBConcreto);
	}

}
