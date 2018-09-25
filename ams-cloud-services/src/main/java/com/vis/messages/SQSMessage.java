
package com.vis.messages;

import java.io.Serializable;

/**
 * The Class SQSMessage.
 */
public class SQSMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The Enum SQSMessageType.
	 */
	public enum SQSMessageType {/** The order. */
		ORDER,/** The email. */
		EMAIL}
	
	/** The message type. */
	public SQSMessageType messageType ;
	
	/**
	 * Gets the message type.
	 *
	 * @return the messageType
	 */
	public SQSMessageType getMessageType() {
		return messageType;
	}
	
	/**
	 * Sets the message type.
	 *
	 * @param messageType the messageType to set
	 */
	public void setMessageType(SQSMessageType messageType) {
		this.messageType = messageType;
	}
	
	/** The user id. */
	String userId  ; 
	
	/** The channel. */
	String channel  ;
	
	/**
	 * Gets the user id.
	 *
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Gets the channel.
	 *
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Sets the channel.
	 *
	 * @param channel the new channel
	 */
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	} 
	
	
	
}
