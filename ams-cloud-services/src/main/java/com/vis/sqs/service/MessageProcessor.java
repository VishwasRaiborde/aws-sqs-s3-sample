
package com.vis.sqs.service
;


/**
 * The Interface OrderProcessor.
 */
public interface MessageProcessor {
	
	/**
	 * Process order.
	 *
	 * @param message the order as xml
	 */
	public void processMessage(String message);

}
