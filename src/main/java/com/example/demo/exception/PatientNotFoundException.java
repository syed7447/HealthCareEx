package com.example.demo.exception;

public class PatientNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3272221121898433425L;
	public PatientNotFoundException() {
		super();
	}
	public PatientNotFoundException(String msg) {
		super(msg);
	}

}
