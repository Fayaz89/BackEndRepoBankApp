package com.pack.exception;

public class TransactionNotFoundException extends Exception {

	String msg;
	
	public TransactionNotFoundException(String msg)
	{
		this.msg=msg;
	}
}
