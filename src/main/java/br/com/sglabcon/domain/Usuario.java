package br.com.sglabcon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

	@Column(length = 40, nullable = false)
	private String nomeCompleto;

	@Column(length = 15, nullable = false)
	private String username;

	@Column(length = 40, nullable = false)
	private String senha;

	@Transient
	private String senhaSemCriptografia;
	
	@Transient
	private String senhaConfirmacao;

	@Column(length = 35, nullable = false)
	private String email;

	@Column(nullable = false)
	private char papel;

	@Column(nullable = false)
	private Boolean ativo;

	// Método dedicado a converter Char em String
	// Para apresentar informação útil para usuário no campo "Papel"
	@Transient // Essa anotação serve para dizer para o hibernate que não se trata de um campo
				// do BD.
	public String getPapelFormatado() {
		String papelFormatado = null;

		if (papel == 'A') {
			papelFormatado = "Administrador";
		} else if (papel == 'L') {
			papelFormatado = "Laboratorista";
		} else {
			papelFormatado = "Inválido ou nulo";
		}

		return papelFormatado;
	}

	// Método similar ao papel formatado só que dessa vez convertendo um Boolean
	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = "Não";

		if (ativo) {
			ativoFormatado = "Sim";
		}

		return ativoFormatado;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}
	
	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}
	
	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getPapel() {
		return papel;
	}

	public void setPapel(char papel) {
		this.papel = papel;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
