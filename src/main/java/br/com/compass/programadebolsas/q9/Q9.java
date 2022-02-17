package br.com.compass.programadebolsas.q9;

import br.com.compass.programadebolsas.q9.view.Menu;

public class Q9 {

	public static void main(String[] args) {
		Menu menu = new Menu();
		while(menu.mostraMenu());
		menu.fechaScanner();
	}

}
