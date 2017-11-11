package br.com.sglabcon.bean;

import org.junit.Test;

public class EnsaioCilindricoBeanTest {
	
	@Test
	public void obterInterpolacao() {
		
		Double x = 1.0;
		
		Double x1;
		Double x2;
		Double y1;
		Double y2;
		
		if(x <= 1.25) {
			x1 = 0.93;
			x2 = 1.25;
			y1 = 0.87;
			y2 = 1.0;
		} else if(x <= 1.50) {
			x1 = 0.96;
			x2 = 1.50;
			y1 = 0.93;
			y2 = 1.25;
		} else {
			x1 = 1.0;
			x2 = 2.0;
			y1 = 0.96;
			y2 = 1.5;
		}
		
		Double resultado = y1+(y2-y1)*(x-x1)/(x2-x1);
		
		System.out.println("Resultado :"+ resultado);
		
	}

}
