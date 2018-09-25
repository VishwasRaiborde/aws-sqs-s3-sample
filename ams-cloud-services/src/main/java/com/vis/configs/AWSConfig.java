
package com.vis.configs;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQSClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class AWSConfig.
 */
@Configuration
public class AWSConfig {
	
	/**
	 * Amazon S 3 client.
	 *
	 * @return the amazon S 3 client
	 */
	@Bean
	public AmazonS3Client amazonS3Client() {

		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTPS);
		clientConfig.setUseReaper(true);
		clientConfig.setUseTcpKeepAlive(true);
		return new AmazonS3Client(new EnvironmentVariableCredentialsProvider(),clientConfig);
	}
	
	/**
	 * Amazon SQS client.
	 *
	 * @return the amazon SQS client
	 */
	@Bean
	public AmazonSQSClient amazonSQSClient() {
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTPS);
		clientConfig.setUseReaper(true);
		clientConfig.setUseTcpKeepAlive(true);
		return new AmazonSQSClient(new EnvironmentVariableCredentialsProvider(),clientConfig);
	}

}
