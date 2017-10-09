package br.com.sglabcon.bean;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
	
	//Método destinado a converter unidades de kN para Mpa
	public Double converterKNemMpa(Double kn) {
		Double mpa = kn * 1000;
		return mpa;
	}
}
