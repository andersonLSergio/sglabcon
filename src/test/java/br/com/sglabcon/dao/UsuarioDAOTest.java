package br.com.sglabcon.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.sglabcon.domain.Usuario;

public class UsuarioDAOTest {
	
	@Test
	@Ignore
	public void autenticar() {
		String username = "anderson";
		String senha = "anderson123";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(username, senha);
		
		if(usuario != null) {
			System.out.println("Usuário '"+ usuario.getUsername() + "' autenticado!");
			System.out.println("Código do usuário: "+ usuario.getCodigo());
		} else {
			System.out.println("Falha na autenticação");
		}
	}
	
	@Test
	@Ignore
	public void buscarPorUsername() {
		String username = "anderson2";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorUsername(username);
		
		if(usuario != null) {
			System.out.println("Usuário '"+ usuario.getUsername() + "' encontrado!");
			System.out.println("Código do usuário: "+ usuario.getCodigo());
		} else {
			System.out.println("Nenhum resultado");
		}
	}

	@Test
	@Ignore
	public void salvar() {
		Usuario usuario = new Usuario();

		usuario.setNomeCompleto("Teste Exclusão");
		usuario.setUsername("teste");
		usuario.setSenhaSemCriptografia("testeee");

		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());

		usuario.setSenha(hash.toHex());
		usuario.setEmail("teste@gmail.com");
		usuario.setPapel('L');

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}

	@Test
	@Ignore
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();

		for (Usuario usuario : resultado) {
			System.out.println("Código: " + usuario.getCodigo());
			System.out.println("Nome Completo: " + usuario.getNomeCompleto());
			System.out.println("Usuário: " + usuario.getUsername());
			if (usuario.getPapel() == 'A') {
				System.out.println("Papel: Administrador");
			} else if (usuario.getPapel() == 'L') {
				System.out.println("Papel: Laboratorista");
			} else {
				System.out.println("Papel inválido");
			}
			System.out.println("-------------");
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigoUsuario = 2L;

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigoUsuario);

		System.out.println("Código: " + usuario.getCodigo());
		System.out.println("Nome Completo: " + usuario.getNomeCompleto());
		System.out.println("Usuário: " + usuario.getUsername());
		if (usuario.getPapel() == 'A') {
			System.out.println("Papel: Administrador");
		} else if (usuario.getPapel() == 'L') {
			System.out.println("Papel: Laboratorista");
		} else {
			System.out.println("Papel inválido");
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigoUsuario = 4L;

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigoUsuario);
		
		usuarioDAO.excluir(usuario);
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoUsuario = 3L;

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigoUsuario);
		
		usuario.setNomeCompleto("Teste Editado");
		usuario.setUsername("testão");
		usuario.setSenhaSemCriptografia("testudo");

		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());

		usuario.setSenha(hash.toHex());
		usuario.setEmail("teste@yahoo.com");
		usuario.setPapel('A');
		
		usuarioDAO.editar(usuario);
	}

}
