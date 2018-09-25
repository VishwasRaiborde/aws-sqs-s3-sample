

package com.vis.sqs.service;

import org.springframework.stereotype.Component;

import com.vis.exception.ServiceException;

/**
 * The Interface MessagingService.
 */
@Component
public interface MessagingService {

	/** The order queue. */
	String JMS_QUEUE = "MessageProcessorQueue";

	/**
	 * Send message.
	 *
	 * @param xml the xml
	 * @param queue the queue
	 * @throws ServiceException the service exception
	 */
	public void sendMessage(String xml, String queue) throws ServiceException;

	

}
