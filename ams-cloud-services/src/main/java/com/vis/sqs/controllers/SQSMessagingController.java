

package com.vis.sqs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vis.exception.ServiceException;
import com.vis.sqs.service.MessagingService;

/**
 * The Class AccountController.
 *
 * @Desc AccountController routes request and response
 */

@RestController
public class SQSMessagingController {

	/** The order messaging service. */
	@Autowired
	@Qualifier("jMSMessagingListernerService")
	private MessagingService messagingService;

	
	/**
	 * Submit order.
	 *
	 * @param jsonAsString the json as string
	 * @throws ServiceException the service exception
	 */
	@RequestMapping(value = "/submitJMSMessage", method = RequestMethod.GET)
	public void submitOrder(@RequestParam("jsonAsString") String jsonAsString) throws ServiceException {
		messagingService.sendMessage(jsonAsString, MessagingService.JMS_QUEUE);
	}


}
