
package com.vis.exception;

import com.vis.response.ServiceResponse;

/**
 * The Class ServiceException.
 */
public class ServiceException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9222763569715007835L;

	/** The response. */
	private ServiceResponse<?> response;

	/** The error id. */
	private String errorId;

	/**
	 * Instantiates a new service exception.
	 *
	 * @param errorId
	 *            and message
	 * @param message
	 *            the message
	 */
	public ServiceException(String errorId, String message) {
		this.errorId = errorId;
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param exeption
	 *            the exeption
	 */
	public ServiceException(Exception exeption) {
		super(exeption);
	}

	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public ServiceResponse<?> getResponse() {
		return response;
	}

	/**
	 * Sets the response.
	 *
	 * @param response
	 *            the new response
	 */
	public void setResponse(ServiceResponse<?> response) {
		this.response = response;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

}
