
package com.vis.sqs.service;

import org.springframework.stereotype.Service;


/**
 * The Interface OrderProcessor.
 */
@Service("messageProcessor")
public class MessageProcessorImpl implements MessageProcessor{

	@Override
	public void processMessage(String message) {
		System.out.println(message);
	}
	
	
}
