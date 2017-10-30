package br.com.sglabcon.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.sglabcon.domain.Usuario;
import br.com.sglabcon.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{

	public Usuario autenticar(String username, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			@SuppressWarnings("deprecation")
			Criteria consulta = sessao.createCriteria(Usuario.class);
			
			consulta.add(Restrictions.eq("username", username));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public Usuario buscarPorUsername(String username) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("username", username));
			Usuario resultado = (Usuario) consulta.uniqueResult();
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
