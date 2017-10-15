package br.com.sglabcon.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sglabcon.domain.AmostraCilindrico;
import br.com.sglabcon.util.HibernateUtil;

public class AmostraCilindricoDAO extends GenericDAO<AmostraCilindrico>{
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<AmostraCilindrico> buscarPorEnsaio(Long ensaioCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(AmostraCilindrico.class);
			consulta.add(Restrictions.eq("ensaioCilindrico.codigo", ensaioCodigo));
			consulta.addOrder(Order.asc("codigo"));
			List<AmostraCilindrico> resultado = consulta.list();
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
