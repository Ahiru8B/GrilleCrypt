package com.ahiru.grillecrypt.service;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable throwArg) {
		super(throwArg);
	}
	
	public ServiceException(String message, Throwable throwArg) {
		super(message, throwArg);
	}
	
}
