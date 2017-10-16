package br.com.sglabcon.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.ClasseMaquina;
import br.com.sglabcon.domain.Cliente;
import br.com.sglabcon.domain.EnsaioPaver;

public class EnsaioPaverDAOTest {

	@Test
	@Ignore
	public void salvar() {
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(2L);
		
		ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
		ClasseMaquina classeMaquina = classeMaquinaDAO.buscar(1L);
		
		EnsaioPaver ensaioPaver = new EnsaioPaver();

		ensaioPaver.setDataFabricacao(new Date());
		ensaioPaver.setLote("13sa1das");
		ensaioPaver.setClasseMaquina(classeMaquina);
		ensaioPaver.setQtdAmostras(13);
		ensaioPaver.setObservacoes("Exclusãaao");
		ensaioPaver.setTipoPeca("16 faces");
		ensaioPaver.setFatorCorrecao(3.5);
		ensaioPaver.setResistenciaCaracteristica(70.5);
		ensaioPaver.setIdade(new Short("7"));
		ensaioPaver.setCliente(cliente);
		
		EnsaioPaverDAO ensaioPaverDAO = new EnsaioPaverDAO();
		ensaioPaverDAO.salvar(ensaioPaver);
	}
	
	@Test
	@Ignore
	public void listar() {
		EnsaioPaverDAO ensaioPaverDAO = new EnsaioPaverDAO();
		List<EnsaioPaver> resultado = ensaioPaverDAO.listar();
		
		for(EnsaioPaver ensaioPaver : resultado) {
			System.out.println("Código: " + ensaioPaver.getCodigo());
			System.out.println("Lote: " + ensaioPaver.getLote());
			System.out.println("Classe de Máquina: " + ensaioPaver.getClasseMaquina());
			System.out.println("Qtd de Amostras: " + ensaioPaver.getQtdAmostras());
			System.out.println("Observações: " + ensaioPaver.getObservacoes());
			System.out.println("Dimensão Nominal: " + ensaioPaver.getDimensaoNominal());
			System.out.println("Fator de Correção: " + ensaioPaver.getFatorCorrecao());
			System.out.println("Idade: " + ensaioPaver.getIdade());
			System.out.println("Cliente: " + ensaioPaver.getCliente().getNomeFantasia());
			System.out.println("Resistencia Característica: "
					+ ensaioPaver.getResistenciaCaracteristica());
			System.out.println("--------------------");
		}
	}

}
