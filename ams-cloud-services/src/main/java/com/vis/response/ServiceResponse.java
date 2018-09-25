
package com.vis.response;

import java.io.Serializable;

/**
 * The Class ServiceResponse.
 *
 * @param <T>
 *            the generic type
 */
public class ServiceResponse<T> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The result. */
	private T result;

	/** The service message. */
	private String serviceMessage;

	/** The status code. */
	private int statusCode;

	/** The is successfull. */
	private boolean isSuccessful = Boolean.FALSE;


	
	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public T getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result
	 *            the new result
	 */
	public void setResult(T result) {
		this.result = result;
	}

	/**
	 * Gets the service message.
	 *
	 * @return the service message
	 */
	public String getServiceMessage() {
		return serviceMessage;
	}

	/**
	 * Sets the service message.
	 *
	 * @param serviceMessage
	 *            the new service message
	 */
	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}

	/**
	 * Checks if is successful.
	 *
	 * @return true, if is successful
	 */
	public boolean isSuccessful() {
		return isSuccessful;
	}

	/**
	 * Sets the successful.
	 *
	 * @param isSuccessfull
	 *            the new successful
	 */
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode
	 *            the new status code
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public static ServiceResponse getInstance() {
		return new ServiceResponse();
	}


}
