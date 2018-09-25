
package com.vis.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The Class SLProperties.
 */
public class AWSProperties {

	public static final String S3_BUCKET_NAME = "abcdefghi1";
	public static final String JMS_QUEUE = "MessageProcessorQueue";
	private static final Map<String, String> propertyMap = new HashMap<>();
	private static Properties s3properties;

	private AWSProperties() {
	}
	

	static {
		s3properties = System.getProperties();
		propertyMap.put(S3_BUCKET_NAME, s3properties.getProperty(S3_BUCKET_NAME));
		propertyMap.put(JMS_QUEUE, s3properties.getProperty(JMS_QUEUE));
	}

	public static String getProperty(String propKey) {
		return propertyMap.get(propKey);
	}
}
