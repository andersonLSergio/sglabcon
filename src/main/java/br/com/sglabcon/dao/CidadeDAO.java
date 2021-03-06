package br.com.sglabcon.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sglabcon.domain.Cidade;
import br.com.sglabcon.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Cidade> buscarPorEstado(Long estadoCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	//busca no banco uma cidade que já existe no mesmo estado que se deseja inserir uma nova
	//cidade com o mesmo nome.
	@SuppressWarnings("deprecation")
	public Cidade cidadeExists(String nome, String estadoSigla) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.createAlias("estado", "e");
			consulta.add(Restrictions.eq("e.sigla", estadoSigla));
			consulta.add(Restrictions.eq("nome", nome));
			Cidade resultado = (Cidade) consulta.uniqueResult();
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
