package br.com.sglabcon.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.sglabcon.bean.AutenticacaoBean;
import br.com.sglabcon.domain.Usuario;

@SuppressWarnings("serial")
public class AuthListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		
		//verifica qual é a pagina atual
		String paginaAtual = Faces.getViewId();
		
		boolean isPaginaPublica = paginaAtual.contains("autenticacao.xhtml");
		
		if(!isPaginaPublica) {
			//pega o atributo da sessão "autenticacaoBean"
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			
			//verifica se o bean foi criado
			if(autenticacaoBean == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			
			//se o bean foi criado mas o usuário logado é nulo, então o usuário não conseguiu autenticar
			if(usuario == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
