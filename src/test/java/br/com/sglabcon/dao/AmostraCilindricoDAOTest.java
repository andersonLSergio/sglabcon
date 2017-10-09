package br.com.sglabcon.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.AmostraCilindrico;
import br.com.sglabcon.domain.EnsaioCilindrico;
import br.com.sglabcon.domain.Usuario;

public class AmostraCilindricoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(2L);
		
		EnsaioCilindricoDAO ensaioCilindricoDAO = new EnsaioCilindricoDAO();
		EnsaioCilindrico ensaioCilindrico = ensaioCilindricoDAO.buscar(2L);
		
		AmostraCilindrico amostraCilindrico = new AmostraCilindrico();
		
		amostraCilindrico.setIdentificacao("testeeeee");
		amostraCilindrico.setForcaAplicada(12.45);
		amostraCilindrico.setConversaoFA(145.58);
		amostraCilindrico.setStatus(true);
		amostraCilindrico.setData(new Date());
		amostraCilindrico.setUsuario(usuario);
		amostraCilindrico.setEnsaioCilindrico(ensaioCilindrico);
		
		AmostraCilindricoDAO amostraCilindricoDAO = new AmostraCilindricoDAO();
		amostraCilindricoDAO.salvar(amostraCilindrico);
	}
	
	@Test
	@Ignore
	public void listar() {
		
		AmostraCilindricoDAO amostraCilindricoDAO = new AmostraCilindricoDAO();
		List<AmostraCilindrico> resultado = amostraCilindricoDAO.listar();
		
		for(AmostraCilindrico amostraCilindrico : resultado) {
			System.out.println("Código: "+ amostraCilindrico.getCodigo());
			System.out.println("Identificação: "+ amostraCilindrico.getIdentificacao());
			System.out.println("Força aplicada: "+ amostraCilindrico.getForcaAplicada());
			System.out.println("Conversão da FA: "+ amostraCilindrico.getConversaoFA());
			System.out.println("Status: "+ amostraCilindrico.getStatus());
			System.out.println("Data: "+ amostraCilindrico.getData());
			System.out.println("Usuário: "+ amostraCilindrico.getUsuario().getNomeCompleto());
			System.out.println("Ensaio ID: "+ amostraCilindrico.getEnsaioCilindrico().getCodigo());
			System.out.println("---------");
		}
		
	}
	
	
	
}
