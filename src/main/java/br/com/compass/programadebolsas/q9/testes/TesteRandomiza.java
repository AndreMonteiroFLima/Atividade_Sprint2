package br.com.compass.programadebolsas.q9.testes;

import java.util.Random;

public class TesteRandomiza {

	public static void main(String[] args) {
		
		Random r = new Random();
		float f =(float) Math.round(r.nextFloat()*100)/100;
		System.out.println(f);
		
	}
	
}
