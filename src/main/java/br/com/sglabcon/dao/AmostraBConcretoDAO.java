package br.com.sglabcon.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sglabcon.domain.AmostraBConcreto;
import br.com.sglabcon.util.HibernateUtil;

public class AmostraBConcretoDAO extends GenericDAO<AmostraBConcreto> {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<AmostraBConcreto> buscarPorEnsaio(Long ensaioCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			
			Criteria consulta = sessao.createCriteria(AmostraBConcreto.class);
			consulta.add(Restrictions.eq("ensaioBConcreto.codigo", ensaioCodigo));
			consulta.addOrder(Order.asc("codigo"));
			List<AmostraBConcreto> resultado = consulta.list();
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
}
