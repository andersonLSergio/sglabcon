package br.com.sglabcon.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.ClasseMaquina;
import br.com.sglabcon.domain.Cliente;
import br.com.sglabcon.domain.DimensaoBasicaCilindrico;
import br.com.sglabcon.domain.EnsaioCilindrico;

public class EnsaioCilindricoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);
		
		DimensaoBasicaCilindricoDAO dimensaoBasicaDAO = new DimensaoBasicaCilindricoDAO();
		DimensaoBasicaCilindrico dimensaoBasica = dimensaoBasicaDAO.buscar(2L);
		
		ClasseMaquinaDAO classeMaquinaDAO = new ClasseMaquinaDAO();
		ClasseMaquina classeMaquina = classeMaquinaDAO.buscar(1L);
		
		EnsaioCilindrico ensaioCilindrico = new EnsaioCilindrico();
		
		ensaioCilindrico.setDataAddAgua(new Date());
		ensaioCilindrico.setLote("13sa1das");
		ensaioCilindrico.setClasseMaquina(classeMaquina);
		ensaioCilindrico.setQtdAmostras(13);
		ensaioCilindrico.setObservacoes("Excluirrr");
		ensaioCilindrico.setDimensaoBasica(dimensaoBasica);
		ensaioCilindrico.setCapeamento("Capeamento com Enxofre");
		ensaioCilindrico.setResistenciaProjeto(70.5);
		ensaioCilindrico.setIdade(new Short("7"));
		ensaioCilindrico.setCliente(cliente);
		
		EnsaioCilindricoDAO ensaioCilindricoDAO = new EnsaioCilindricoDAO();
		ensaioCilindricoDAO.salvar(ensaioCilindrico);
	}
	
	@Test
	@Ignore
	public void listar() {
		EnsaioCilindricoDAO ensaioCilindricoDAO = new EnsaioCilindricoDAO();
		List<EnsaioCilindrico> resultado = ensaioCilindricoDAO.listar();
		
		for(EnsaioCilindrico ensaioCilindrico : resultado) {
			System.out.println("Código: "+ ensaioCilindrico.getCodigo());
			System.out.println("Data de adição de agua: "+ ensaioCilindrico.getDataAddAgua());
			System.out.println("Lote: "+ ensaioCilindrico.getLote());
			System.out.println("Classe de maquina: "+ ensaioCilindrico.getClasseMaquina());
			System.out.println("Qtd de amostras: "+ ensaioCilindrico.getQtdAmostras());
			System.out.println("Observações: "+ ensaioCilindrico.getObservacoes());
			System.out.println("Dimensão básica: "+ ensaioCilindrico.getDimensaoBasica());
			System.out.println("Capeamento: "+ ensaioCilindrico.getCapeamento());
			System.out.println("Resistência de Projeto: "+ ensaioCilindrico.getResistenciaProjeto());
			System.out.println("Idade (dias): "+ ensaioCilindrico.getIdade());
			System.out.println("Cliente: "+ ensaioCilindrico.getCliente().getNomeFantasia());
			System.out.println("--------------------");
		}
		
	}
	
}
