package br.com.sglabcon.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.UsuarioDAO;
import br.com.sglabcon.domain.Usuario;

@SuppressWarnings({ "serial", "deprecation" })
@ViewScoped
@ManagedBean
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private boolean isMinhaConta = false;

	private List<Usuario> usuarios;

	@PostConstruct
	public void listar() {
		try {

			// verifica qual é a pagina atual
			String paginaAtual = Faces.getViewId();

			isMinhaConta = paginaAtual.contains("minhaconta.xhtml");

			if (isMinhaConta) {
				// pega o atributo da sessão "autenticacaoBean"
				AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

				usuario = autenticacaoBean.getUsuarioLogado();

			} else {

				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarios = usuarioDAO.listar();
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Houve um erro ao consultar o banco");
			erro.printStackTrace();
		}
	}

	public void novo() {
		usuario = new Usuario();
	}

	public void salvar() {
		try {

			// Se a senha foi digitada, então é um usuário novo
			// caso contrário, significa que se trata de uma edição
			// onde a senha não é digitada, portanto: nula
			if (usuario.getSenhaSemCriptografia() != null) {
				System.out.println("Senha sem criptografia digitada");
				SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
				usuario.setSenha(hash.toHex());
				Messages.addGlobalInfo("Usuário cadastrado com sucesso");
			} else {
				Messages.addGlobalInfo("Usuário alterado com sucesso");
			}

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			novo();
			listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar usuário");
			erro.printStackTrace();
		}
	}

	public void alterarSenha() {
		try {
			
			System.out.println("É página Minha conta: "+ isMinhaConta);
			System.out.println("senha sem cripto: "+ usuario.getSenhaSemCriptografia());
			System.out.println("senha confirmacao: "+ usuario.getSenhaConfirmacao());
			
			//se as senhas coincidem ou não é a página minhaconta.xhtml
			if (usuario.getSenhaSemCriptografia().equals(usuario.getSenhaConfirmacao()) || !isMinhaConta) {
				SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
				usuario.setSenha(hash.toHex());
				Messages.addGlobalInfo("Senha alterada com sucesso");

				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioDAO.merge(usuario);

			} else {
				Messages.addFlashGlobalError("As senhas não coincidem. Tente novamente");
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar usuário");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao excluir usuário");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
