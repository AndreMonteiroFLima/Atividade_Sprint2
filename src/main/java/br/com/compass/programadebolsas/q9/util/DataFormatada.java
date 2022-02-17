package br.com.compass.programadebolsas.q9.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataFormatada {

	public static LocalDate addDate(String str) {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return LocalDate.parse(str, dtf);
	}

}
