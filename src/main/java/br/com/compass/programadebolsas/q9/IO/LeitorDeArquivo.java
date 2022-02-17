package br.com.compass.programadebolsas.q9.IO;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

import br.com.compass.programadebolsas.q9.controller.ProdutoController;
import br.com.compass.programadebolsas.q9.model.Produto;
import br.com.compass.programadebolsas.q9.util.DataFormatada;

public class LeitorDeArquivo {
		
	public static void leArquivoProdutos(String str) {
		try {
			Scanner arquivo = new Scanner(new File("produtos.csv"));
			ProdutoController pc = new ProdutoController();
			
			while(arquivo.hasNext()) {
				
				String linha = arquivo.nextLine();
				System.out.println(linha);
				Produto produto = new Produto();
				
				Scanner leitorDeLinhas = new Scanner(linha);
				leitorDeLinhas.useLocale(Locale.US);
				leitorDeLinhas.useDelimiter(" ,");	
				
				produto.setNome(leitorDeLinhas.next());
				produto.setDescricao(leitorDeLinhas.next());
				produto.setDesconto(leitorDeLinhas.nextInt());
				double valor = leitorDeLinhas.nextDouble();
				produto.setValor(BigDecimal.valueOf(valor));
				LocalDate data = DataFormatada.addDate(leitorDeLinhas.next());
				produto.setDataInicio(data);				

				pc.insere(produto);
				
				leitorDeLinhas.close();
			}
			
			arquivo.close();
		}catch(Exception e) {
			System.out.println("Erro:"+ e);
		}
	}
	
}
