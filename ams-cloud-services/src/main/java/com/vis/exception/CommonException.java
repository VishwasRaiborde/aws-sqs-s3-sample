
package com.vis.exception;

/**
 * The Class CommonException.
 */
public class CommonException extends ServiceException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7760931897621982735L;

	/**
	 * Accepts message & Exception for chaining.
	 *
	 * @param errorId
	 *            the error id
	 * @param message
	 *            the message
	 */
	public CommonException(String errorId, String message) {
		super(errorId, message);
	}

	/**
	 * Instantiates a new common exception.
	 *
	 * @param e
	 *            the e
	 */
	public CommonException(Exception e) {
		super(e);
	}

}
