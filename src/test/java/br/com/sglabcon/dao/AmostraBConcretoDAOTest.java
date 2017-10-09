package br.com.sglabcon.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.AmostraBConcreto;
import br.com.sglabcon.domain.EnsaioBConcreto;
import br.com.sglabcon.domain.Usuario;

public class AmostraBConcretoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(1L);
		
		EnsaioBConcretoDAO ensaioBConcretoDAO = new EnsaioBConcretoDAO();
		EnsaioBConcreto ensaioBConcreto = ensaioBConcretoDAO.buscar(4L);
		
		AmostraBConcreto amostraBConcreto = new AmostraBConcreto();
		
		amostraBConcreto.setIdentificacao("EdicaoTesteExcluir");
		amostraBConcreto.setForcaAplicada(43.5);
		amostraBConcreto.setConversaoFA(87.3);
		amostraBConcreto.setStatus(false);
		amostraBConcreto.setData(new Date());
		amostraBConcreto.setUsuario(usuario);
		amostraBConcreto.setEnsaioBConcreto(ensaioBConcreto);
		
		AmostraBConcretoDAO amostraBConcretoDAO = new AmostraBConcretoDAO();
		amostraBConcretoDAO.salvar(amostraBConcreto);
		
	}
	
	@Test
	@Ignore
	public void listar() {
		AmostraBConcretoDAO amostraBConcretoDAO = new AmostraBConcretoDAO();
		List<AmostraBConcreto> resultado = amostraBConcretoDAO.listar();
		
		for(AmostraBConcreto amostraBConcreto : resultado) {
			System.out.println("Código: "+ amostraBConcreto.getCodigo());
			System.out.println("Identificação: "+ amostraBConcreto.getIdentificacao());
			System.out.println("Força aplicada: "+ amostraBConcreto.getForcaAplicada());
			System.out.println("Conversão da FA: "+ amostraBConcreto.getConversaoFA());
			System.out.println("Status: "+ amostraBConcreto.getStatus());
			System.out.println("Data: "+ amostraBConcreto.getData());
			System.out.println("Usuário: "+ amostraBConcreto.getUsuario().getNomeCompleto());
			System.out.println("Ensaio ID: "+ amostraBConcreto.getEnsaioBConcreto().getCodigo());
			System.out.println("---------");
		}
	}
	
	@Test
	public void buscarPorEnsaio() {
		
		Long ensaioCodigo = 4L;
		
		AmostraBConcretoDAO amostraBConcretoDAO = new AmostraBConcretoDAO();
		List<AmostraBConcreto> resultado = amostraBConcretoDAO.buscarPorEnsaio(ensaioCodigo);
		
		for(AmostraBConcreto amostraBConcreto : resultado) {
			System.out.println("Código: "+ amostraBConcreto.getCodigo());
			System.out.println("Identificação: "+ amostraBConcreto.getIdentificacao());
			System.out.println("Força aplicada: "+ amostraBConcreto.getForcaAplicada());
			System.out.println("Conversão da FA: "+ amostraBConcreto.getConversaoFA());
			System.out.println("Status: "+ amostraBConcreto.getStatus());
			System.out.println("Data: "+ amostraBConcreto.getData());
			System.out.println("Usuário: "+ amostraBConcreto.getUsuario().getNomeCompleto());
			System.out.println("Ensaio ID: "+ amostraBConcreto.getEnsaioBConcreto().getCodigo());
			System.out.println("---------");
		}
	}
	
}
