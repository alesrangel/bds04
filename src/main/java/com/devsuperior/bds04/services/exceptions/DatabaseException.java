package com.devsuperior.bds04.services.exceptions;

public class DatabaseException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String str) {
		super(str);
	}

}
