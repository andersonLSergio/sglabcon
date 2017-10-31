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
	private Usuario localUser;
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
			
			//Se o usuário digitado já existe e não se trata de uma edição, não pode cadastrar!
			if(usernameExists(usuario.getUsername()) && usuario.getSenhaSemCriptografia() != null) {
				Messages.addFlashGlobalError("Usuário já cadastrado no sistema");
			} else {
				// Se a senha foi digitada, então é um usuário novo
				// caso contrário, significa que se trata de uma edição
				// onde a senha não é digitada, portanto: nula
				if (usuario.getSenhaSemCriptografia() != null) {
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
			}


		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar usuário");
			erro.printStackTrace();
		}
	}

	public void alterarSenha() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			//Checa se a senha antiga coincide com a senha de autenticação deste usuário
			//Se coincidir, o localUser é preenchido, se não é null
			if(isMinhaConta) {
				localUser = usuarioDAO.autenticar(usuario.getUsername(), usuario.getSenhaAntiga());				
			}
			
			//se o localUser está preenchido ou não é a página minhaconta.xhtml
			//A senha pode ser alterada
			if (localUser != null || !isMinhaConta) {
				SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
				usuario.setSenha(hash.toHex());
				Messages.addGlobalInfo("Senha alterada com sucesso");

				usuarioDAO.merge(usuario);

				//Se o localUser não foi preenchido e está na tela minhaConta
				//Então a senha antiga está incorreta
			} else if(localUser == null) {
				Messages.addFlashGlobalError("Senha antiga incorreta");
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
	
	
	//Este método verifica se o username já foi cadastrado no banco
	public boolean usernameExists(String username) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		localUser = usuarioDAO.buscarPorUsername(username);
		
		if (localUser != null) {
			localUser = null;
			return true;
		}		
		return false;
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
