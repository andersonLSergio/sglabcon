package br.com.sglabcon.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//Classe responsável por minimizar o impacto da inicialização do hibernate,
//Fazendo-o inicializar junto do tomcat
public class HibernateContexto implements ServletContextListener {

	//Para quanto o tomcat é desligado
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

	//Para quanto o tomcat é inicializado força a criação da fábrica de sessões
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.getFabricaDeSessoes();
		
	}

}
