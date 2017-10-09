package br.com.sglabcon.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.sglabcon.dao.UsuarioDAO;
import br.com.sglabcon.domain.Usuario;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private Usuario usuario;
	private Usuario usuarioLogado;

	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}
	
	public void autenticar() {
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getUsername() , usuario.getSenha());
			
			if(usuarioLogado == null || !usuarioLogado.getAtivo()) {
				Messages.addGlobalError("Falha na autenticação. Usuário ou Senha inválidos");
				//Para não redirecionar, inclui-se um return para quebrar a sequencia do método.
				return;
			}
			
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void sair() {
		try {
			usuarioLogado = null;
			Faces.redirect("./pages/autenticacao.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean temPermissoes(List<String> permissoes) {
		for(String permissao : permissoes) {
			char permissaoChar = permissao.charAt(0);
			if(usuarioLogado.getPapel() == permissaoChar) {
				return true;
			}
		}
		return false;
	}
}
