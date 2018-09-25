package com.vis.exception;


public class UnableToAccessS3Exception extends Exception {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7333865212617088161L;

	/**
	 * Instantiates a new unable to access S 3 exception.
	 */
	public UnableToAccessS3Exception() {
		super();
	}

	/**
	 * Instantiates a new unable to access S 3 exception.
	 *
	 * @param message the message
	 */
	public UnableToAccessS3Exception(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new unable to access S 3 exception.
	 *
	 * @param cause the cause
	 */
	public UnableToAccessS3Exception(Throwable cause) {
		super(cause);
	}
	
	
}
