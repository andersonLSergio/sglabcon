package br.com.sglabcon.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.sglabcon.domain.Cliente;
import br.com.sglabcon.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente>{
	@SuppressWarnings("deprecation")
	public Cliente clienteExists(String cnpj) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Cliente.class);
			consulta.add(Restrictions.eq("cnpj", cnpj));
			Cliente resultado = (Cliente) consulta.uniqueResult();
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
