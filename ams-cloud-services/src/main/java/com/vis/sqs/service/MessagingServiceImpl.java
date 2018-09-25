
package com.vis.sqs.service;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.vis.common.util.AWSProperties;
import com.vis.exception.ServiceException;
import com.vis.response.ServiceResponse;


@Component("jMSMessagingListernerService")
public final class MessagingServiceImpl implements MessagingService {
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger("jms.messages.logger");
	
	@Autowired
	protected JmsTemplate jmsTemplate;

	@Autowired
	AmazonSQSClient amazonSQSClient;
	
	/** The order processor. */
	@Autowired
	MessageProcessor messageProcessor ;


	/**
	 * On email receive.
	 *
	 * @param jmsMessage the order as xml
	 * @throws JMSException the JMS exception
	 */
	@JmsListener(destination=MessagingService.JMS_QUEUE)
	private void onMessageReceive(String jmsMessage) throws JMSException{
		logger.debug("RECEIVED " + jmsMessage);
		messageProcessor.processMessage(jmsMessage);
	}
	
	@Override
	public void sendMessage(final String jsonAsString, String queueName) throws ServiceException {
		try {
			jmsTemplate.send(queueName, new MessageCreator() {
				public javax.jms.Message createMessage(Session session) throws JMSException {
					javax.jms.Message message = session.createTextMessage(jsonAsString);
					message.setStringProperty("requiresReply", "No");
					return message;
				}
			});

		} catch (AmazonClientException ase) {
			logger.error("", ase);
		}
	}
	

	/**
	 * Purge queue by message type.
	 * 
	 * @param messageIdentifier
	 *            the message identifier
	 * @return the service response
	 */
	public ServiceResponse<Boolean> purgeQueueByMessageType(String messageIdentifier) {
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(amazonSQSClient.getQueueUrl(
				AWSProperties.getProperty(AWSProperties.JMS_QUEUE)).getQueueUrl());
		List<Message> messages = amazonSQSClient.receiveMessage(receiveMessageRequest.withMessageAttributeNames(messageIdentifier))
				.getMessages();
		for (Message message : messages) {
			String messageReceiptHandle = message.getReceiptHandle();
			amazonSQSClient.deleteMessage(new DeleteMessageRequest(amazonSQSClient.getQueueUrl(
					AWSProperties.getProperty(AWSProperties.JMS_QUEUE)).getQueueUrl(), messageReceiptHandle));
		}
		return null;
	}

}