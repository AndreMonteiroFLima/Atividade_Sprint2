package br.com.compass.programadebolsas.q9.exceptions;

public class NoDataBaseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDataBaseException() {
		super("No Database found!");
	}
}
