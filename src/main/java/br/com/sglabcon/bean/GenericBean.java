package br.com.sglabcon.bean;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

public class GenericBean {

	//Método destinado a calcular a diferença entre duas datas e 
	//retornar conversão em dias
	public long calcDiaData(Date data1, Date data2) {
		long diferenca = data2.getTime() - data1.getTime();
		long resultado = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
		
		if(resultado < 0) {
			resultado = 0;
		}
		
		return resultado;
	}
	
	public void redirecionar() {
		try {
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}
}
