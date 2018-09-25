
package com.vis.exception;

/**
 * The Class CoreInvalideParameterException.
 */
public class CoreInvalidParameterException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4094942142028408769L;

	/**
	 * Instantiates a new core invalid parameter exception.
	 *
	 * @param string
	 *            the string
	 */
	public CoreInvalidParameterException(String string) {
		super(string);
	}

	/**
	 * Instantiates a new core invalid parameter exception.
	 *
	 * @param exception
	 *            the exception
	 */
	public CoreInvalidParameterException(Exception exception) {
		super(exception);
	}

}
