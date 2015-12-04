package com.yang.exception;

public class MyException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public MyException(String msg){
		super(msg);
		this.message=msg;
	}
	public String toString(){
		return message;
	}
}
