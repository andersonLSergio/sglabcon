package br.com.sglabcon.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.AmostraPaver;
import br.com.sglabcon.domain.EnsaioPaver;
import br.com.sglabcon.domain.Usuario;

public class AmostraPaverDAOTest {

	@Test
	@Ignore
	public void salvar() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(1L);
		
		EnsaioPaverDAO ensaioPaverDAO = new EnsaioPaverDAO();
		EnsaioPaver ensaioPaver = ensaioPaverDAO.buscar(2L);
		
		AmostraPaver amostraPaver = new AmostraPaver();
		
		amostraPaver.setIdentificacao("sdasdsagggg");
		amostraPaver.setForcaAplicada(43.5);
		amostraPaver.setConversaoFA(87.3);
		amostraPaver.setStatus(true);
		amostraPaver.setAlturaReal(125.2);
		amostraPaver.setComprimentoReal(80.4);
		amostraPaver.setLarguraReal(50.2);
		amostraPaver.setData(new Date());
		amostraPaver.setUsuario(usuario);
		amostraPaver.setEnsaioPaver(ensaioPaver);
		
		AmostraPaverDAO amostraPaverDAO = new AmostraPaverDAO();
		amostraPaverDAO.salvar(amostraPaver);
	}
	
	@Test
	@Ignore
	public void listar() {
		AmostraPaverDAO amostraPaverDAO = new AmostraPaverDAO();
		List<AmostraPaver> resultado = amostraPaverDAO.listar();
		
		for(AmostraPaver amostraPaver : resultado) {
			System.out.println("Código: "+ amostraPaver.getCodigo());
			System.out.println("Identificação: "+ amostraPaver.getIdentificacao());
			System.out.println("Força aplicada: "+ amostraPaver.getForcaAplicada());
			System.out.println("Conversão da FA: "+ amostraPaver.getConversaoFA());
			System.out.println("Status: "+ amostraPaver.getStatus());
			System.out.println("Altura real: "+ amostraPaver.getAlturaReal());
			System.out.println("Comprimento real: "+ amostraPaver.getComprimentoReal());
			System.out.println("Largura real: "+ amostraPaver.getLarguraReal());
			System.out.println("Data: "+ amostraPaver.getData());
			System.out.println("Usuário: "+ amostraPaver.getUsuario().getNomeCompleto());
			System.out.println("Ensaio ID: "+ amostraPaver.getEnsaioPaver().getCodigo());
			System.out.println("---------");
		}
	}
	
}
