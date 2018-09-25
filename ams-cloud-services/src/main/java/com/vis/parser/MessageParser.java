
package com.vis.parser;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vis.messages.SQSMessage;

/**
 * The Class MessageParser.
 */
@Service
public class MessageParser {


	


	/**
	 * Parses the json to object.
	 *
	 * @param <T> the generic type
	 * @param messageAsString the message as string
	 * @param valueType the value type
	 * @return the SQS message
	 */
	public <T> SQSMessage parseJsonToObject(String messageAsString, Class<T> valueType) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return (SQSMessage) mapper.readValue(messageAsString, valueType);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * Parses the object to json.
	 *
	 * @param message the message
	 * @return the string
	 */
	public String parseObjectToJson(SQSMessage message) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
			return jsonString;
		} catch (IOException exception) {

		}
		return null;
	}

}
