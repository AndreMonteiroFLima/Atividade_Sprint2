package br.com.compass.programadebolsas.q10.testes;

import org.apache.commons.lang3.StringUtils;

public class TesteSubstring {

	public static void main(String[] args) {
		
		String entrada="Sonhei com Java. :-( :-((vou estudar.";
		String carinhaTriste = ":-(";
		String carinhaFeliz = ":-)";
		
		int qtdCarinhaTriste= StringUtils.countMatches(entrada, carinhaTriste);
		int qtdCarinhaFeliz= StringUtils.countMatches(entrada, carinhaFeliz);
		
		System.out.println("A qtd Carinha triste é: "+qtdCarinhaTriste+" A qtd carinha Feliz é: "+qtdCarinhaFeliz);
		
		/*
		List<List<Integer>> lista = new ArrayList<List<Integer>>();
		List<Integer> primeiroIndex = new ArrayList<Integer>();
		List<Integer> ultimoIndex = new ArrayList<Integer>();
		int i=0;
		
		
		boolean tem = entrada.contains(":-(");
		if(tem) {
			primeiroIndex.add(entrada.indexOf(":-(",i));
			ultimoIndex.add(entrada.lastIndexOf(":-("));
			i=entrada.lastIndexOf(":-(");	
			
		}
		String[] split = entrada.split(".:-(?");
		System.out.println(split.toString());
		*/
		
		
	}
	
}
