package br.com.sglabcon.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sglabcon.domain.AmostraPaver;
import br.com.sglabcon.util.HibernateUtil;

public class AmostraPaverDAO extends GenericDAO<AmostraPaver>{

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<AmostraPaver> buscarPorEnsaio(Long ensaioCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			
			Criteria consulta = sessao.createCriteria(AmostraPaver.class);
			consulta.add(Restrictions.eq("ensaioPaver.codigo", ensaioCodigo));
			consulta.addOrder(Order.asc("codigo"));
			List<AmostraPaver> resultado = consulta.list();
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
