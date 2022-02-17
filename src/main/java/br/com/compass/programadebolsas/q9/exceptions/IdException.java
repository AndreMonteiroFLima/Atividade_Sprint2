package br.com.compass.programadebolsas.q9.exceptions;

public class IdException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IdException(String msg) {
		super("id Não Econtrado");
	}
	
	
}
