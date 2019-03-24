package com.boku.exception;
/**
 * Exception class for checked exception handling
 * */
public class SalesTaxException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SalesTaxException(){
		super();
	}
	
	public SalesTaxException(String msg, Exception e){
		super(msg,e);
	}
}
